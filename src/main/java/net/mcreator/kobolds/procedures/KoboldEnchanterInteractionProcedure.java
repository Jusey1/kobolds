package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Difficulty;
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

import net.mcreator.kobolds.network.KoboldsModVariables;
import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldZombieEntity;
import net.mcreator.kobolds.entity.KoboldZombieDrownedEntity;
import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Iterator;

public class KoboldEnchanterInteractionProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double waitTicks = 0;
		double potionLevel = 0;
		if (!world.isClientSide()) {
			if (entity instanceof KoboldEnchanterEntity
					&& ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY)
							.getItem()) == true
					&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.EMERALD) {
				if (!(new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
									.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(sourceentity))) {
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
						_entity.swing(InteractionHand.OFF_HAND, true);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_trade")), SoundSource.HOSTILE, 1, 1,
									false);
						}
					}
					if (Math.random() >= 0.1) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
											_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/loot spawn ~ ~ ~ loot kobolds:gameplay/enchanter_potion_loot");
					} else {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO,
											_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/loot spawn ~ ~ ~ loot kobolds:gameplay/enchanter_gear_loot");
					}
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
												.getAdvancement(new ResourceLocation("kobolds:kobold_enchanter_advancement")))
										.isDone()
								: false) == false) {
							if (sourceentity instanceof ServerPlayer _player) {
								Advancement _adv = _player.server.getAdvancements()
										.getAdvancement(new ResourceLocation("kobolds:kobold_enchanter_advancement"));
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
			} else if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.GOLDEN_APPLE
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffects.WEAKNESS) : false)
					&& (entity instanceof KoboldZombieEntity || entity instanceof KoboldZombieDrownedEntity)) {
				if (world.getDifficulty() == Difficulty.EASY) {
					potionLevel = 0;
					waitTicks = 1200;
				} else if (world.getDifficulty() == Difficulty.NORMAL) {
					potionLevel = 0;
					waitTicks = 2400;
				} else if (world.getDifficulty() == Difficulty.HARD) {
					potionLevel = 1;
					waitTicks = 4800;
				}
				if (!(new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft
									.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(sourceentity))) {
					if (sourceentity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(Items.GOLDEN_APPLE);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
								_player.inventoryMenu.getCraftSlots());
					}
				}
				if (sourceentity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.HOSTILE, 1,
								1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.cure")), SoundSource.HOSTILE, 1, 1,
								false);
					}
				}
				if (entity instanceof LivingEntity _entity)
					_entity.removeEffect(MobEffects.WEAKNESS);
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) waitTicks, (int) potionLevel));
				KoboldsMod.queueServerWork((int) waitTicks, () -> {
					if (entity.isAlive()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")),
										SoundSource.HOSTILE, 1, 1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ() - 1),
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")),
										SoundSource.HOSTILE, 1, 1, false);
							}
						}
						KoboldsModVariables.mainhand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
						KoboldsModVariables.offhand = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
						KoboldsModVariables.armor0 = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
								: ItemStack.EMPTY);
						KoboldsModVariables.armor1 = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
								: ItemStack.EMPTY);
						KoboldsModVariables.armor2 = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
								: ItemStack.EMPTY);
						KoboldsModVariables.armor3 = (entity instanceof LivingEntity _entGetArmor
								? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD)
								: ItemStack.EMPTY);
						if (world.getBiome(new BlockPos(entity.getX(), entity.getY(), entity.getZ()))
								.is(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("minecraft:is_jungle")))) {
							if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
									.getItem() == KoboldsModItems.KOBOLD_IRON_SWORD.get()
									&& ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).isEnchanted()) {
								if (world instanceof ServerLevel _level) {
									Entity entityToSpawn = new KoboldCaptainEntity(KoboldsModEntities.KOBOLD_CAPTAIN.get(), _level);
									entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
									if (entityToSpawn instanceof Mob _mobToSpawn)
										_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()),
												MobSpawnType.MOB_SUMMONED, null, null);
									world.addFreshEntity(entityToSpawn);
								}
							} else {
								if (world instanceof ServerLevel _level) {
									Entity entityToSpawn = new KoboldPirateEntity(KoboldsModEntities.KOBOLD_PIRATE.get(), _level);
									entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
									if (entityToSpawn instanceof Mob _mobToSpawn)
										_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()),
												MobSpawnType.MOB_SUMMONED, null, null);
									world.addFreshEntity(entityToSpawn);
								}
							}
						} else if (world.getBiome(new BlockPos(entity.getX(), entity.getY(), entity.getZ()))
								.is(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("minecraft:is_mountain")))
								&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.CROSSBOW
								&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
										.getItem() == (ItemStack.EMPTY).getItem()) {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = new KoboldEngineerEntity(KoboldsModEntities.KOBOLD_ENGINEER.get(), _level);
								entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
								if (entityToSpawn instanceof Mob _mobToSpawn)
									_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()),
											MobSpawnType.MOB_SUMMONED, null, null);
								world.addFreshEntity(entityToSpawn);
							}
						} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.SHIELD) {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = new KoboldWarriorEntity(KoboldsModEntities.KOBOLD_WARRIOR.get(), _level);
								entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
								if (entityToSpawn instanceof Mob _mobToSpawn)
									_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()),
											MobSpawnType.MOB_SUMMONED, null, null);
								world.addFreshEntity(entityToSpawn);
							}
						} else {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = new KoboldEntity(KoboldsModEntities.KOBOLD.get(), _level);
								entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
								if (entityToSpawn instanceof Mob _mobToSpawn)
									_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()),
											MobSpawnType.MOB_SUMMONED, null, null);
								world.addFreshEntity(entityToSpawn);
							}
						}
						if (!entity.level.isClientSide())
							entity.discard();
					}
				});
			}
		}
	}
}
