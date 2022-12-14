package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.procedures.KoboldCaptainInteractionProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldCaptainEntity extends AbstractKoboldEntity {
	public KoboldCaptainEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_CAPTAIN.get(), world);
	}

	public KoboldCaptainEntity(EntityType<KoboldCaptainEntity> type, Level world) {
		super(type, world);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Silverfish.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Zombie.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Villager.class, true, false));
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		KoboldCaptainInteractionProcedure.execute(this.level, this, sourceentity);
		return InteractionResult.FAIL;
	}

	public static void init() {
	}
}
