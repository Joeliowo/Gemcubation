package com.zozo.gem.entities.bases;

import com.google.common.base.Optional;
import com.zozo.gem.entities.ai.EntityAIFollow;
import com.zozo.gem.items.ItemGem;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.UUID;

enum GemPlacement{
    //Associates text with a number, used for gem placements.
    FOREHEAD,
    LEFT_EYE,
    RIGHT_EYE,
    LEFT_EAR,
    RIGHT_EAR,
    NOSE,
    LEFT_CHEEK,
    RIGHT_CHEEK,
    MOUTH,
    LEFT_SHOULDER,
    RIGHT_SHOULDER,
    CHEST,
    BACK,
    LEFT_HAND,
    RIGHT_HAND,
    LEFT_SIDE,
    RIGHT_SIDE,
    BELLY,
    LEFT_KNEE,
    RIGHT_KNEE
}

enum GemAlignments{
    //Associates text with a number, used for gem alignments.
    SELF,
    PLAYER,
    GEM,
    ENTITY
}

public abstract class EntityGem extends EntityCreature {
    //TODO: Make it more efficient when finding the droppedGemstone.
    public static final DataParameter<Integer> SKIN = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> GEM_PLACEMENT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    public boolean isOwned = false;
    public int movementType = 1;

    public Item droppedGemstone;

    public EntityGem(World worldIn) {
        //Every time the Gem loads into the world.
        super(worldIn);
        this.dataManager.register(EntityGem.SKIN, 0);
        this.dataManager.register(EntityGem.GEM_PLACEMENT, 0);;
        this.dataManager.register(EntityGem.OWNER_ID, Optional.fromNullable(UUID.randomUUID()));
        this.tasks.addTask(4, new com.zozo.gem.entities.ai.EntityAIWander(this, this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
        this.tasks.addTask(4, new EntityAIFollow(this, this.getOwnerID(), this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound){
        //Saves the data on this Gem.
        super.writeEntityToNBT(compound);
        compound.setInteger("skinColor", this.getSkinColor());
        compound.setInteger("gemPlacement", this.getGemPlacement());
        compound.setUniqueId("ownerID", this.getOwnerID());
        compound.setBoolean("isOwned", this.getOwned());
        compound.setInteger("movementType", this.getMovementType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        //Loads this Gems save data.
        super.readEntityFromNBT(compound);
        this.setSkinColor(compound.getInteger("skinColor"));
        this.setGemPlacement(compound.getInteger("gemPlacement"));
        this.setOwnerID(compound.getUniqueId("ownerID"));
        this.setOwned(compound.getBoolean("isOwned"));
        this.setMovementType(compound.getInteger("movementType"));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data){
        //The first time this Gem spawns into the game.
        this.setSkinColor(this.generateSkinColor());
        return super.onInitialSpawn(diff, data);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand){
        //When the player right clicks on the Gem.
        if(!this.world.isRemote){
            if(hand == EnumHand.MAIN_HAND){
                if(!this.getOwned() && !player.isSneaking()){
                    this.setOwnerID(player);
                    player.sendMessage(new TextComponentString("This Gem now belongs to you!"));
                    return super.processInteract(player, hand);
                }
                else if(this.isOwner(player) && this.getOwned()){
                    if(player.isSneaking()){
                        this.cycleMovementAI(player);
                    }
                    else{
                        //Do interaction things.
                    }
                }
                else{
                    return super.processInteract(player, hand);
                }
            }
        }
        return super.processInteract(player, hand);
    }

    public void cycleMovementAI(EntityPlayer player){
        //Cycles through the various movement types.
        this.navigator.clearPath();
        if(this.getMovementType() < 2){
            this.addMovementType(1);
            switch(this.getMovementType()){
                case 1:
                    player.sendMessage(new TextComponentString("This Gem will now wander around."));
                    return;
                case 2:
                    player.sendMessage(new TextComponentString("This Gem will now follow you."));
                    return;
                default:
                    player.sendMessage(new TextComponentString("This Gem will now stay put."));
                    return;
            }
        }
        else if(this.getMovementType() == 2){
            this.setMovementType(0);
            player.sendMessage(new TextComponentString("This Gem will now stay put."));
            return;
        }
    }

    @Override
    public void onDeath(DamageSource source){
        //When the Gem dies.
        if(!this.world.isRemote){
            ItemStack stack = new ItemStack(this.droppedGemstone);
            ((ItemGem) stack.getItem()).setData(this, stack);
            this.entityDropItem(stack, 0.0F);
        }
        super.onDeath(source);
    }

    public boolean isOwner(EntityLivingBase entity){
        //Is this entity the owner of this Gem?
        if(this.getOwnerID().equals(entity.getUniqueID())){
            return true;
        }
        return false;
    }

    public boolean isOwner(UUID uuid){
        //Is this entity UUID the owner of this Gem?
        if(this.getOwnerID().equals(uuid)){
            return true;
        }
        return true;
    }

    /*The method has no use in this foundation class as every Gem has their own color spectrum,
    however it is needed for the extensions of this foundation class (the actual Gems), so it remains Abstract.*/
    public abstract int generateSkinColor();

    public int getSkinColor(){
        //Loads the value for the skin color from the DataParameter and returns that value.
        return this.dataManager.get(EntityGem.SKIN);
    }

    public void setSkinColor(int value){
        //Sets the value contained in the skin color DataParameter.
        this.dataManager.set(EntityGem.SKIN, value);
    }

    public int getGemPlacement(){
        //Loads the value for the gem placement from the DataParameter and returns that value.
        return this.dataManager.get(EntityGem.GEM_PLACEMENT);
    }

    public void setGemPlacement(int value){
        //Sets the value contained in the gem placement DataParameter.
        this.dataManager.set(EntityGem.GEM_PLACEMENT, value);
    }

    public UUID getOwnerID(){
        //Loads te value contained in the owner ID DataParameter and returns the value.
        return dataManager.get(EntityGem.OWNER_ID).get();
    }

    public void setOwnerID(UUID uuid){
        //Sets te value contained in the owner ID DataParameter.
        this.dataManager.set(EntityGem.OWNER_ID, Optional.fromNullable(uuid));
        this.setOwned(true);
    }

    public void setOwnerID(EntityPlayer player){
        //Sets te value contained in the owner ID DataParameter.
        this.dataManager.set(EntityGem.OWNER_ID, Optional.fromNullable(player.getUniqueID()));
        this.setOwned(true);
    }

    public boolean getOwned() {
        //Gets the ownership status variable.
        return this.isOwned;
    }

    public void setOwned(boolean owned) {
        //Sets the ownership status variable.
        this.isOwned = owned;
    }

    public int getMovementType(){
        //Gets the movement type variable.
        return this.movementType;
    }

    public void setMovementType(int value){
        //Sets the movement type variable.
        this.movementType = value;
    }

    public void addMovementType(int value){
        //Adds the value to the movement type variable.
        this.movementType += value;
    }
}
