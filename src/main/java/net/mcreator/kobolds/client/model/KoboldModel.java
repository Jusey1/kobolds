package net.mcreator.kobolds.client.model;

import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.EquipmentSlot;
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

import net.mcreator.kobolds.entity.AbstractKoboldEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldModel<T extends AbstractKoboldEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation KOBOLD_MODEL = new ModelLayerLocation(new ResourceLocation("kobolds", "kobold"), "main");

	public KoboldModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = KoboldModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static MeshDefinition createMesh(CubeDeformation cube, float f1) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-2.5F, -3.0F, -6.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(1, 3)
						.addBox(-0.5F, -3.85F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, -0.5F));
		PartDefinition hat = partdefinition.addOrReplaceChild(
				"hat", CubeListBuilder.create().texOffs(0, 50).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.5F))
						.texOffs(17, 52).addBox(-6.0F, -5.2F, -6.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, -0.5F));
		PartDefinition leftHorn = head.addOrReplaceChild("left_horn",
				CubeListBuilder.create().texOffs(36, 0).addBox(-0.5F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, -7.0F, 2.0F, -0.6109F, 0.3054F, 0.1745F));
		PartDefinition rightHorn = head.addOrReplaceChild("right_horn",
				CubeListBuilder.create().texOffs(45, 0).addBox(-1.5F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, -7.0F, 2.0F, -0.6109F, -0.3054F, -0.1745F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(46, 16).addBox(-3.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, 5.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(33, 16).addBox(0.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 5.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(13, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.5F, 14.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.5F, 14.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(3, 15).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, 0.0F));
		PartDefinition tail = body.addOrReplaceChild("tail",
				CubeListBuilder.create().texOffs(24, 15).addBox(-1.0F, 6.0F, -4.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
		return meshdefinition;
	}

	@Override
	public void setupAnim(T kobold, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
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
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.04F) * 0.04F + 0.04F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.04F) * 0.04F + 0.04F;
		if (this.riding) {
			this.rightLeg.xRot = -1.5708F;
			this.leftLeg.xRot = -1.5708F;
			this.rightLeg.yRot = 0.2618F;
			this.leftLeg.yRot = -0.2618F;
		}
		if (kobold.isPartyKobold()) {
			this.head.xRot += Mth.cos(ageInTicks * 0.4F) * 0.04F + 0.04F;
			this.hat.xRot += Mth.cos(ageInTicks * 0.4F) * 0.04F + 0.04F;
		}
		if (kobold.hasItemInSlot(EquipmentSlot.MAINHAND)) {
			if (kobold.isAggressive()) {
				if (kobold.isLeftHanded()) {
					this.leftArm.xRot = -2.0944F;
					this.leftArm.yRot = -0.1745F;
				} else {
					this.rightArm.xRot = -2.0944F;
					this.rightArm.yRot = 0.1745F;
				}
			} else if (kobold.getMainHandItem().getItem() instanceof CrossbowItem) {
				if (kobold.isLeftHanded()) {
					if (kobold.isCharging()) {
						this.leftArm.xRot = -0.6981F;
						this.leftArm.yRot = 0.3491F;
						this.rightArm.xRot = -1.1345F;
						this.rightArm.yRot = -0.5672F;
					} else if (CrossbowItem.isCharged(kobold.getMainHandItem())) {
						this.leftArm.xRot = -1.4399F;
						this.leftArm.yRot = 0.2618F;
						this.rightArm.xRot = -1.3963F;
						this.rightArm.yRot = -0.3054F;
					}
				} else {
					if (kobold.isCharging()) {
						this.rightArm.xRot = -0.6981F;
						this.rightArm.yRot = -0.3491F;
						this.leftArm.xRot = -1.1345F;
						this.leftArm.yRot = 0.5672F;
					} else if (CrossbowItem.isCharged(kobold.getMainHandItem())) {
						this.rightArm.xRot = -1.4399F;
						this.rightArm.yRot = -0.2618F;
						this.leftArm.xRot = -1.3963F;
						this.leftArm.yRot = 0.3054F;
					}
				}
			}
		}
		if (kobold.hasItemInSlot(EquipmentSlot.OFFHAND)) {
			if (kobold.getOffhandItem().getItem() instanceof TridentItem) {
				if (kobold.isAggressive()) {
					if (kobold.isLeftHanded()) {
						this.rightArm.xRot = 2.8798F;
						this.leftArm.xRot = 0.0F;
					} else {
						this.leftArm.xRot = 2.8798F;
						this.rightArm.xRot = 0.0F;
					}
				}
			} else if (kobold.getOffhandItem().getItem() instanceof ShieldItem) {
				if (kobold.isBlocking()) {
					if (kobold.isLeftHanded()) {
						this.rightArm.xRot = -0.6981F;
						this.rightArm.yRot = -0.2618F;
					} else {
						this.leftArm.xRot = -0.6981F;
						this.leftArm.yRot = 0.2618F;
					}
				}
			} else {
				if (kobold.isLeftHanded()) {
					this.rightArm.xRot = -0.8727F;
					this.rightArm.yRot = 0.0873F;
					this.head.xRot = -0.2618F;
					this.head.xRot = 0.1745F;
					this.hat.xRot = -0.2618F;
					this.hat.xRot = 0.1745F;
				} else {
					this.leftArm.xRot = -0.8727F;
					this.leftArm.yRot = 0.0873F;
					this.head.xRot = -0.2618F;
					this.head.xRot = 0.1745F;
					this.hat.xRot = -0.2618F;
					this.hat.xRot = 0.1745F;
				}
			}
		}
		if (this.attackTime > 0.0F) {
			if (kobold.isAggressive()) {
				if (kobold.isLeftHanded()) {
					float progress = this.attackTime;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					leftArm.xRot = (float) ((double) leftArm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
				} else {
					float progress = this.attackTime;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					rightArm.xRot = (float) ((double) rightArm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
				}
			} else {
				if (kobold.hasItemInSlot(EquipmentSlot.OFFHAND)) {
					float progress = this.attackTime;
					this.body.yRot = Mth.sin(Mth.sqrt(progress) * ((float) Math.PI * 2F)) * 0.2F;
					this.rightArm.yRot += this.body.yRot;
					this.leftArm.yRot += this.body.yRot;
					this.leftArm.xRot += this.body.yRot;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					float f3 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
					rightArm.xRot = (float) ((double) rightArm.xRot - ((double) f2 * 1.2D + (double) f3));
					rightArm.yRot += this.body.yRot * 2.0F;
					rightArm.zRot += Mth.sin(this.attackTime * (float) Math.PI) * -0.4F;
				}
			}
		}
	}

	@Override
	public void translateToHand(HumanoidArm Arm, PoseStack poseStack) {
		switch (Arm) {
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
