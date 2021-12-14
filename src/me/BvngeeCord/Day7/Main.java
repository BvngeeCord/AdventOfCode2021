package me.BvngeeCord.Day7;

import me.BvngeeCord.Util;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        int[] input = Arrays.stream(Util.getPuzzleInput(7).get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Part one: " + partOne(input, Arrays.stream(input).max().getAsInt()));
        System.out.println("Part two: " + partTwo(input, Arrays.stream(input).max().getAsInt()));

    }

    public static int partOne(int[] input, int max){
        int bestPos = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++){
            int fuelUsed = 0;
            for (int j : input) {
                fuelUsed += Math.abs(j - i);
            }
            System.out.println(fuelUsed);
            if (fuelUsed < bestPos) bestPos = fuelUsed;
        }
        return bestPos;
    }

    public static int partTwo(int[] input, int max) {
        int bestPos = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            int fuelUsed = 0;
            for (int j : input) {
                fuelUsed += (Math.abs(i - j) * Math.abs(i - j)+1) / 2;
                System.out.println(i + " " + j + " " + (Math.abs(i - j) * Math.abs(i - j)+1) / 2);
            }
            System.out.println(fuelUsed);
            if (fuelUsed < bestPos) bestPos = fuelUsed;
        }
        return bestPos;
    }

    }
