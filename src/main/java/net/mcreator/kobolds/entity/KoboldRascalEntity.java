package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.procedures.RascalSpawnProcedure;
import net.mcreator.kobolds.procedures.KoboldRascalFoundProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldRascalEntity extends AbstractKoboldEntity {
	public KoboldRascalEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_RASCAL.get(), world);
	}

	public KoboldRascalEntity(EntityType<KoboldRascalEntity> type, Level world) {
		super(type, world);
	}

	public boolean isFound;

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return true;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		if (this.isFound == false && !(this.hasEffect(MobEffects.INVISIBILITY))) {
			this.isFound = true;
		}
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		if (this.isFound == false) {
			KoboldRascalFoundProcedure.execute(this.level, this, sourceentity);
			this.isFound = true;
		}
		for (AbstractKoboldEntity kobolds : this.level.getEntitiesOfClass(AbstractKoboldEntity.class, this.getBoundingBox().inflate(128.0D))) {
			if (!(kobolds instanceof KoboldRascalEntity)) {
				this.getNavigation().moveTo(kobolds.getX(), kobolds.getY(), kobolds.getZ(), 1.2);
			}
		}
		return InteractionResult.FAIL;
	}

	public static void init() {
		SpawnPlacements.register(KoboldsModEntities.KOBOLD_RASCAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return RascalSpawnProcedure.execute(world, x, y, z);
				});
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack item, ItemStack stack) {
		if (item.getItem() instanceof SwordItem) {
			if (stack.isEmpty() && (this.getOffhandItem().getItem() instanceof TridentItem)) {
				return false;
			} else if (!(stack.getItem() instanceof SwordItem)) {
				return true;
			} else {
				SwordItem sworditem = (SwordItem) item.getItem();
				SwordItem sworditem1 = (SwordItem) stack.getItem();
				if (sworditem.getDamage() != sworditem1.getDamage()) {
					return sworditem.getDamage() > sworditem1.getDamage();
				} else {
					return this.canReplaceEqualItem(item, stack);
				}
			}
		} else if (item.getItem() instanceof ArmorItem) {
			if (EnchantmentHelper.hasBindingCurse(stack)) {
				return false;
			} else if (!(stack.getItem() instanceof ArmorItem)) {
				return true;
			} else {
				ArmorItem armoritem = (ArmorItem) item.getItem();
				ArmorItem armoritem1 = (ArmorItem) stack.getItem();
				if (armoritem.getDefense() != armoritem1.getDefense()) {
					return armoritem.getDefense() > armoritem1.getDefense();
				} else if (armoritem.getToughness() != armoritem1.getToughness()) {
					return armoritem.getToughness() > armoritem1.getToughness();
				} else {
					return this.canReplaceEqualItem(item, stack);
				}
			}
		} else {
			return false;
		}
	}
}
