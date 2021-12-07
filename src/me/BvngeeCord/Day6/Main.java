package me.BvngeeCord.Day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.BvngeeCord.Util;

public class Main {

    public static void main (String[] args){

        int[] fish = Arrays.stream(Util.getPuzzleInput(6).get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        long[] score = new long[9];
        for (int n : fish){
            score[n] ++;
        }

        System.out.println("part one " + calculateFish(score, 80));
        System.out.println("part two " + calculateFish(score, 256));

    }

    public static int calculateFish(long[] score, int days){

        for (int n=0; n<days; n++){
            System.arraycopy(score, 1, score, 0, score.length-1);

            score[6] += score[0];
            score[8] = score[0];
        }

        return (int) Arrays.stream(score).sum();
    }

}
