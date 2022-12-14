package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;

import net.mcreator.kobolds.KoboldsMod;

public class KoboldCaptainInteractionProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!world.isClientSide()) {
			if (((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY)
					.getItem()) == true) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
						.is(ItemTags.create(new ResourceLocation("kobolds:captain_tier_one")))) {
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayer _serverPlayer) {
								return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
							} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
								return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
										&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())
												.getGameMode() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(sourceentity))) {
						KoboldsMod.queueServerWork(1, () -> {
							if (sourceentity instanceof Player _player) {
								ItemStack _stktoremove = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
										_player.inventoryMenu.getCraftSlots());
							}
						});
					}
					if (sourceentity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, -10, (false), (false)));
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					KoboldsMod.queueServerWork(100, () -> {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1, false);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
											_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/loot spawn ~ ~ ~ loot kobolds:gameplay/captain_one_loot");
						KoboldsMod.queueServerWork(20, () -> {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = (ItemStack.EMPTY);
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						});
					});
				} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
						.is(ItemTags.create(new ResourceLocation("kobolds:captain_tier_two")))) {
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayer _serverPlayer) {
								return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
							} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
								return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
										&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())
												.getGameMode() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(sourceentity))) {
						KoboldsMod.queueServerWork(1, () -> {
							if (sourceentity instanceof Player _player) {
								ItemStack _stktoremove = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
										_player.inventoryMenu.getCraftSlots());
							}
						});
					}
					if (sourceentity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, -10, (false), (false)));
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					KoboldsMod.queueServerWork(100, () -> {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1, false);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
											_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/loot spawn ~ ~ ~ loot kobolds:gameplay/captain_two_loot");
						KoboldsMod.queueServerWork(20, () -> {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = (ItemStack.EMPTY);
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						});
					});
				} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
						.is(ItemTags.create(new ResourceLocation("kobolds:captain_tier_three")))) {
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayer _serverPlayer) {
								return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
							} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
								return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
										&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())
												.getGameMode() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(sourceentity))) {
						KoboldsMod.queueServerWork(1, () -> {
							if (sourceentity instanceof Player _player) {
								ItemStack _stktoremove = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
										_player.inventoryMenu.getCraftSlots());
							}
						});
					}
					if (sourceentity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, -10, (false), (false)));
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					KoboldsMod.queueServerWork(100, () -> {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.NEUTRAL, 1,
										1, false);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
											_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/loot spawn ~ ~ ~ loot kobolds:gameplay/captain_three_loot");
						KoboldsMod.queueServerWork(20, () -> {
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
		}
	}
}
