
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.kobolds.block.KoboldBlockSkullBlock;
import net.mcreator.kobolds.KoboldsMod;

public class KoboldsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, KoboldsMod.MODID);
	public static final RegistryObject<Block> KOBOLD_BLOCK_SKULL = REGISTRY.register("kobold_block_skull", () -> new KoboldBlockSkullBlock());
}
