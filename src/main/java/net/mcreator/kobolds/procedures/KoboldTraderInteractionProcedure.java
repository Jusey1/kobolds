package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
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
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Iterator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class KoboldTraderInteractionProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		File Kobolds = new File("");
		com.google.gson.JsonObject Koboldz = new com.google.gson.JsonObject();
		if ((entity instanceof KoboldEntity || entity instanceof KoboldPirateEntity)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
				&& !world.isClientSide()) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.EMERALD) {
				if ((new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
									.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(sourceentity)) == false) {
					if (sourceentity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(Items.EMERALD);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
								_player.inventoryMenu.getCraftSlots());
					}
				}
				if (sourceentity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, -10, (false), (false)));
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.EMERALD);
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
							_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.HOSTILE, 1, 1,
									false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
										_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/loot spawn ~ ~ ~ loot kobolds:gameplay/trader_loot");
					KoboldsMod.queueServerWork(20, () -> {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = (ItemStack.EMPTY);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
							if (_entity instanceof Player _player)
								_player.getInventory().setChanged();
						}
						if ((sourceentity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
								? _plr.getAdvancements()
										.getOrStartProgress(_plr.server.getAdvancements()
												.getAdvancement(new ResourceLocation("kobolds:kobold_trader_advancement")))
										.isDone()
								: false) == false) {
							if (sourceentity instanceof ServerPlayer _player) {
								Advancement _adv = _player.server.getAdvancements()
										.getAdvancement(new ResourceLocation("kobolds:kobold_trader_advancement"));
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									Iterator _iterator = _ap.getRemainingCriteria().iterator();
									while (_iterator.hasNext())
										_player.getAdvancements().award(_adv, (String) _iterator.next());
								}
							}
						}
					});
				});
			} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == KoboldsModItems.KOBOLD_IRON_AXE.get()) {
				if ((new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
									.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(sourceentity)) == false) {
					if (sourceentity instanceof LivingEntity _entity) {
						ItemStack _setstack = (ItemStack.EMPTY);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
				if (sourceentity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, -10, (false), (false)));
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_AXE.get());
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				KoboldsMod.queueServerWork(600, () -> {
					if (!entity.level.isClientSide())
						entity.discard();
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldWarriorEntity(KoboldsModEntities.KOBOLD_WARRIOR.get(), _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				});
			}
		}
		if (entity.getType().is(TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge:kobolds")))) {
			if (!((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.SHIELD)
					&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY)
							.getItem()) {
				if (world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_purr")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_purr")), SoundSource.HOSTILE, 1, 1,
									false);
						}
					}
				} else {
					if (sourceentity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					if ((sourceentity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
							? _plr.getAdvancements()
									.getOrStartProgress(
											_plr.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:kobold_pet_advancement")))
									.isDone()
							: false) == false) {
						if (sourceentity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements()
									.getAdvancement(new ResourceLocation("kobolds:kobold_pet_advancement"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
					}
				}
			} else if (entity.getPersistentData().getDouble("TimerApple") == 0
					&& !world.getEntitiesOfClass(KoboldWarriorEntity.class,
							AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 32, 32, 32), e -> true).isEmpty()
					&& !(entity instanceof KoboldWarriorEntity) && !world.isClientSide()) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND
						|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
								.getItem() == Items.AMETHYST_SHARD
						|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.APPLE) {
					Kobolds = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "kobolds.json");
					{
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(Kobolds));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							Koboldz = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							if ((Koboldz.get("kobold_breed_item").getAsString()).equals("DIAMOND")) {
								if ((new Object() {
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
								}.checkGamemode(sourceentity)) == false) {
									if (sourceentity instanceof Player _player) {
										ItemStack _stktoremove = new ItemStack(Items.DIAMOND);
										_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
												_player.inventoryMenu.getCraftSlots());
									}
								}
								if (sourceentity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								entity.getPersistentData().putDouble("TimerApple", 24000);
								if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
										.getItem() == (ItemStack.EMPTY).getItem()) {
									if (entity instanceof LivingEntity _entity)
										_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, -10, (false), (false)));
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = new ItemStack(Items.DIAMOND);
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									KoboldsMod.queueServerWork(30, () -> {
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = (ItemStack.EMPTY);
											_setstack.setCount(1);
											_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
									});
								}
							} else if ((Koboldz.get("kobold_breed_item").getAsString()).equals("AMETHYST")) {
								if ((new Object() {
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
								}.checkGamemode(sourceentity)) == false) {
									if (sourceentity instanceof Player _player) {
										ItemStack _stktoremove = new ItemStack(Items.AMETHYST_SHARD);
										_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
												_player.inventoryMenu.getCraftSlots());
									}
								}
								if (sourceentity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								entity.getPersistentData().putDouble("TimerApple", 24000);
								if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
										.getItem() == (ItemStack.EMPTY).getItem()) {
									if (entity instanceof LivingEntity _entity)
										_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, -10, (false), (false)));
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = new ItemStack(Items.AMETHYST_SHARD);
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									KoboldsMod.queueServerWork(30, () -> {
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = (ItemStack.EMPTY);
											_setstack.setCount(1);
											_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
									});
								}
							} else if ((Koboldz.get("kobold_breed_item").getAsString()).equals("APPLE")) {
								if ((new Object() {
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
								}.checkGamemode(sourceentity)) == false) {
									if (sourceentity instanceof Player _player) {
										ItemStack _stktoremove = new ItemStack(Items.APPLE);
										_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
												_player.inventoryMenu.getCraftSlots());
									}
								}
								if (sourceentity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								entity.getPersistentData().putDouble("TimerApple", 24000);
								if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
										.getItem() == (ItemStack.EMPTY).getItem()) {
									if (entity instanceof LivingEntity _entity)
										_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, -10, (false), (false)));
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = new ItemStack(Items.APPLE);
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									KoboldsMod.queueServerWork(30, () -> {
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = (ItemStack.EMPTY);
											_setstack.setCount(1);
											_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
									});
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
