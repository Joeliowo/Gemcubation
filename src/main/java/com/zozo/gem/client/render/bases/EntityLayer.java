package com.zozo.gem.client.render.bases;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class EntityLayer {
    public String getName(EntityLiving entity){
        ResourceLocation location = EntityList.getKey(entity);
        String name = location.getResourcePath();
        if(location.getResourceDomain().equals("gem")){
            return name.replaceFirst("gem.", "");
        }
        return name;
    }
}
