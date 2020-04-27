package com.zozo.gem.client.render.layers;

import com.zozo.gem.client.render.bases.EntityLayer;
import com.zozo.gem.entities.bases.EntityGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerGemUniform extends EntityLayer implements LayerRenderer<EntityGem> {
    public final RenderLivingBase<?> renderer;
    public final ModelBase model;

    public LayerGemUniform(RenderLivingBase<?> renderer) {
        this.renderer = renderer;
        this.model = this.renderer.getMainModel();
    }

    @Override
    public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(gem.getHasUniform()) {
            this.renderer.bindTexture(this.getEntityTexture(gem));
            float[] insigniaColor = EntitySheep.getDyeRgb(EnumDyeColor.values()[gem.getUniformColor()]);
            GlStateManager.color(insigniaColor[0], insigniaColor[1], insigniaColor[2]);
            this.model.setModelAttributes(this.renderer.getMainModel());
            this.model.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
        }
    }

    public ResourceLocation getEntityTexture(EntityGem gem){
        ResourceLocation location = EntityList.getKey(gem);
        return new ResourceLocation(location.getResourceDomain() + ":textures/entities/" + this.getName(gem) + "/uniform.png");
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
