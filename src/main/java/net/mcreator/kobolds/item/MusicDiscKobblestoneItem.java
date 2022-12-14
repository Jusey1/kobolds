
package net.mcreator.kobolds.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.kobolds.init.KoboldsModTabs;

public class MusicDiscKobblestoneItem extends RecordItem {
	public MusicDiscKobblestoneItem() {
		super(0, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:music_kobblestone")),
				new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB).stacksTo(1).rarity(Rarity.RARE), 0);
	}
}
