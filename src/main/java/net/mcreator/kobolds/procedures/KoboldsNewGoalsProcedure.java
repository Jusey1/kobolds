package net.mcreator.kobolds.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

import net.mcreator.kobolds.entity.AbstractKoboldEntity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class KoboldsNewGoalsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File kobolds = new File("");
		com.google.gson.JsonObject koboldz = new com.google.gson.JsonObject();
		kobolds = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "kobolds.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(kobolds));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				koboldz = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (entity instanceof Zombie && !(entity instanceof ZombifiedPiglin)
						&& koboldz.get("zombies_attack_kobolds").getAsBoolean() == true) {
					((Mob) entity).targetSelector.addGoal(3, new NearestAttackableTargetGoal((Mob) entity, AbstractKoboldEntity.class, false));
				} else if (entity instanceof AbstractIllager && koboldz.get("illagers_attack_kobolds").getAsBoolean() == true) {
					((Mob) entity).targetSelector.addGoal(3, new NearestAttackableTargetGoal((Mob) entity, AbstractKoboldEntity.class, false));
				} else if (entity instanceof Villager && koboldz.get("villagers_avoid_kobolds").getAsBoolean() == true) {
					((Villager) entity).goalSelector.addGoal(1, new AvoidEntityGoal((Villager) entity, AbstractKoboldEntity.class, (float) 6, 0.6, 0.8));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
