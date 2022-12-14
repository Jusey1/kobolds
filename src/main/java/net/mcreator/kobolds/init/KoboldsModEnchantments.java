
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.kobolds.enchantment.ProspectorEnchantment;
import net.mcreator.kobolds.KoboldsMod;

public class KoboldsModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, KoboldsMod.MODID);
	public static final RegistryObject<Enchantment> PROSPECTOR = REGISTRY.register("prospector", () -> new ProspectorEnchantment());
}
