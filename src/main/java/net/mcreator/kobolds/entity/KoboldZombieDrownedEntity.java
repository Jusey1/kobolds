package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Difficulty;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.kobolds.procedures.ZomboldSpawnProcedure;
import net.mcreator.kobolds.procedures.KoboldEnchanterInteractionProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class KoboldZombieDrownedEntity extends Drowned {
	public KoboldZombieDrownedEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_ZOMBIE_DROWNED.get(), world);
	}

	public KoboldZombieDrownedEntity(EntityType<KoboldZombieDrownedEntity> type, Level world) {
		super(type, world);
		getEyePosition(0.5F);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, GlowSquid.class, true, false));
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? 0.0D : -0.15D;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.66F : 1.26F;
	}

	protected boolean convertsInWater() {
		return false;
	}

	public boolean isBaby() {
		return false;
	}

	public static void init() {
		SpawnPlacements.register(KoboldsModEntities.KOBOLD_ZOMBIE_DROWNED.get(), SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL
						&& Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		ZomboldSpawnProcedure.execute(this);
		return retval;
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		KoboldEnchanterInteractionProcedure.execute(this.level, this, sourceentity);
		return InteractionResult.FAIL;
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
		return builder;
	}
}
