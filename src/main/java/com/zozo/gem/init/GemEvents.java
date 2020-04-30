package com.zozo.gem.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class GemEvents {

    /*@SubscribeEvent
    public void onItemCollideWithBlock(TickEvent.WorldTickEvent event){
        World world = event.world;
        for(Entity entity : world.getLoadedEntityList()){
            if(entity instanceof EntityItem){
                ItemStack stack = ((EntityItem) entity).getItem();
                ItemStack button = new ItemStack(Blocks.STONE_BUTTON);
                if(stack.getItem() == button.getItem()){
                    ItemStack pebble = new ItemStack(GemItems.PEBBLE_GEM);
                    EntityItem gem = new EntityItem(world, entity.posX, entity.posY + 1, entity.posZ, pebble);
                    entity.setDead();
                    world.spawnEntity(gem);
                }
            }
        }
    }*/
}
