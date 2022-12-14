package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldRascalEntity;
import net.mcreator.kobolds.client.model.RascalModel;
import net.mcreator.kobolds.client.model.KoboldArmorModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldRascalRenderer extends MobRenderer<KoboldRascalEntity, RascalModel<KoboldRascalEntity>> {
	public KoboldRascalRenderer(EntityRendererProvider.Context context) {
		super(context, new RascalModel(context.bakeLayer(RascalModel.RASCAL_MODEL)), 0.36f);
		this.addLayer(new EyesLayer<KoboldRascalEntity, RascalModel<KoboldRascalEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_glow.png"));
			}
		});
		this.addLayer(new ItemInHandLayer<KoboldRascalEntity, RascalModel<KoboldRascalEntity>>(this, context.getItemInHandRenderer()) {
			public void render(PoseStack pose, MultiBufferSource buffer, int inty, KoboldRascalEntity kobold, float f1, float f2, float f3, float f4,
					float f5, float f6) {
				if (kobold.isAggressive()) {
					super.render(pose, buffer, inty, kobold, f1, f2, f3, f4, f5, f6);
				}
			}
		});
		this.addLayer(new HumanoidArmorLayer<>(this, new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_INNER_MODEL)),
				new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_OUTER_MODEL))));
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldRascalEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_rascal.png");
	}

	@Override
	public void render(KoboldRascalEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
