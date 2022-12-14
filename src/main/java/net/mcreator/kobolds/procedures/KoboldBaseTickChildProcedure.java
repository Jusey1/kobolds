package net.mcreator.kobolds.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;

public class KoboldBaseTickChildProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("TimerGrow") < 24000
				&& (entity.getDisplayName().getString()).equals(Component.translatable("entity.kobolds.kobold_child").getString())) {
			entity.getPersistentData().putDouble("TimerGrow", (entity.getPersistentData().getDouble("TimerGrow") + 1));
		} else if (entity.getPersistentData().getDouble("TimerGrow") >= 24000) {
			if (!entity.level.isClientSide())
				entity.discard();
			if (world.getBiome(new BlockPos(entity.getX(), entity.getY(), entity.getZ()))
					.is(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("minecraft:is_jungle")))) {
				if (Math.random() < 0.06) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldCaptainEntity(KoboldsModEntities.KOBOLD_CAPTAIN.get(), _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				} else {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldPirateEntity(KoboldsModEntities.KOBOLD_PIRATE.get(), _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				}
			} else if (Math.random() > 0.95) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new KoboldEngineerEntity(KoboldsModEntities.KOBOLD_ENGINEER.get(), _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			} else if (Math.random() < 0.1) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new KoboldEnchanterEntity(KoboldsModEntities.KOBOLD_ENCHANTER.get(), _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new KoboldEntity(KoboldsModEntities.KOBOLD.get(), _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}
