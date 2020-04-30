package com.zozo.gem.world.structures.bases;

import com.zozo.gem.world.structures.SchematicLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Structure extends WorldGenerator {
    public ArrayList<Biome> biomes = new ArrayList<>();
    public Schematic structure;

    public Structure(){

    }

    public boolean checkIsBiome(World world, BlockPos pos) {
        if (this.biomes.isEmpty()) {
            return true;
        }
        Biome biome = world.getBiome(pos);
        if (this.biomes.contains(biome)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if(!this.checkIsBiome(worldIn, position)) {
            return false;
        }
        return SchematicLoader.spawnSchematic(this.structure, worldIn, position);
    }
}
