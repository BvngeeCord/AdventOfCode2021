package me.BvngeeCord.Day6;

import java.util.Arrays;

import me.BvngeeCord.Util;

public class Main {

    public static void main (String[] args){

        int[] fish = Arrays.stream(Util.getPuzzleInput(6).get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        long[] score = new long[9];
        for (int n : fish){
            score[n] ++;
        }

        System.out.println("part one " + calculateFish(score, 80));

        score = new long[9];
        for (int n : fish){
            score[n] ++;
        }

        System.out.println("part two " + calculateFish(score, 256));

        System.out.println(score[0]);
    }

    public static long calculateFish(long[] score, int days){

        for (int n=0; n<days; n++){
            score = new long[]{score[1], score[2], score[3], score[4], score[5], score[6], score[7]+ score[0], score[8], score[0]};
        }

        return Arrays.stream(score).sum();
    }

}
