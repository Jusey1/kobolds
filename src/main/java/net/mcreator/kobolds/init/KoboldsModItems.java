
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.kobolds.item.MusicDiscKobblestoneItem;
import net.mcreator.kobolds.item.KoboldPotionWaterItem;
import net.mcreator.kobolds.item.KoboldPotionStealthItem;
import net.mcreator.kobolds.item.KoboldPotionMiningItem;
import net.mcreator.kobolds.item.KoboldPotionLevitationItem;
import net.mcreator.kobolds.item.KoboldPotionLeapingItem;
import net.mcreator.kobolds.item.KoboldPotionHealthItem;
import net.mcreator.kobolds.item.KoboldPotionFireItem;
import net.mcreator.kobolds.item.KoboldPotionCombatItem;
import net.mcreator.kobolds.item.KoboldIronSwordItem;
import net.mcreator.kobolds.item.KoboldIronShovelItem;
import net.mcreator.kobolds.item.KoboldIronPickaxeItem;
import net.mcreator.kobolds.item.KoboldIronHoeItem;
import net.mcreator.kobolds.item.KoboldIronAxeItem;
import net.mcreator.kobolds.item.KoboldInfinityPotionItem;
import net.mcreator.kobolds.KoboldsMod;

public class KoboldsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, KoboldsMod.MODID);
	public static final RegistryObject<Item> KOBOLD = REGISTRY.register("kobold_spawn_egg", () -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD,
			-10066330, -6684775, new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_WARRIOR = REGISTRY.register("kobold_warrior_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_WARRIOR, -10066330, -16738048,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_ENCHANTER = REGISTRY.register("kobold_enchanter_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_ENCHANTER, -10066330, -13057,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_ENGINEER = REGISTRY.register("kobold_engineer_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_ENGINEER, -10066330, -65536,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_PIRATE = REGISTRY.register("kobold_pirate_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_PIRATE, -10066330, -3355648,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_CAPTAIN = REGISTRY.register("kobold_captain_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_CAPTAIN, -6750208, -3355648,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_ZOMBIE = REGISTRY.register("kobold_zombie_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_ZOMBIE, -16724788, -6684775,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_ZOMBIE_DROWNED = REGISTRY.register("kobold_zombie_drowned_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_ZOMBIE_DROWNED, -16737895, -16737793,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_SKELETON = REGISTRY.register("kobold_skeleton_spawn_egg",
			() -> new ForgeSpawnEggItem(KoboldsModEntities.KOBOLD_SKELETON, -3355444, -13421773,
					new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	public static final RegistryObject<Item> KOBOLD_POTION_HEALTH = REGISTRY.register("kobold_potion_health", () -> new KoboldPotionHealthItem());
	public static final RegistryObject<Item> KOBOLD_POTION_FIRE = REGISTRY.register("kobold_potion_fire", () -> new KoboldPotionFireItem());
	public static final RegistryObject<Item> KOBOLD_POTION_STEALTH = REGISTRY.register("kobold_potion_stealth", () -> new KoboldPotionStealthItem());
	public static final RegistryObject<Item> KOBOLD_POTION_COMBAT = REGISTRY.register("kobold_potion_combat", () -> new KoboldPotionCombatItem());
	public static final RegistryObject<Item> KOBOLD_POTION_WATER = REGISTRY.register("kobold_potion_water", () -> new KoboldPotionWaterItem());
	public static final RegistryObject<Item> KOBOLD_POTION_LEAPING = REGISTRY.register("kobold_potion_leaping", () -> new KoboldPotionLeapingItem());
	public static final RegistryObject<Item> KOBOLD_POTION_LEVITATION = REGISTRY.register("kobold_potion_levitation",
			() -> new KoboldPotionLevitationItem());
	public static final RegistryObject<Item> KOBOLD_POTION_MINING = REGISTRY.register("kobold_potion_mining", () -> new KoboldPotionMiningItem());
	public static final RegistryObject<Item> KOBOLD_BLOCK_SKULL = block(KoboldsModBlocks.KOBOLD_BLOCK_SKULL, KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB);
	public static final RegistryObject<Item> KOBOLD_IRON_SWORD = REGISTRY.register("kobold_iron_sword", () -> new KoboldIronSwordItem());
	public static final RegistryObject<Item> KOBOLD_IRON_SHOVEL = REGISTRY.register("kobold_iron_shovel", () -> new KoboldIronShovelItem());
	public static final RegistryObject<Item> KOBOLD_IRON_PICKAXE = REGISTRY.register("kobold_iron_pickaxe", () -> new KoboldIronPickaxeItem());
	public static final RegistryObject<Item> KOBOLD_IRON_AXE = REGISTRY.register("kobold_iron_axe", () -> new KoboldIronAxeItem());
	public static final RegistryObject<Item> KOBOLD_IRON_HOE = REGISTRY.register("kobold_iron_hoe", () -> new KoboldIronHoeItem());
	public static final RegistryObject<Item> MUSIC_DISC_KOBBLESTONE = REGISTRY.register("music_disc_kobblestone",
			() -> new MusicDiscKobblestoneItem());
	public static final RegistryObject<Item> KOBOLD_INFINITY_POTION = REGISTRY.register("kobold_infinity_potion",
			() -> new KoboldInfinityPotionItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
