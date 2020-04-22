package com.zozo.gem;

import com.zozo.gem.init.GemEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GemC.MODID, acceptableRemoteVersions = GemC.VERSION, acceptedMinecraftVersions = GemC.VERSION)
public class GemC {
    public static final String MODID = "gem";
    public static final String VERSION = "1.12.2";

    @Mod.Instance
    public static GemC instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        //Pre-Initialization Event is for Loot Tables, Recipes,
        //and setting a World Generator

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        //Initialization is for (Tile-)Entities, Events, and World Generation
        GemEntities.registerGems();
    }
}
