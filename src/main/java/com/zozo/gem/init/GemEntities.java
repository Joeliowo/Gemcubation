package com.zozo.gem.init;

import com.zozo.gem.GemC;
import com.zozo.gem.entities.bases.EntityGem;
import com.zozo.gem.entities.gems.EntityPebble;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class GemEntities {
    public static int currentID = 0;

    public static void registerGems(){
        //Registers the Gems into the game.
        registerGem("pebble", EntityPebble.class, 0, 0);
    }

    public static <B extends EntityGem> void registerGem(String name, Class<B> entity, int back, int fore){
        //Does the registering.
        EntityRegistry.registerModEntity(new ResourceLocation("gem:" + name), entity, name, GemEntities.currentID, GemC.instance, 256, 1, true, back, fore);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            try {
                Class<Render<? extends B>> render = (Class<Render<? extends B>>) GemC.class.getClassLoader().loadClass("com/zozo/gem/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
                RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ++GemEntities.currentID;
    }
}
