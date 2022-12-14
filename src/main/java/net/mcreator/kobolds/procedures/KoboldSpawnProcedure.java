package net.mcreator.kobolds.procedures;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;

import net.mcreator.kobolds.network.KoboldsModVariables;
import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldSkeletonEntity;
import net.mcreator.kobolds.entity.KoboldRascalEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;

public class KoboldSpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (KoboldsModVariables.mainhand.getItem() == (ItemStack.EMPTY).getItem()) {
			if (entity instanceof KoboldCaptainEntity) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = (EnchantmentHelper.enchantItem(RandomSource.create(),
							new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD.get()), 30, (false)));
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			} else if (entity instanceof KoboldRascalEntity) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = (EnchantmentHelper.enchantItem(RandomSource.create(),
							new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD.get()), 20, (false)));
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 24000, 0, (false), (false)));
			} else if (entity instanceof KoboldEntity || entity instanceof KoboldPirateEntity) {
				if (Math.random() >= 0.6) {
					if (Math.random() <= 0.45 && entity instanceof KoboldPirateEntity) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.TRIDENT);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					} else if (Math.random() >= 0.95) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = (EnchantmentHelper.enchantItem(RandomSource.create(), new ItemStack(Items.CROSSBOW), 30, (false)));
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					} else {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.CROSSBOW);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					}
				} else if (Math.random() <= 0.1) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = (EnchantmentHelper.enchantItem(RandomSource.create(),
								new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD.get()), 30, (false)));
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				} else {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD.get());
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			} else if (entity instanceof KoboldSkeletonEntity || entity instanceof KoboldEngineerEntity) {
				if (Math.random() >= 0.6) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = (EnchantmentHelper.enchantItem(RandomSource.create(), new ItemStack(Items.CROSSBOW), 30, (false)));
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				} else {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Items.CROSSBOW);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			} else if (entity instanceof KoboldWarriorEntity) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_AXE.get());
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		} else {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = KoboldsModVariables.mainhand;
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			KoboldsModVariables.mainhand = (ItemStack.EMPTY);
		}
		if (KoboldsModVariables.offhand.getItem() == (ItemStack.EMPTY).getItem()) {
			if (entity instanceof KoboldWarriorEntity) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.SHIELD);
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		} else {
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
