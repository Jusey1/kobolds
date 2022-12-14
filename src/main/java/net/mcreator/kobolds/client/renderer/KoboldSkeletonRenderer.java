package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldSkeletonEntity;
import net.mcreator.kobolds.client.model.SkeleboldModel;
import net.mcreator.kobolds.client.model.KoboldArmorModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldSkeletonRenderer extends MobRenderer<KoboldSkeletonEntity, SkeleboldModel<KoboldSkeletonEntity>> {
	public KoboldSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeleboldModel(context.bakeLayer(SkeleboldModel.SKELEBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<KoboldSkeletonEntity, SkeleboldModel<KoboldSkeletonEntity>>(this, context.getItemInHandRenderer()));
		this.addLayer(new EyesLayer<KoboldSkeletonEntity, SkeleboldModel<KoboldSkeletonEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_skeleton_glow.png"));
			}
		});
		this.addLayer(new HumanoidArmorLayer<>(this, new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_INNER_MODEL)),
				new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_OUTER_MODEL))));
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldSkeletonEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_skeleton.png");
	}

	@Override
	public void render(KoboldSkeletonEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
