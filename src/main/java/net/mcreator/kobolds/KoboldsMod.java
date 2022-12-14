package net.mcreator.kobolds;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.kobolds.init.KoboldsModTabs;
import net.mcreator.kobolds.init.KoboldsModSounds;
import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.init.KoboldsModEnchantments;
import net.mcreator.kobolds.init.KoboldsModBlocks;
import net.mcreator.kobolds.item.KoboldBanner;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.List;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("kobolds")
public class KoboldsMod {
	public static final Logger LOGGER = LogManager.getLogger(KoboldsMod.class);
	public static final String MODID = "kobolds";

	public KoboldsMod() {
		MinecraftForge.EVENT_BUS.register(this);
		KoboldsModTabs.load();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		KoboldsModSounds.REGISTRY.register(bus);
		KoboldsModBlocks.REGISTRY.register(bus);
		KoboldsModItems.REGISTRY.register(bus);
		KoboldBanner.REGISTRY.register(bus);
		KoboldBanner.REGISTRI.register(bus);
		KoboldsModEntities.REGISTRY.register(bus);
		KoboldsModEnchantments.REGISTRY.register(bus);

	}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final List<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ArrayList<>();

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new AbstractMap.SimpleEntry(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}
