package net.mcreator.kobolds.item;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.mcreator.kobolds.init.KoboldsModTabs;
import net.mcreator.kobolds.KoboldsMod;

public class KoboldBanner {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, KoboldsMod.MODID);
	public static final DeferredRegister<BannerPattern> REGISTRI = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, KoboldsMod.MODID);

	static {
		REGISTRI.register("kobold", () -> new BannerPattern("kobold"));
		initBanners();
	}

	public static void initBanners() {
		registerPatternItem("kobold");
	}

	private static void registerPatternItem(String name) {
		TagKey<BannerPattern> bannerPatternTagKey = TagKey.create(Registry.BANNER_PATTERN_REGISTRY,
				new ResourceLocation(KoboldsMod.MODID, "pattern_for_" + name));
		REGISTRY.register("banner_pattern_" + name,
				() -> new BannerPatternItem(bannerPatternTagKey, (new Item.Properties()).stacksTo(1).tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB)));
	}
}
