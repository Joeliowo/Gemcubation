package com.zozo.gem.items;

import com.zozo.gem.GemC;
import com.zozo.gem.entities.bases.EntityGem;
import com.zozo.gem.entities.gems.EntityPebble;
import com.zozo.gem.init.GemCreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ItemGem extends Item {
    //TODO: GIVE THIS SCRIPT IT'S COMMENTS.
    public String name;

    public ItemGem(String name){
        this.setUnlocalizedName(name + "_gem");
        this.setMaxStackSize(1);
        this.setCreativeTab(GemCreativeTabs.CREATIVE_TAB_GOOD_GEMS);
        this.name = name;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(!worldIn.isRemote){
            ItemStack stack = playerIn.getHeldItem(hand);
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
            if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();
                if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, stack)) {
                    boolean spawned = this.formGem(worldIn, playerIn, blockpos, stack);
                    if (!playerIn.capabilities.isCreativeMode && spawned) {
                        stack.shrink(1);
                    }
                }
            }
        }
        return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    public boolean formGem(World world, EntityPlayer player, BlockPos pos, ItemStack stack) {
        if(!world.isRemote) {
            EntityGem gem = new EntityPebble(world);
            String namee = this.getUnlocalizedName().replaceAll("^item\\.(cracked_)*", "").replaceAll("_(\\d*_)*gem$", "");
            try {
                Class<EntityGem> geml = (Class<EntityGem>) GemC.class.getClassLoader().loadClass("com/zozo/gem/entities/gems/Entity" + namee.substring(0, 1).toUpperCase() + namee.substring(1));
                gem = (EntityGem) geml.getConstructors()[0].newInstance(world);
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            try {
                gem.readFromNBT(stack.getTagCompound());
            } catch (Exception e){
                gem.onInitialSpawn(world.getDifficultyForLocation(pos), null);
            }
            gem.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            gem.setHealth(gem.getMaxHealth());
            gem.setAttackTarget(null);
            gem.extinguish();
            gem.getNavigator().clearPath();
            gem.clearActivePotions();
            world.spawnEntity(gem);
            return true;
        }
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entity) {
        entity.setNoDespawn();
        entity.isDead = false;
        entity.setEntityInvulnerable(true);
        entity.extinguish();
        return false;
    }

    public void setData(EntityGem host, ItemStack stack) {
        stack.setTagCompound(host.writeToNBT(new NBTTagCompound()));
        stack.getTagCompound().setString("name", host.getName());
    }

    public void clearData(ItemStack stack) {
        stack.setTagCompound(new NBTTagCompound());
    }
}
