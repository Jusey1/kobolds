package net.mcreator.kobolds.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.entity.KoboldRascalEntity;
import net.mcreator.kobolds.entity.KoboldEntity;

public class RascalSpawnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean Spawn = false;
		if (!world.isClientSide() && world.isEmptyBlock(new BlockPos(x, y, z))
				&& world.getBlockState(new BlockPos(x, y, z)).getLightEmission(world, new BlockPos(x, y, z)) <= 4) {
			if (!world.getEntitiesOfClass(KoboldEntity.class, AABB.ofSize(new Vec3(x, y, z), 64, 64, 64), e -> true).isEmpty()
					&& !(!world.getEntitiesOfClass(KoboldRascalEntity.class, AABB.ofSize(new Vec3(x, y, z), 128, 128, 128), e -> true).isEmpty())) {
				Spawn = true;
			} else {
				Spawn = false;
			}
		}
		return Spawn;
	}
}
