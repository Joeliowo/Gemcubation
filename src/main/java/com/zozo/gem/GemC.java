package com.zozo.gem;

import com.zozo.gem.init.GemBlocks;
import com.zozo.gem.init.GemCreativeTabs;
import com.zozo.gem.init.GemEntities;
import com.zozo.gem.init.GemRecipes;
import com.zozo.gem.proxies.CommonProxy;
import com.zozo.gem.world.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GemC.MODID, acceptableRemoteVersions = GemC.VERSION, acceptedMinecraftVersions = GemC.VERSION)
public class GemC {
    public static final String MODID = "gem";
    public static final String VERSION = "1.12.2";

    @Mod.Instance
    public static GemC instance;
    public static StructureGenerator worldGen;

    @SidedProxy(clientSide = "com.zozo.gem.proxies.ClientProxy")
    public static CommonProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        //Pre-Initialization Event is for Loot Tables, Recipes,
        //and setting a World Generator
        GemC.worldGen = new StructureGenerator();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        //Initialization is for (Tile-)Entities, Events, and World Generation
        GemEntities.registerGems();
        GameRegistry.registerWorldGenerator(GemC.worldGen, 50);
    }
}
