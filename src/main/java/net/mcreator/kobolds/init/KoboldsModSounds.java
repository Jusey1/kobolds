
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.kobolds.KoboldsMod;

public class KoboldsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KoboldsMod.MODID);
	public static final RegistryObject<SoundEvent> KOBOLD_TRADE = REGISTRY.register("kobold_trade",
			() -> new SoundEvent(new ResourceLocation("kobolds", "kobold_trade")));
	public static final RegistryObject<SoundEvent> KOBOLD_PURR = REGISTRY.register("kobold_purr",
			() -> new SoundEvent(new ResourceLocation("kobolds", "kobold_purr")));
	public static final RegistryObject<SoundEvent> KOBOLD_DEATH = REGISTRY.register("kobold_death",
			() -> new SoundEvent(new ResourceLocation("kobolds", "kobold_death")));
	public static final RegistryObject<SoundEvent> KOBOLD_HURT = REGISTRY.register("kobold_hurt",
			() -> new SoundEvent(new ResourceLocation("kobolds", "kobold_hurt")));
	public static final RegistryObject<SoundEvent> KOBOLD_IDLE = REGISTRY.register("kobold_idle",
			() -> new SoundEvent(new ResourceLocation("kobolds", "kobold_idle")));
	public static final RegistryObject<SoundEvent> MUSIC_KOBBLESTONE = REGISTRY.register("music_kobblestone",
			() -> new SoundEvent(new ResourceLocation("kobolds", "music_kobblestone")));
}
