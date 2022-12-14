package net.mcreator.kobolds.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsConfigProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		File kobolds = new File("");
		com.google.gson.JsonObject koboldz = new com.google.gson.JsonObject();
		kobolds = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "kobolds.json");
		if (!kobolds.exists()) {
			try {
				kobolds.getParentFile().mkdirs();
				kobolds.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			koboldz.addProperty("zombies_attack_kobolds", (false));
			koboldz.addProperty("illagers_attack_kobolds", (false));
			koboldz.addProperty("villagers_avoid_kobolds", (false));
			koboldz.addProperty("kobold_breed_item", "AMETHYST");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(kobolds);
					fileWriter.write(mainGSONBuilderVariable.toJson(koboldz));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
