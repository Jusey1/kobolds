
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.kobolds.entity.KoboldZombieEntity;
import net.mcreator.kobolds.entity.KoboldZombieDrownedEntity;
import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldSkeletonEntity;
import net.mcreator.kobolds.entity.KoboldRascalEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldChildEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;
import net.mcreator.kobolds.KoboldsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KoboldsMod.MODID);
	public static final RegistryObject<EntityType<KoboldEntity>> KOBOLD = register("kobold",
			EntityType.Builder.<KoboldEntity>of(KoboldEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(KoboldEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldWarriorEntity>> KOBOLD_WARRIOR = register("kobold_warrior",
			EntityType.Builder.<KoboldWarriorEntity>of(KoboldWarriorEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldWarriorEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldEnchanterEntity>> KOBOLD_ENCHANTER = register("kobold_enchanter",
			EntityType.Builder.<KoboldEnchanterEntity>of(KoboldEnchanterEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldEnchanterEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldEngineerEntity>> KOBOLD_ENGINEER = register("kobold_engineer",
			EntityType.Builder.<KoboldEngineerEntity>of(KoboldEngineerEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldEngineerEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldPirateEntity>> KOBOLD_PIRATE = register("kobold_pirate",
			EntityType.Builder.<KoboldPirateEntity>of(KoboldPirateEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldPirateEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldCaptainEntity>> KOBOLD_CAPTAIN = register("kobold_captain",
			EntityType.Builder.<KoboldCaptainEntity>of(KoboldCaptainEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldCaptainEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldChildEntity>> KOBOLD_CHILD = register("kobold_child",
			EntityType.Builder.<KoboldChildEntity>of(KoboldChildEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldChildEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldZombieEntity>> KOBOLD_ZOMBIE = register("kobold_zombie",
			EntityType.Builder.<KoboldZombieEntity>of(KoboldZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldZombieEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldZombieDrownedEntity>> KOBOLD_ZOMBIE_DROWNED = register("kobold_zombie_drowned",
			EntityType.Builder.<KoboldZombieDrownedEntity>of(KoboldZombieDrownedEntity::new, MobCategory.MONSTER)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(KoboldZombieDrownedEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldSkeletonEntity>> KOBOLD_SKELETON = register("kobold_skeleton",
			EntityType.Builder.<KoboldSkeletonEntity>of(KoboldSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldSkeletonEntity::new)

					.sized(0.5f, 1.48f));
	public static final RegistryObject<EntityType<KoboldRascalEntity>> KOBOLD_RASCAL = register("kobold_rascal",
			EntityType.Builder.<KoboldRascalEntity>of(KoboldRascalEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldRascalEntity::new)

					.sized(0.5f, 1.48f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			KoboldEntity.init();
			KoboldWarriorEntity.init();
			KoboldEnchanterEntity.init();
			KoboldEngineerEntity.init();
			KoboldPirateEntity.init();
			KoboldCaptainEntity.init();
			KoboldChildEntity.init();
			KoboldZombieEntity.init();
			KoboldZombieDrownedEntity.init();
			KoboldSkeletonEntity.init();
			KoboldRascalEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(KOBOLD.get(), KoboldEntity.createAttributes().build());
		event.put(KOBOLD_WARRIOR.get(), KoboldWarriorEntity.createAttributes().build());
		event.put(KOBOLD_ENCHANTER.get(), KoboldEnchanterEntity.createAttributes().build());
		event.put(KOBOLD_ENGINEER.get(), KoboldEngineerEntity.createAttributes().build());
		event.put(KOBOLD_PIRATE.get(), KoboldPirateEntity.createAttributes().build());
		event.put(KOBOLD_CAPTAIN.get(), KoboldCaptainEntity.createAttributes().build());
		event.put(KOBOLD_CHILD.get(), KoboldChildEntity.createAttributes().build());
		event.put(KOBOLD_ZOMBIE.get(), KoboldZombieEntity.createAttributes().build());
		event.put(KOBOLD_ZOMBIE_DROWNED.get(), KoboldZombieDrownedEntity.createAttributes().build());
		event.put(KOBOLD_SKELETON.get(), KoboldSkeletonEntity.createAttributes().build());
		event.put(KOBOLD_RASCAL.get(), KoboldRascalEntity.createAttributes().build());
	}
}
