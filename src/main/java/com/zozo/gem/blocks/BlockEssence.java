package com.zozo.gem.blocks;

import com.zozo.gem.init.GemBlocks;
import com.zozo.gem.init.GemItems;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockEssence extends BlockFluidClassic {
    EntityItem button;

    public BlockEssence(Fluid fluid, Material mat, MapColor color) {
        super(fluid, mat, color);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if(!world.isRemote) {
            if (entity instanceof EntityItem) {
                if ((((EntityItem) entity).getItem()).getItem() == Item.getItemFromBlock(Blocks.STONE_BUTTON) && (EntityItem) entity != this.button) {
                    this.button = (EntityItem)entity;
                    ItemStack peebis = new ItemStack(GemItems.PEBBLE_GEM);
                    EntityItem pebble = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, peebis);
                    entity.world.spawnEntity(pebble);
                    entity.setDead();
                    return;
                }
            }
        }
    }
}
