
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.renderer.KoboldZombieRenderer;
import net.mcreator.kobolds.client.renderer.KoboldZombieDrownedRenderer;
import net.mcreator.kobolds.client.renderer.KoboldWarriorRenderer;
import net.mcreator.kobolds.client.renderer.KoboldSkeletonRenderer;
import net.mcreator.kobolds.client.renderer.KoboldRenderer;
import net.mcreator.kobolds.client.renderer.KoboldRascalRenderer;
import net.mcreator.kobolds.client.renderer.KoboldPirateRenderer;
import net.mcreator.kobolds.client.renderer.KoboldEngineerRenderer;
import net.mcreator.kobolds.client.renderer.KoboldEnchanterRenderer;
import net.mcreator.kobolds.client.renderer.KoboldChildRenderer;
import net.mcreator.kobolds.client.renderer.KoboldCaptainRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KoboldsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD.get(), KoboldRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_WARRIOR.get(), KoboldWarriorRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ENCHANTER.get(), KoboldEnchanterRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ENGINEER.get(), KoboldEngineerRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_PIRATE.get(), KoboldPirateRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_CAPTAIN.get(), KoboldCaptainRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_CHILD.get(), KoboldChildRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ZOMBIE.get(), KoboldZombieRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ZOMBIE_DROWNED.get(), KoboldZombieDrownedRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_SKELETON.get(), KoboldSkeletonRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_RASCAL.get(), KoboldRascalRenderer::new);
	}
}
