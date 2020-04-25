package com.zozo.gem.entities.ai;

import com.zozo.gem.entities.bases.EntityGem;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class EntityAIFollow extends EntityAIBase {
    public EntityGem owned;
    public EntityPlayer givenOwner;
    public UUID givenOwnerID;
    public double speed;

    public EntityPlayer owner;

    public EntityAIFollow(EntityGem owned, EntityPlayer owner, double speed){
        this.owned = owned;
        this.givenOwner = owner;
        this.speed = speed;
        this.setMutexBits(3);
    }

    public EntityAIFollow(EntityGem owned, UUID owner, double speed){
        this.owned = owned;
        this.givenOwnerID = owner;
        this.speed = speed;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        List<EntityPlayer> list = this.owned.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.owned.getEntityBoundingBox().grow(24.0D, 8.0D, 24.0D));
        double maxDistance = Double.MAX_VALUE;
        for (EntityPlayer player : list) {
            if (!player.isSpectator() || !player.isInvisible() && this.owned.isOwner(player.getUniqueID())) {
                double newDistance = player.getDistanceSq(this.owned);
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.owner = player;
                }
            }
        }
        return this.owner != null && this.owned.getMovementType() == 2;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.owner != null && !this.owned.getNavigator().noPath();
    }

    @Override
    public void startExecuting(){
        super.startExecuting();
        this.owned.setPathPriority(PathNodeType.WATER, 0);
    }

    @Override
    public void updateTask() {
        if (this.owned.getDistanceSq(this.owner) > this.owner.width * 4) {
            this.owned.getNavigator().tryMoveToEntityLiving(this.owner, this.speed);
        }
    }

    @Override
    public void resetTask() {
        this.owner = null;
        this.owned.getNavigator().clearPath();
        this.owned.setPathPriority(PathNodeType.WATER, 0);
    }
}
