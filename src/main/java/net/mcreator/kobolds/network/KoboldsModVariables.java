package net.mcreator.kobolds.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModVariables {
	public static ItemStack mainhand = ItemStack.EMPTY;
	public static ItemStack offhand = ItemStack.EMPTY;
	public static ItemStack armor0 = ItemStack.EMPTY;
	public static ItemStack armor1 = ItemStack.EMPTY;
	public static ItemStack armor2 = ItemStack.EMPTY;
	public static ItemStack armor3 = ItemStack.EMPTY;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
