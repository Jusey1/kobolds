package net.mcreator.kobolds.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.init.KoboldsModTabs;
import net.mcreator.kobolds.init.KoboldsModItems;

import java.util.Set;
import com.google.common.collect.Sets;

public class KoboldIronAxeItem extends AxeItem {
	public KoboldIronAxeItem() {
		super(new Tier() {
			public int getUses() {
				return 256;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return 6f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 14;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(KoboldsModItems.KOBOLD_IRON_AXE.get()), new ItemStack(Items.IRON_INGOT));
			}
		}, 1, -3f, new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB));
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity attacker) {
		itemstack.hurtAndBreak(1, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.world.item.enchantment.Enchantment enchantment) {
		Set<Enchantment> ALLOWED_ENCHANTMENTS = Sets.newHashSet(Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK,
				Enchantments.FIRE_ASPECT, Enchantments.MOB_LOOTING);
		if (ALLOWED_ENCHANTMENTS.contains(enchantment)) {
			return true;
		}
		Set<Enchantment> DENIED_ENCHANTMENTS = Sets.newHashSet(Enchantments.BLOCK_FORTUNE, Enchantments.BLOCK_EFFICIENCY, Enchantments.SILK_TOUCH,
				Enchantments.SHARPNESS);
		if (DENIED_ENCHANTMENTS.contains(enchantment)) {
			return false;
		}
		return enchantment.category.canEnchant(stack.getItem());
	}
}
