package net.mcreator.kobolds.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import net.mcreator.kobolds.init.KoboldsModEnchantments;

public class ProspectorProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!entity.isAlive() && !world.isClientSide()) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffects.LUCK) : false) && Math.random() <= 0.25) {
				if (EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
					for (int index0 = 0; index0 < (int) (1 * EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
							(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY))); index0++) {
						if (world instanceof Level _level && !_level.isClientSide()) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				} else if (EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) {
					for (int index1 = 0; index1 < (int) (1 * EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
							(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY))); index1++) {
						if (world instanceof Level _level && !_level.isClientSide()) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				}
			} else if (Math.random() <= 0.1) {
				if (EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
					for (int index2 = 0; index2 < (int) (1 * EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
							(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY))); index2++) {
						if (world instanceof Level _level && !_level.isClientSide()) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				} else if (EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) {
					for (int index3 = 0; index3 < (int) (1 * EnchantmentHelper.getItemEnchantmentLevel(KoboldsModEnchantments.PROSPECTOR.get(),
							(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY))); index3++) {
						if (world instanceof Level _level && !_level.isClientSide()) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(Items.EMERALD));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				}
			}
		}
	}
}
