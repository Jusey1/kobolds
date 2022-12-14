package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.procedures.KoboldTradeProcedure;
import net.mcreator.kobolds.procedures.KoboldEnchanterInteractionProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldEnchanterEntity extends AbstractKoboldEntity {
	public KoboldEnchanterEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_ENCHANTER.get(), world);
	}

	public KoboldEnchanterEntity(EntityType<KoboldEnchanterEntity> type, Level world) {
		super(type, world);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.2));
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		super.mobInteract(sourceentity, hand);
		KoboldEnchanterInteractionProcedure.execute(this.level, this, sourceentity);
		return InteractionResult.FAIL;
	}

	public static void init() {
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack drop, ItemStack hand) {
		if (drop.getItem() == Items.EMERALD) {
			KoboldTradeProcedure.execute(this.level, this, drop);
			return false;
		} else if (drop.getItem() instanceof ArmorItem) {
			if (EnchantmentHelper.hasBindingCurse(hand)) {
				return false;
			} else if (!(hand.getItem() instanceof ArmorItem)) {
				return true;
			} else {
				ArmorItem armoritem = (ArmorItem) drop.getItem();
				ArmorItem armoritem1 = (ArmorItem) hand.getItem();
				if (armoritem.getDefense() != armoritem1.getDefense()) {
					return armoritem.getDefense() > armoritem1.getDefense();
				} else if (armoritem.getToughness() != armoritem1.getToughness()) {
					return armoritem.getToughness() > armoritem1.getToughness();
				} else {
					return this.canReplaceEqualItem(drop, hand);
				}
			}
		} else {
			return false;
		}
	}
}
