package com.zozo.gem.entities.bases;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGem extends EntityLiving {
    public static final DataParameter<Integer> SKIN = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);

    public EntityGem(World worldIn) {
        super(worldIn);
        this.dataManager.register(EntityGem.SKIN, 0);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data){
        this.setSkinColor(this.generateSkinColor());
        return super.onInitialSpawn(diff, data);
    }

    public int generateSkinColor(){
        return 0;
    }

    public int getSkinColor(){
        return this.dataManager.get(EntityGem.SKIN);
    }

    public void setSkinColor(int value){
        this.dataManager.set(EntityGem.SKIN, value);
    }
}
