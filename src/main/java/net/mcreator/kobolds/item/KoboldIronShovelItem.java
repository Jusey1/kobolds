
package net.mcreator.kobolds.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.kobolds.init.KoboldsModTabs;

public class KoboldIronShovelItem extends ShovelItem {
	public KoboldIronShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 256;
			}

			public float getSpeed() {
				return 5f;
			}

			public float getAttackDamageBonus() {
				return 2.5f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 14;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.IRON_INGOT));
			}
		}, 1, -3f, new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB));
	}
}
