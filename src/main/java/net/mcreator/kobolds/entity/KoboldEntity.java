package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.EntityType;

import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldEntity extends AbstractKoboldEntity {
	public KoboldEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD.get(), world);
	}

	public KoboldEntity(EntityType<KoboldEntity> type, Level world) {
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

	public static void init() {
	}
}
