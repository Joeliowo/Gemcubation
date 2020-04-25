package com.zozo.gem.entities.gems;

import com.zozo.gem.entities.ai.EntityAIFollow;
import com.zozo.gem.entities.bases.EntityGem;
import com.zozo.gem.init.GemItems;
import com.zozo.gem.util.Math;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class EntityPebble extends EntityGem {
    public static final int SKIN_START = 0x2C242F;
    public static final int SKIN_END = 0x887F92;

    public EntityPebble(World worldIn) {
        //Every time the Gem loads into the world.
        super(worldIn);
        this.setSize(.2f, .2f);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
        this.droppedGemstone = GemItems.PEBBLE_GEM;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand){
        if(!this.world.isRemote) {
            ItemStack stack = player.getHeldItemMainhand();
            if (this.isOwner(player)) {
                if (stack == new ItemStack(Blocks.COBBLESTONE)) {
                    this.entityDropItem(new ItemStack(Blocks.STONE_STAIRS), 0.0F);
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return super.processInteract(player, hand);
                }
            }
        }
        return super.processInteract(player, hand);
    }

    @Override
    public int generateSkinColor(){
        //Generates the skin color on initial spawn.
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(EntityPebble.SKIN_START);
        colors.add(EntityPebble.SKIN_END);
        return Math.arbiLerp(colors);
    }
}
