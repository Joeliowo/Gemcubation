package com.zozo.gem.entities.gems;

import com.zozo.gem.entities.bases.EntityGem;
import com.zozo.gem.util.Math;
import net.minecraft.world.World;

import java.util.ArrayList;

public class EntityPebble extends EntityGem {
    public static final int SKIN_START = 0xBFAEA4;
    public static final int SKIN_END = 0xB6B7CB;

    public EntityPebble(World worldIn) {
        super(worldIn);
    }

    @Override
    public int generateSkinColor(){
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(EntityPebble.SKIN_START);
        colors.add(EntityPebble.SKIN_END);
        return arbiLerp(colors);
    }

    public int arbiLerp(ArrayList<Integer> colors) {
        if (colors.size() == 0) {
            return 0;
        }
        if (colors.size() == 1) {
            return colors.get(0);
        }

        int r = 0;
        int g = 0;
        int b = 0;
        float u = this.rand.nextFloat();

        int bound = this.rand.nextInt(colors.size() - 1);

        int b_r = (colors.get(bound) & 16711680) >> 16;
        int b_g = (colors.get(bound) & 65280) >> 8;
        int b_b = (colors.get(bound) & 255) >> 0;
        int e_r = (colors.get(bound + 1) & 16711680) >> 16;
        int e_g = (colors.get(bound + 1) & 65280) >> 8;
        int e_b = (colors.get(bound + 1) & 255) >> 0;

        r = (int) (u * b_r + (1f - u) * e_r);
        g = (int) (u * b_g + (1f - u) * e_g);
        b = (int) (u * b_b + (1f - u) * e_b);

        return (r << 16) + (g << 8) + b;
    }
}
