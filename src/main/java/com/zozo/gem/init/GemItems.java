package com.zozo.gem.init;

import com.zozo.gem.items.ItemGem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class GemItems {
    public static final ItemGem PEBBLE_GEM = new ItemGem("pebble");
    public static final Item ESSENCE = new Item().setUnlocalizedName("essence").setCreativeTab(GemCreativeTabs.CREATIVE_TAB_MISC);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        //Puts all the Items into registry.
        registerItem(PEBBLE_GEM, event);

        registerItem(ESSENCE,event);
    }

    public static void registerItem(Item item, RegistryEvent.Register<Item> event){
        //Registers Items.
        item.setRegistryName(new ResourceLocation("gem:" + item.getUnlocalizedName().replaceFirst("item\\.|tile\\.", "")));
        event.getRegistry().register(item);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
