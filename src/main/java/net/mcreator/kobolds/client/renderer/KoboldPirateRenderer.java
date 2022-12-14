package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.client.model.KoboldModel;
import net.mcreator.kobolds.client.model.KoboldArmorModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldPirateRenderer extends MobRenderer<KoboldPirateEntity, KoboldModel<KoboldPirateEntity>> {
	public KoboldPirateRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldModel(context.bakeLayer(KoboldModel.KOBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<KoboldPirateEntity, KoboldModel<KoboldPirateEntity>>(this, context.getItemInHandRenderer()));
		this.addLayer(new EyesLayer<KoboldPirateEntity, KoboldModel<KoboldPirateEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_pirate_eye.png"));
			}
		});
		this.addLayer(new HumanoidArmorLayer<>(this, new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_INNER_MODEL)),
				new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_OUTER_MODEL))));
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldPirateEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_pirate.png");
	}

	@Override
	public void render(KoboldPirateEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
