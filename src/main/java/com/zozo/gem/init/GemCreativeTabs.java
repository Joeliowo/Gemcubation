package com.zozo.gem.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GemCreativeTabs extends CreativeTabs {
    public static final CreativeTabs CREATIVE_TAB_GOOD_GEMS = new GemCreativeTabs("good_gems", 0);
    public static final CreativeTabs CREATIVE_TAB_MISC = new GemCreativeTabs("misc",1);
    public final int id;

    public GemCreativeTabs(String label, int id) {
        super(CreativeTabs.getNextID(), label);
        this.id = id;
    }

    @Override
    public ItemStack getTabIconItem() {
        switch(id){
            case 0:
                return new ItemStack(GemItems.PEBBLE_GEM);
            case 1:
                return new ItemStack(GemItems.ESSENCE);
        }
        return new ItemStack(GemItems.PEBBLE_GEM);
    }
}
