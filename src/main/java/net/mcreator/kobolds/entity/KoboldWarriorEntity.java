package net.mcreator.kobolds.entity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.EntityType;

import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldWarriorEntity extends AbstractKoboldEntity {
	public KoboldWarriorEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_WARRIOR.get(), world);
	}

	public KoboldWarriorEntity(EntityType<KoboldWarriorEntity> type, Level world) {
		super(type, world);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Skeleton.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractIllager.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Spider.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Zombie.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Villager.class, true, false));
	}

	public static void init() {
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack item, ItemStack stack) {
		if (item.getItem() instanceof AxeItem) {
			if (stack.isEmpty() && (this.getOffhandItem().getItem() instanceof TridentItem)) {
				return false;
			} else if (!(stack.getItem() instanceof AxeItem)) {
				return true;
			} else {
				AxeItem sworditem = (AxeItem) item.getItem();
				AxeItem sworditem1 = (AxeItem) stack.getItem();
				if (sworditem.getAttackDamage() != sworditem1.getAttackDamage()) {
					return sworditem.getAttackDamage() > sworditem1.getAttackDamage();
				} else {
					return this.canReplaceEqualItem(item, stack);
				}
			}
		} else if (item.getItem() instanceof ArmorItem) {
			if (EnchantmentHelper.hasBindingCurse(stack)) {
				return false;
			} else if (!(stack.getItem() instanceof ArmorItem)) {
				return true;
			} else {
				ArmorItem armoritem = (ArmorItem) item.getItem();
				ArmorItem armoritem1 = (ArmorItem) stack.getItem();
				if (armoritem.getDefense() != armoritem1.getDefense()) {
					return armoritem.getDefense() > armoritem1.getDefense();
				} else if (armoritem.getToughness() != armoritem1.getToughness()) {
					return armoritem.getToughness() > armoritem1.getToughness();
				} else {
					return this.canReplaceEqualItem(item, stack);
				}
			}
		} else if (item.getItem() instanceof ShieldItem && stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
