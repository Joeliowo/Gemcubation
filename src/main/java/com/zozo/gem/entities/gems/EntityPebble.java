package com.zozo.gem.entities.gems;

import com.zozo.gem.entities.ai.EntityAIFollow;
import com.zozo.gem.entities.bases.EntityGem;
import com.zozo.gem.init.GemItems;
import com.zozo.gem.util.Math;
import com.zozo.gem.world.structures.SchematicLoader;
import com.zozo.gem.world.structures.bases.Schematic;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.io.Console;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class EntityPebble extends EntityGem {
    public static final int SKIN_START = 0x2C242F;
    public static final int SKIN_END = 0x887F92;

    public static final int[] GEM_PLACEMENTS = new int[]{
            //The Gem placements allowed for Pebbles.
        1,2,11,12,13,14,18,19,20
    };

    public EntityPebble(World worldIn) {
        //Every time the Gem loads into the world.
        super(worldIn);
        this.setSize(.2f, .2f);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.tasks.addTask(1, new EntityAISwimming(this));
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand){
        if(!this.world.isRemote) {
            if(this.isOwner(player)) {
                if(player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE)){
                    ItemStack stairs = new ItemStack(Item.getItemFromBlock(Blocks.STONE_STAIRS));
                    this.entityDropItem(stairs, 0f);
                    if(!player.isCreative()){
                        player.getHeldItemMainhand().shrink(1);
                    }
                    return super.processInteract(player, hand);
                }
            }
        }
        return super.processInteract(player, hand);
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return false;
    }

    @Override
    public int[] getGemPlacements(){
        return GEM_PLACEMENTS;
    }

    @Override
    public int generateSkinColor(){
        //Generates the skin color on initial spawn.
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(EntityPebble.SKIN_START);
        colors.add(EntityPebble.SKIN_END);
        return Math.arbiLerp(colors);
    }

    @Override
    public int generateGemColor(){
        //Generates the skin color on initial spawn.
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(EntityPebble.SKIN_START);
        colors.add(EntityPebble.SKIN_END);
        return Math.arbiLerp(colors);
    }
}
