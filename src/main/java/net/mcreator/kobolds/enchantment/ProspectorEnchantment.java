package net.mcreator.kobolds.enchantment;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.kobolds.procedures.ProspectorProcedureProcedure;
import net.mcreator.kobolds.init.KoboldsModEnchantments;

public class ProspectorEnchantment extends Enchantment {
	public ProspectorEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.DIGGER, slots);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		if (ench == Enchantments.MOB_LOOTING)
			return false;
		if (ench == KoboldsModEnchantments.PROSPECTOR.get())
			return false;
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if (stack.getItem() instanceof AxeItem)
			return true;
		return false;
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}

	@Override
	public boolean isDiscoverable() {
		return false;
	}

	@Override
	public boolean isTradeable() {
		return false;
	}

	@Override
	public void doPostAttack(LivingEntity source, Entity target, int inty) {
		if (target instanceof Monster) {
			ProspectorProcedureProcedure.execute(target.level, target.getX(), target.getY(), target.getZ(), target, source);
		}
	}
}
