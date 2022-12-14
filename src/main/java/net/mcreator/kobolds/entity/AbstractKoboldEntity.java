package net.mcreator.kobolds.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.procedures.KoboldTraderInteractionProcedure;
import net.mcreator.kobolds.procedures.KoboldTradeProcedure;
import net.mcreator.kobolds.procedures.KoboldSpawnProcedure;
import net.mcreator.kobolds.procedures.KoboldDeathProcedure;
import net.mcreator.kobolds.procedures.KoboldBaseTickProcedure;

import javax.annotation.Nullable;

public abstract class AbstractKoboldEntity extends Monster implements CrossbowAttackMob, RangedAttackMob {
	protected AbstractKoboldEntity(EntityType<? extends Monster> type, Level world) {
		super(type, world);
		xpReward = 4;
		this.setCanPickUpLoot(true);
		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
	}

	private static final EntityDataAccessor<Boolean> DATA_CHARGING_STATE = SynchedEntityData.defineId(AbstractKoboldEntity.class,
			EntityDataSerializers.BOOLEAN);
	private boolean partyKobold;
	@Nullable
	private BlockPos jukebox;

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(0, new AvoidEntityGoal(this, IronGolem.class, (float) 14, 1.2, 1.8));
		this.goalSelector.addGoal(0, new AvoidEntityGoal(this, Creeper.class, (float) 4, 1.2, 1.6));
		this.targetSelector.addGoal(0, new AbstractKoboldEntity.KoboldRevengeGoal(this));
		this.goalSelector.addGoal(1, new AbstractKoboldEntity.KoboldShieldGoal(this));
		this.goalSelector.addGoal(1, new AbstractKoboldEntity.KoboldTridentAttackGoal(this, 1.0D, 40, 10.0F));
		this.goalSelector.addGoal(1, new RangedCrossbowAttackGoal<>(this, 1.0D, 21.0F));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, (float) 6));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 2);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 2);
		return builder;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean isPreventingPlayerRest(Player player) {
		return false;
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? 0.0D : -0.15D;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		if (this.getMainHandItem().getItem() instanceof CrossbowItem) {
			this.performCrossbowAttack(this, 6.0F);
		} else if (this.getOffhandItem().getItem() instanceof TridentItem) {
			ThrownTrident trident = new ThrownTrident(this.level, this, new ItemStack(Items.TRIDENT));
			double d0 = target.getX() - this.getX();
			double d1 = target.getY(0.3333333333333333D) - trident.getY();
			double d2 = target.getZ() - this.getZ();
			double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
			trident.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
			this.playSound(SoundEvents.DROWNED_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level.addFreshEntity(trident);
			this.getPersistentData().putDouble("TimerTrident", 1200);
		}
	}

	@Override
	public void onCrossbowAttackPerformed() {
		this.noActionTime = 0;
	}

	@Override
	public void shootCrossbowProjectile(LivingEntity arg0, ItemStack arg1, Projectile arg2, float arg3) {
		this.shootCrossbowProjectile(this, arg0, arg2, arg3, 1.6F);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_CHARGING_STATE, false);
	}

	public boolean isCharging() {
		return this.entityData.get(DATA_CHARGING_STATE);
	}

	public void setChargingCrossbow(boolean charging) {
		this.entityData.set(DATA_CHARGING_STATE, charging);
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack drop, ItemStack hand) {
		if (drop.getItem() instanceof SwordItem) {
			if (hand.isEmpty() && (this.getOffhandItem().getItem() instanceof TridentItem)) {
				return false;
			} else if (!(hand.getItem() instanceof SwordItem)) {
				return true;
			} else {
				SwordItem sworditem = (SwordItem) drop.getItem();
				SwordItem sworditem1 = (SwordItem) hand.getItem();
				if (sworditem.getDamage() != sworditem1.getDamage()) {
					return sworditem.getDamage() > sworditem1.getDamage();
				} else {
					return this.canReplaceEqualItem(drop, hand);
				}
			}
		} else if (drop.getItem() instanceof CrossbowItem && hand.getItem() instanceof CrossbowItem) {
			return this.canReplaceEqualItem(drop, hand);
		} else if (drop.getItem() instanceof ArmorItem) {
			if (EnchantmentHelper.hasBindingCurse(hand)) {
				return false;
			} else if (!(hand.getItem() instanceof ArmorItem)) {
				return true;
			} else {
				ArmorItem armoritem = (ArmorItem) drop.getItem();
				ArmorItem armoritem1 = (ArmorItem) hand.getItem();
				if (armoritem.getDefense() != armoritem1.getDefense()) {
					return armoritem.getDefense() > armoritem1.getDefense();
				} else if (armoritem.getToughness() != armoritem1.getToughness()) {
					return armoritem.getToughness() > armoritem1.getToughness();
				} else {
					return this.canReplaceEqualItem(drop, hand);
				}
			}
		} else if (drop.getItem() instanceof ShieldItem && hand.isEmpty()) {
			return true;
		} else if (drop.getItem() == Items.EMERALD) {
			KoboldTradeProcedure.execute(this.level, this, drop);
			return false;
		} else {
			return false;
		}
	}

	public void aiStep() {
		if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 12.76D)
				|| !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
			this.partyKobold = false;
			this.jukebox = null;
		}
		super.aiStep();
	}

	public void setRecordPlayingNearby(BlockPos pos, boolean boop) {
		this.jukebox = pos;
		this.partyKobold = boop;
	}

	public boolean isPartyKobold() {
		return this.partyKobold;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		if (this.isBlocking()) {
			return SoundEvents.SHIELD_BLOCK;
		} else {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_hurt"));
		}
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_death"));
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		if (source.getDirectEntity() instanceof Zombie) {
			Zombie zombie = (Zombie) source.getDirectEntity();
			KoboldDeathProcedure.execute(this.level, this, zombie);
		}
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		KoboldTraderInteractionProcedure.execute(this.level, this, sourceentity);
		return InteractionResult.FAIL;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		KoboldSpawnProcedure.execute(this);
		return retval;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		if (this.isEffectiveAi()) {
			KoboldBaseTickProcedure.execute(this.level, this);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof Projectile) {
			Projectile proj = (Projectile) source.getDirectEntity();
			if (proj.getOwner() instanceof AbstractKoboldEntity) {
				return false;
			}
		} else if (source.getDirectEntity() instanceof AbstractKoboldEntity) {
			return false;
		} else if (source == DamageSource.IN_FIRE) {
			return false;
		}
		return super.hurt(source, amount);
	}

	class KoboldRevengeGoal extends HurtByTargetGoal {
		public KoboldRevengeGoal(AbstractKoboldEntity kobold) {
			super(kobold);
			this.setAlertOthers(new Class[]{AbstractKoboldEntity.class});
		}

		@Override
		public void start() {
			super.start();
			for (AbstractKoboldEntity kobolds : this.mob.level.getEntitiesOfClass(AbstractKoboldEntity.class,
					this.mob.getBoundingBox().inflate(32.0D), (kobold) -> kobold.getTarget() == null)) {
				if (!(this.mob.level.getDifficulty() == Difficulty.PEACEFUL)) {
					kobolds.setTarget(this.mob.getTarget());
				}
			}
		}
	}

	class KoboldShieldGoal extends Goal {
		public final AbstractKoboldEntity kobold;

		public KoboldShieldGoal(AbstractKoboldEntity kobold) {
			this.kobold = kobold;
		}

		@Override
		public boolean canUse() {
			return kobold.getOffhandItem().getItem() instanceof ShieldItem && raiseShield();
		}

		@Override
		public boolean canContinueToUse() {
			return this.canUse();
		}

		@Override
		public void start() {
			if (kobold.getOffhandItem().getItem() instanceof ShieldItem)
				kobold.startUsingItem(InteractionHand.OFF_HAND);
		}

		protected boolean raiseShield() {
			LivingEntity target = kobold.getTarget();
			if (target instanceof RangedAttackMob && kobold.distanceTo(target) >= 0.2D) {
				if (target.getMainHandItem().getItem() instanceof AxeItem) {
					kobold.stopUsingItem();
					return false;
				} else {
					return true;
				}
			} else if (target instanceof LivingEntity && kobold.distanceTo(target) >= 0.2D && kobold.distanceTo(target) <= 3.2D) {
				if (target.getMainHandItem().getItem() instanceof AxeItem) {
					kobold.stopUsingItem();
					return false;
				} else {
					return true;
				}
			} else {
				kobold.stopUsingItem();
				return false;
			}
		}
	}

	class KoboldTridentAttackGoal extends RangedAttackGoal {
		private final AbstractKoboldEntity kobold;

		public KoboldTridentAttackGoal(RangedAttackMob mob, double dub, int inty, float enty) {
			super(mob, dub, inty, enty);
			this.kobold = (AbstractKoboldEntity) mob;
		}

		public boolean canUse() {
			return super.canUse() && this.kobold.getOffhandItem().getItem() == Items.TRIDENT;
		}

		public void start() {
			super.start();
			this.kobold.setAggressive(true);
			this.kobold.startUsingItem(InteractionHand.OFF_HAND);;
		}

		public void stop() {
			super.stop();
			this.kobold.stopUsingItem();
			this.kobold.setAggressive(false);
		}
	}
}
