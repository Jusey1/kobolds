
package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.procedures.KoboldEngineerInteractionProcedure;
import net.mcreator.kobolds.procedures.KoboldBaseTickChildProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldChildEntity extends AbstractKoboldEntity {
	public KoboldChildEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_CHILD.get(), world);
	}

	public KoboldChildEntity(EntityType<KoboldChildEntity> type, Level world) {
		super(type, world);
		setPersistenceRequired();
		this.setCanPickUpLoot(false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.2));
	}

	public boolean isBaby() {
		return true;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.66F : 1.26F;
	}

	public static void init() {
	}

	@Override
	public void baseTick() {
		super.baseTick();
		KoboldBaseTickChildProcedure.execute(this.level, this);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		KoboldEngineerInteractionProcedure.execute(this.level, this, sourceentity);
		return InteractionResult.FAIL;
	}
}
