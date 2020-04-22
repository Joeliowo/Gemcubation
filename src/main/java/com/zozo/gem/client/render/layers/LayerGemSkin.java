package com.zozo.gem.client.render.layers;

import com.zozo.gem.client.render.bases.EntityLayer;
import com.zozo.gem.entities.bases.EntityGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

public class LayerGemSkin extends EntityLayer implements LayerRenderer<EntityGem> {
    public final RenderLivingBase<?> renderer;
    public final ModelBase model;

    public LayerGemSkin(RenderLivingBase<?> renderer) {
        this.renderer = renderer;
        this.model = this.renderer.getMainModel();
    }

    @Override
    public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.renderer.bindTexture(this.getEntityTexture(gem));
        int skin = gem.getSkinColor();
        float r = ((skin & 16711680) >> 16) / 255f;
        float g = ((skin & 65280) >> 8) / 255f;
        float b = ((skin & 255) >> 0) / 255f;
        GlStateManager.color(r, g, b, 1f);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.model.setModelAttributes(this.renderer.getMainModel());
        this.model.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.disableBlend();
    }

    public ResourceLocation getEntityTexture(EntityGem gem){
        ResourceLocation location = EntityList.getKey(gem);
        return new ResourceLocation(location.getResourceDomain() + ":textures/entities/" + this.getName(gem) + "/skin.png");
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
