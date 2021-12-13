package me.BvngeeCord.Day7;

import me.BvngeeCord.Util;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        int[] input = Arrays.stream(Util.getPuzzleInput(7).get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        System.out.println(sum/input.length);

    }

}
