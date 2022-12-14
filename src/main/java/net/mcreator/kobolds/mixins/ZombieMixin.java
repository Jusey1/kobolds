package net.mcreator.kobolds.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.kobolds.procedures.KoboldsNewGoalsProcedure;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
	public ZombieMixin(EntityType<? extends Monster> type, Level lvl) {
		super(type, lvl);
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		KoboldsNewGoalsProcedure.execute(this);
		return retval;
	}
}