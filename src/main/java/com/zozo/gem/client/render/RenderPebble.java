package com.zozo.gem.client.render;

import com.zozo.gem.client.model.ModelPebble;
import com.zozo.gem.client.render.bases.RenderGem;
import com.zozo.gem.client.render.layers.*;
import com.zozo.gem.entities.bases.GemPlacements;
import com.zozo.gem.entities.gems.EntityPebble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPebble extends RenderGem<EntityPebble> {

    public RenderPebble() {
        //Is the renderer.
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPebble(), .1f);
        this.addLayer(new LayerGemSkin(this));
        this.addLayer(new LayerGemPants(this));
        this.addLayer(new LayerGemDress(this));
        this.addLayer(new LayerGemSleeves(this));
        this.addLayer(new LayerGemUniform(this));
        this.addLayer(new LayerGemPlacement(this));
    }

    @Override
    protected void preRenderCallback(EntityPebble gem, float partialTickTime) {
        //Allows you to prepare the next frames render.
        GlStateManager.scale(.7F, .7F, .7F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPebble gem) {
        //Gets the base texture for the Gem.
        GemPlacements placement = GemPlacements.getPlacement(gem.getGemPlacement());
        ResourceLocation loc = new ResourceLocation("");
        if(placement != GemPlacements.LEFT_EYE && placement != GemPlacements.RIGHT_EYE){
            if(!gem.getIsDeformed()){
                loc = new ResourceLocation("gem:textures/entities/pebble/face_0.png");
            }
            else{
                loc = new ResourceLocation("gem:textures/entities/pebble/face_1.png");
            }
        }
        else if(placement == GemPlacements.LEFT_EYE){
            if(!gem.getIsDeformed()){
                loc = new ResourceLocation("gem:textures/entities/pebble/face_2.png");
            }
            else{
                loc = new ResourceLocation("gem:textures/entities/pebble/face_4.png");
            }
        }
        else if(placement == GemPlacements.RIGHT_EYE){
            loc = new ResourceLocation("gem:textures/entities/pebble/face_3.png");
        }
        return loc;
    }
}
