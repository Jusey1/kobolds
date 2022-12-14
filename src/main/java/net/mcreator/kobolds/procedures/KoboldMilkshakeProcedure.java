package net.mcreator.kobolds.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class KoboldMilkshakeProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		boolean Milkshake = false;
		if (entity.isInWater() && ((world.getBlockState(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ()))).getBlock() == Blocks.WATER
				|| (world.getBlockState(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ()))).getBlock() == Blocks.WATER)) {
			Milkshake = true;
		} else {
			Milkshake = false;
		}
		return Milkshake;
	}
}
