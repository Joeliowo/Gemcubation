package com.zozo.gem.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GemRecipes {

    public static void registerShapelessRecipes(){
        GameRegistry.addShapelessRecipe(new ResourceLocation("gem:pebble_gem"),
                new ResourceLocation("gem:items"),
                new ItemStack(GemItems.PEBBLE_GEM),
                new Ingredient[]{Ingredient.fromItem(new ItemStack(Blocks.STONE_BUTTON).getItem()),
                        Ingredient.fromItem(GemItems.ESSENCE)});
        GameRegistry.addSmelting(Items.DIAMOND, new ItemStack(GemItems.ESSENCE), 0);
    }
}
