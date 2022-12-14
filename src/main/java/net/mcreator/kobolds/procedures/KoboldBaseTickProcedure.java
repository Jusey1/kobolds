package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldRascalEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldChildEntity;
import net.mcreator.kobolds.KoboldsMod;

public class KoboldBaseTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 12 && entity.getPersistentData().getDouble("TimerHeal") == 0) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
					&& !(entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : false) && !(entity instanceof KoboldRascalEntity)) {
				entity.getPersistentData().putDouble("TimerHeal", 6000);
				KoboldsMod.queueServerWork(5, () -> {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_POTION_HEALTH.get());
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 32, -4, (false), (false)));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.drink")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.drink")), SoundSource.HOSTILE, 1, 1,
									false);
						}
					}
					KoboldsMod.queueServerWork(32, () -> {
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1, (false), (false)));
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 440, 1, (false), (false)));
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = (ItemStack.EMPTY);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
					});
				});
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
				&& !(entity instanceof KoboldEnchanterEntity) && !(entity instanceof KoboldChildEntity)) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Items.CROSSBOW);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
		}
		if (entity.getPersistentData().getDouble("TimerTrident") == 1200
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.TRIDENT) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD.get());
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			entity.getPersistentData().putDouble("TimerTrident", (entity.getPersistentData().getDouble("TimerTrident") - 1));
		} else if (entity.getPersistentData().getDouble("TimerTrident") > 1) {
			entity.getPersistentData().putDouble("TimerTrident", (entity.getPersistentData().getDouble("TimerTrident") - 1));
		} else if (entity.getPersistentData().getDouble("TimerTrident") == 1) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Items.TRIDENT);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			entity.getPersistentData().putDouble("TimerTrident", (entity.getPersistentData().getDouble("TimerTrident") - 1));
		}
		if (entity.getPersistentData().getDouble("TimerHeal") > 0) {
			entity.getPersistentData().putDouble("TimerHeal", (entity.getPersistentData().getDouble("TimerHeal") - 1));
		}
		if (entity.getPersistentData().getDouble("TimerApple") == 12000) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new KoboldChildEntity(KoboldsModEntities.KOBOLD_CHILD.get(), _level);
				entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
							null);
				world.addFreshEntity(entityToSpawn);
			}
			entity.getPersistentData().putDouble("TimerApple", (entity.getPersistentData().getDouble("TimerApple") - 1));
		} else if (entity.getPersistentData().getDouble("TimerApple") > 0) {
			entity.getPersistentData().putDouble("TimerApple", (entity.getPersistentData().getDouble("TimerApple") - 1));
		}
	}
}
