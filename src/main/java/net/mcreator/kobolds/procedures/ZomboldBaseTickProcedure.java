package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.network.KoboldsModVariables;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldZombieDrownedEntity;

public class ZomboldBaseTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("Drown") < 600) {
			if (entity.isInWater() && ((world.getBlockState(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ()))).getBlock() == Blocks.WATER
					|| (world.getBlockState(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ()))).getBlock() == Blocks.WATER)) {
				entity.getPersistentData().putDouble("Drown", (entity.getPersistentData().getDouble("Drown") + 1));
			} else if (entity.getPersistentData().getDouble("Drown") > 0) {
				entity.getPersistentData().putDouble("Drown", (entity.getPersistentData().getDouble("Drown") - 1));
			}
		} else if (entity.getPersistentData().getDouble("Drown") >= 600) {
			KoboldsModVariables.mainhand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
			KoboldsModVariables.offhand = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
			KoboldsModVariables.armor0 = (entity instanceof LivingEntity _entGetArmor
					? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
					: ItemStack.EMPTY);
			KoboldsModVariables.armor1 = (entity instanceof LivingEntity _entGetArmor
					? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
					: ItemStack.EMPTY);
			KoboldsModVariables.armor2 = (entity instanceof LivingEntity _entGetArmor
					? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
					: ItemStack.EMPTY);
			KoboldsModVariables.armor3 = (entity instanceof LivingEntity _entGetArmor
					? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
					: ItemStack.EMPTY);
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.converted_to_drowned")), SoundSource.NEUTRAL, 1,
							1);
				} else {
					_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.converted_to_drowned")), SoundSource.NEUTRAL, 1,
							1, false);
				}
			}
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new KoboldZombieDrownedEntity(KoboldsModEntities.KOBOLD_ZOMBIE_DROWNED.get(), _level);
				entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
							null);
				world.addFreshEntity(entityToSpawn);
			}
		}
	}
}
