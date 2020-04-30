package com.zozo.gem.world.structures;

import com.zozo.gem.init.GemBlocks;
import com.zozo.gem.world.structures.bases.Schematic;
import com.zozo.gem.world.structures.bases.Structure;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Random;

public class EssenceShrine extends Structure {

    public EssenceShrine(String path) {
        super();
        this.structure = SchematicLoader.loadSchematic("/assets/gem/structures/" + path + ".schematic", path);
        this.biomes.add(Biomes.FOREST);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos){
        if(rand.nextInt(500) < 1){
            return super.generate(world, rand, pos.down());
        }
        return false;
    }
}
