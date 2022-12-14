package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldZombieDrownedEntity;
import net.mcreator.kobolds.client.model.ZomboldModel;
import net.mcreator.kobolds.client.model.KoboldArmorModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldZombieDrownedRenderer extends MobRenderer<KoboldZombieDrownedEntity, ZomboldModel<KoboldZombieDrownedEntity>> {
	public KoboldZombieDrownedRenderer(EntityRendererProvider.Context context) {
		super(context, new ZomboldModel(context.bakeLayer(ZomboldModel.ZOMBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<KoboldZombieDrownedEntity, ZomboldModel<KoboldZombieDrownedEntity>>(this, context.getItemInHandRenderer()));
		this.addLayer(new EyesLayer<KoboldZombieDrownedEntity, ZomboldModel<KoboldZombieDrownedEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/entities/kobold_drowned_glow.png"));
			}
		});
		this.addLayer(new HumanoidArmorLayer<>(this, new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_INNER_MODEL)),
				new KoboldArmorModel<>(context.bakeLayer(KoboldArmorModel.KOBOLD_ARMOR_OUTER_MODEL))));
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldZombieDrownedEntity entity) {
		return new ResourceLocation("kobolds:textures/entities/kobold_drowned.png");
	}

	@Override
	public void render(KoboldZombieDrownedEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
