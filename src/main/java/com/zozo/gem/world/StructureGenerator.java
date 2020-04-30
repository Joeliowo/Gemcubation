package com.zozo.gem.world;

import com.zozo.gem.world.structures.EssenceShrine;
import com.zozo.gem.world.structures.bases.Structure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class StructureGenerator implements IWorldGenerator {
    public ArrayList<Structure> structures = new ArrayList<Structure>();

    public StructureGenerator() {
        this.structures.add(new EssenceShrine("shrine"));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        for (Structure structure : this.structures) {
            this.runGenerator(structure, world, random, chunkX, chunkZ, 1);
        }
    }

    public void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn) {
        for (int i = 0; i < chancesToSpawn; ++i) {
            int x = chunkX * 16 + rand.nextInt(16);// + 8;
            int z = chunkZ * 16 + rand.nextInt(16);// + 8;
            int y = world.getHeight(x, z);
            generator.generate(world, rand, world.getTopSolidOrLiquidBlock(new BlockPos(x, y, z)));
        }
    }
}
