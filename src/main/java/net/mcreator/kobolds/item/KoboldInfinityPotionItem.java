
package net.mcreator.kobolds.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class KoboldInfinityPotionItem extends Item {
	public KoboldInfinityPotionItem() {
		super(new Item.Properties().tab(null).stacksTo(1).rarity(Rarity.EPIC));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 32;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A79Instant Health & Regeneration (0:18)"));
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.GENERIC_DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level lvl, Player player, InteractionHand hand) {
		return ItemUtils.startUsingInstantly(lvl, player, hand);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		super.finishUsingItem(itemstack, world, entity);
		entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1, (false), (false)));
		entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 240, 0));
		if (entity instanceof Player player && !player.getAbilities().instabuild) {
			player.getCooldowns().addCooldown(itemstack.getItem(), 240);
		}
		return itemstack;
	}
}
