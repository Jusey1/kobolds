package net.mcreator.kobolds.client.renderer;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldZombieEntity;
import net.mcreator.kobolds.client.model.ZomboldModel;
import net.mcreator.kobolds.client.model.KoboldArmorModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldZombieRenderer extends MobRenderer<KoboldZombieEntity, ZomboldModel<KoboldZombieEntity>> {
	public KoboldZombieRenderer(EntityRendererProvider.Context context) {
		super(context, new ZomboldModel(context.bakeLayer(ZomboldModel.ZOMBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<KoboldZombieEntity, ZomboldModel<KoboldZombieEntity>>(this, context.getItemInHandRenderer()));
		this.addLayer(new EyesLayer<KoboldZombieEntity, ZomboldModel<KoboldZombieEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_zombie_glow.png"));
			}
		});
		this.addLayer(new HumanoidArmorLayer<>(this, new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_INNER_MODEL)),
				new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_OUTER_MODEL))));
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldZombieEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_zombie.png");
	}

	@Override
	protected boolean isShaking(KoboldZombieEntity entity) {
		Level world = entity.level;
		if (entity.isInWater() && (world.getBlockState(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ()))).getBlock() == Blocks.WATER) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void render(KoboldZombieEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
