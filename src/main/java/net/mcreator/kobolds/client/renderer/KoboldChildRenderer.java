package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldChildEntity;
import net.mcreator.kobolds.client.model.KoboldChildModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldChildRenderer extends MobRenderer<KoboldChildEntity, KoboldChildModel<KoboldChildEntity>> {
	public KoboldChildRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldChildModel(context.bakeLayer(KoboldChildModel.KOBOLD_CHILD_MODEL)), 0.36f);
		//this.addLayer(new ItemInHandLayer<KoboldChildEntity, KoboldChildModel<KoboldChildEntity>>(this, context.getItemInHandRenderer()));
		this.addLayer(new EyesLayer<KoboldChildEntity, KoboldChildModel<KoboldChildEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_glow.png"));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldChildEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_classic.png");
	}

	@Override
	public void render(KoboldChildEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.45F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
