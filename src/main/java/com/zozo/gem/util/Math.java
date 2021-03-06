package com.zozo.gem.util;

import java.util.ArrayList;
import java.util.Random;

public class Math {
    public static Random rand = new Random();

    public static int arbiLerp(ArrayList<Integer> colors) {
        //Smoothly finds a color in the spectrum comprised of the colors in the ArrayList.
        if (colors.size() == 0) {
            return 0;
        }
        if (colors.size() == 1) {
            return colors.get(0);
        }

        int r = 0;
        int g = 0;
        int b = 0;
        float u = rand.nextFloat();

        int bound = rand.nextInt(colors.size() - 1);

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
