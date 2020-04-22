package com.zozo.gem.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPebble - Cilli
 * Created using Tabula 7.1.0
 */
public class ModelPebble extends ModelBiped {
    public ModelRenderer BipedBody;
    public ModelRenderer BipedLeftLeg;
    public ModelRenderer BipedRightLeg;
    public ModelRenderer BipedLeftArm;
    public ModelRenderer BipedRightArm;
    public ModelRenderer BipedHead;

    public ModelPebble() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.BipedLeftLeg = new ModelRenderer(this, 0, 7);
        this.BipedLeftLeg.setRotationPoint(-1.9F, 20.0F, -1.0F);
        this.BipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.BipedBody = new ModelRenderer(this, 0, 0);
        this.BipedBody.setRotationPoint(-2.0F, 18.5F, -1.5F);
        this.BipedBody.addBox(0.0F, -2.0F, 0.0F, 4, 4, 3, 0.0F);
        this.BipedLeftArm = new ModelRenderer(this, 8, 7);
        this.BipedLeftArm.setRotationPoint(-4.0F, 16.5F, -1.0F);
        this.BipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.BipedRightLeg = new ModelRenderer(this, 0, 7);
        this.BipedRightLeg.setRotationPoint(-0.1F, 20.0F, -1.0F);
        this.BipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.BipedRightArm = new ModelRenderer(this, 8, 7);
        this.BipedRightArm.setRotationPoint(2.0F, 16.5F, -1.0F);
        this.BipedRightArm.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.BipedHead = new ModelRenderer(this, 14, 0);
        this.BipedHead.setRotationPoint(-2.5F, 12.5F, -2.0F);
        this.BipedHead.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BipedLeftLeg.render(f5);
        this.BipedBody.render(f5);
        this.BipedLeftArm.render(f5);
        this.BipedRightLeg.render(f5);
        this.BipedRightArm.render(f5);
        this.BipedHead.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
