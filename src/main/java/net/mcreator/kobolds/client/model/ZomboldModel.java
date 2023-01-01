package net.mcreator.kobolds.client.model;

import net.minecraft.world.item.TridentItem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;

import net.mcreator.kobolds.client.model.KoboldModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ZomboldModel<T extends Zombie> extends HumanoidModel<T> {
	public static final ModelLayerLocation ZOMBOLD_MODEL = new ModelLayerLocation(new ResourceLocation("kobolds", "zombold"), "main");

	public ZomboldModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = KoboldModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T kobold, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.rightArm.xRot = -1.5708F;
		this.leftArm.xRot = -1.5708F;
		this.rightArm.yRot = 0.0F;
		this.rightArm.zRot = 0.0F;
		this.leftArm.yRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		this.rightLeg.xRot = 0.0F;
		this.leftLeg.xRot = 0.0F;
		this.rightLeg.yRot = 0.0F;
		this.leftLeg.yRot = 0.0F;
		this.body.xRot = 0.0F;
		this.body.yRot = 0.0F;
		this.body.zRot = 0.0F;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.head.yRot = headYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.hat.yRot = headYaw * ((float) Math.PI / 180F);
		this.hat.xRot = headPitch * ((float) Math.PI / 180F);
		this.rightArm.xRot += Mth.cos(ageInTicks * 0.08F) * 0.08F;
		this.leftArm.xRot -= Mth.cos(ageInTicks * 0.08F) * 0.08F;
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.08F) * 0.08F + 0.05F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.08F) * 0.08F + 0.05F;
		if (this.riding) {
			this.rightLeg.xRot = -1.5708F;
			this.leftLeg.xRot = -1.5708F;
			this.rightLeg.yRot = 0.2618F;
			this.leftLeg.yRot = -0.2618F;
		}
		if (kobold.isAggressive()) {
			if (kobold.getMainHandItem().getItem() instanceof TridentItem) {
				if (kobold.isLeftHanded()) {
					this.rightArm.xRot = -1.5708F;
					this.leftArm.xRot = 2.8798F;
					this.leftArm.zRot = 0.1309F;
				} else {
					this.leftArm.xRot = -1.5708F;
					this.rightArm.xRot = 2.8798F;
					this.rightArm.zRot = -0.1309F;
				}
			} else {
				this.rightArm.xRot = -2.0944F;
				this.leftArm.xRot = -2.0944F;
			}
		}
		if (this.attackTime > 0.0F) {
			float progress = this.attackTime;
			progress = 1.0F - this.attackTime;
			progress = progress * progress;
			progress = progress * progress;
			progress = 1.0F - progress;
			float f2 = Mth.sin(progress * (float) Math.PI);
			leftArm.xRot = (float) ((double) leftArm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
			rightArm.xRot = (float) ((double) rightArm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
		}
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		switch (arm) {
			case LEFT -> {
				this.leftArm.translateAndRotate(poseStack);
				poseStack.translate(0.045, 0.05, 0.0);
				poseStack.scale(0.75F, 0.75F, 0.75F);
			}
			case RIGHT -> {
				this.rightArm.translateAndRotate(poseStack);
				poseStack.translate(-0.045, 0.05, 0.0);
				poseStack.scale(0.75F, 0.75F, 0.75F);
			}
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
			float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		hat.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		rightArm.render(poseStack, buffer, packedLight, packedOverlay);
		rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		leftArm.render(poseStack, buffer, packedLight, packedOverlay);
		leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
