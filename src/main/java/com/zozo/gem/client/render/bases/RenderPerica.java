package com.zozo.gem.client.render.bases;

import com.zozo.gem.entities.bases.EntityPerica;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPerica<B extends EntityPerica> extends RenderBiped<B> {

    public RenderPerica(RenderManager renderManagerIn, ModelBiped modelBipedIn, float shadowSize) {
        super(renderManagerIn, modelBipedIn, shadowSize);
    }

    @Override
    public void renderEntityName(B entityIn, double x, double y, double z, String name, double distanceSq){
        this.renderLivingLabel(entityIn, name, x ,y ,z, 32);
    }
}
