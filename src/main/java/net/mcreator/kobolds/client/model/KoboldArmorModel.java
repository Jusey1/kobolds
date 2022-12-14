package net.mcreator.kobolds.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.HumanoidModel;

public class KoboldArmorModel<T extends Monster> extends HumanoidModel<T> {
	public static final ModelLayerLocation KOBOLD_ARMOR_INNER_MODEL = new ModelLayerLocation(new ResourceLocation("kobolds", "kobold_armor_inner"),
			"main");
	public static final ModelLayerLocation KOBOLD_ARMOR_OUTER_MODEL = new ModelLayerLocation(new ResourceLocation("kobolds", "kobold_armor_outer"),
			"main");

	public KoboldArmorModel(ModelPart part) {
		super(part);
	}

	public static LayerDefinition createOuterArmorLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 11.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.35F)).mirror(false), PartPose.offset(1.5F, 15.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.35F)), PartPose.offset(-1.5F, 15.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(0.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.35F)).mirror(false), PartPose.offset(4.5F, 5.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.35F)), PartPose.offset(-4.5F, 5.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createInnerArmorLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.4F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.35F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.45F)).mirror(false), PartPose.offset(1.5F, 15.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.45F)), PartPose.offset(-1.5F, 15.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(0.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.45F)).mirror(false), PartPose.offset(4.5F, 5.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -1.55F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.45F)), PartPose.offset(-4.5F, 5.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
