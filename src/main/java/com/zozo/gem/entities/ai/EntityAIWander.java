package com.zozo.gem.entities.ai;

import com.zozo.gem.entities.bases.EntityGem;
import net.minecraft.entity.EntityCreature;

public class EntityAIWander extends net.minecraft.entity.ai.EntityAIWander {
    public EntityCreature owned;

    public EntityAIWander(EntityCreature creatureIn, double speedIn) {
        super(creatureIn, speedIn);
        if(creatureIn instanceof EntityGem){
            this.owned = creatureIn;
        }
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute(){
        return ((EntityGem)owned).getMovementType() == 1 && super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting(){
        return super.shouldContinueExecuting();
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
    }

    @Override
    public void makeUpdate(){
        super.makeUpdate();
    }
}
