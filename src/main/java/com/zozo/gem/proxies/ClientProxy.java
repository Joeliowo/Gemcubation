package com.zozo.gem.proxies;

import com.zozo.gem.client.model.FluidModelMapper;
import com.zozo.gem.init.GemBlocks;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerStateMappers() {
        ModelLoader.setCustomStateMapper(GemBlocks.PINK_ESSENCE, new FluidModelMapper(GemBlocks.FLUID_PINK_ESSENCE));
    }
}
