package com.zozo.gem.world.structures.bases;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

public class Schematic {
    public String name;
    public int width;
    public int height;
    public int length;
    public Map<BlockPos, IBlockState> structureBlocks;
    public NBTTagList tileEntities;
    public NBTTagList entities;

    public Schematic(String name, int width, int height, int length, Map<BlockPos, IBlockState> structureBlocks, NBTTagList tileEntities, NBTTagList entities) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.length = length;
        this.structureBlocks = structureBlocks;
        this.tileEntities = tileEntities;
        this.entities = entities;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<BlockPos, IBlockState> getStructureBlocks() {
        return structureBlocks;
    }

    public void setStructureBlocks(Map<BlockPos, IBlockState> structureBlocks) {
        this.structureBlocks = structureBlocks;
    }

    public NBTTagList getTileEntities() {
        return tileEntities;
    }

    public void setTileEntities(NBTTagList tileEntities) {
        this.tileEntities = tileEntities;
    }

    public NBTTagList getEntities() {
        return entities;
    }

    public void setEntities(NBTTagList entities) {
        this.entities = entities;
    }
}
