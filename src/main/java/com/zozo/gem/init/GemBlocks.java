package com.zozo.gem.init;

import com.zozo.gem.GemC;
import com.zozo.gem.blocks.BlockEssence;
import com.zozo.gem.client.model.FluidModelMapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GemBlocks {
    public static final Fluid FLUID_PINK_ESSENCE = (Fluid)new Fluid("pink_essence", new ResourceLocation("gem:blocks/pink_essence_still"), new ResourceLocation("gem:blocks/pink_essence_flowing"));
    public static BlockEssence PINK_ESSENCE;

    public static void registerBlocks(RegistryEvent.Register<Block> event){
        GemBlocks.registerFluid(GemBlocks.FLUID_PINK_ESSENCE);
        GemBlocks.PINK_ESSENCE = (BlockEssence) new BlockEssence(GemBlocks.FLUID_PINK_ESSENCE, Material.WATER, MapColor.PINK);
        GemBlocks.registerBlock(GemBlocks.PINK_ESSENCE, new ResourceLocation("gem:pink_essence"), event);
        GemC.proxy.registerStateMappers();
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }

    public static void registerBlock(Block block, ResourceLocation location, RegistryEvent.Register<Block> event) {
        block.setRegistryName(location);
        event.getRegistry().register(block);
    }

    public static void registerStateMappers(){
        ModelLoader.setCustomStateMapper(GemBlocks.PINK_ESSENCE, new FluidModelMapper(GemBlocks.FLUID_PINK_ESSENCE));
    }
}
