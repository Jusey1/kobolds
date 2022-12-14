package net.mcreator.kobolds.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.network.KoboldsModVariables;

public class ZomboldSpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(KoboldsModVariables.mainhand.getItem() == (ItemStack.EMPTY).getItem())) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = KoboldsModVariables.mainhand;
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			KoboldsModVariables.mainhand = (ItemStack.EMPTY);
		}
		if (!(KoboldsModVariables.offhand.getItem() == (ItemStack.EMPTY).getItem())) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = KoboldsModVariables.offhand;
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			KoboldsModVariables.offhand = (ItemStack.EMPTY);
		}
		if (!(KoboldsModVariables.armor0.getItem() == (ItemStack.EMPTY).getItem())) {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(0, KoboldsModVariables.armor0);
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.FEET, KoboldsModVariables.armor0);
				}
			}
			KoboldsModVariables.armor0 = (ItemStack.EMPTY);
		}
		if (!(KoboldsModVariables.armor1.getItem() == (ItemStack.EMPTY).getItem())) {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(1, KoboldsModVariables.armor1);
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.LEGS, KoboldsModVariables.armor1);
				}
			}
			KoboldsModVariables.armor1 = (ItemStack.EMPTY);
		}
		if (!(KoboldsModVariables.armor2.getItem() == (ItemStack.EMPTY).getItem())) {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(2, KoboldsModVariables.armor2);
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.CHEST, KoboldsModVariables.armor2);
				}
			}
			KoboldsModVariables.armor2 = (ItemStack.EMPTY);
		}
		if (!(KoboldsModVariables.armor3.getItem() == (ItemStack.EMPTY).getItem())) {
			{
				Entity _entity = entity;
				if (_entity instanceof Player _player) {
					_player.getInventory().armor.set(3, KoboldsModVariables.armor3);
					_player.getInventory().setChanged();
				} else if (_entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.HEAD, KoboldsModVariables.armor3);
				}
			}
			KoboldsModVariables.armor3 = (ItemStack.EMPTY);
		}
	}
}
