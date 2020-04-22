package com.zozo.gem.client.render;

import com.zozo.gem.client.model.ModelPebble;
import com.zozo.gem.client.render.bases.RenderGem;
import com.zozo.gem.client.render.layers.LayerGemSkin;
import com.zozo.gem.entities.gems.EntityPebble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPebble extends RenderGem<EntityPebble> {

    public RenderPebble() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPebble(), .1f);
        this.addLayer(new LayerGemSkin(this));
    }

    @Override
    protected void preRenderCallback(EntityPebble gem, float partialTickTime) {
        GlStateManager.scale(.5F, .5F, .5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPebble gem) {
        return new ResourceLocation("gem:textures/entities/pebble/skin.png");
    }
}
