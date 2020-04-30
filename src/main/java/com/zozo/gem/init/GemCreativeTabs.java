package com.zozo.gem.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> items) {
        super.displayAllRelevantItems(items);
        if (this == GemCreativeTabs.CREATIVE_TAB_MISC) {
            items.add(FluidUtil.getFilledBucket(new FluidStack(GemBlocks.FLUID_PINK_ESSENCE, 1000)));
        }
    }
}
