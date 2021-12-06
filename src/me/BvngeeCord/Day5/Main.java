package me.BvngeeCord.Day5;

import me.BvngeeCord.Util;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        //read input and setup list of points
        ArrayList<String> list = Util.getPuzzleInput(5);
        List<List<Point>> pointList = new ArrayList<>();
        list.forEach(line -> {
            List<Point> tempPointList = new ArrayList<>();
            tempPointList.add(new Point(Integer.parseInt(line.split(" -> ")[0].split(",")[0]), Integer.parseInt(line.split(" -> ")[0].split(",")[1])));
            tempPointList.add(new Point(Integer.parseInt(line.split(" -> ")[1].split(",")[0]), Integer.parseInt(line.split(" -> ")[1].split(",")[1])));
            if (tempPointList.get(0).getX() == tempPointList.get(1).getX() || tempPointList.get(0).getY() == tempPointList.get(1).getY()) {
                pointList.add(tempPointList);
            }
        });

        //setup grid (technically could be smaller but im lazy)
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < 999; j++) {
                tempList.add(0);
            }
            grid.add(tempList);
        }

        //add points to grid
        for (List<Point> twoPoints : pointList){

            boolean verticle = twoPoints.get(0).getX() == twoPoints.get(1).getX();
            Method methodType = twoPoints.get(0).getClass().getDeclaredMethod(verticle ? "getY" : "getX");

            int max = (int)Math.max((double) methodType.invoke(twoPoints.get(0)), (double)methodType.invoke(twoPoints.get(1)));
            int min = (int)Math.min((double)methodType.invoke(twoPoints.get(0)), (double)methodType.invoke(twoPoints.get(1)));

            if (twoPoints.get(0).getX()==twoPoints.get(1).getX() && twoPoints.get(0).getY()==twoPoints.get(1).getY()) System.out.println("yes");

            for (int n=min; n<=max; n++){
                if (verticle) {
                    grid.get(n).set((int)twoPoints.get(0).getX(), grid.get(n).get((int)twoPoints.get(0).getX())+1);
                } else {
                    grid.get((int)twoPoints.get(0).getY()).set(n, grid.get((int)twoPoints.get(0).getY()).get(n)+1);
                }
            }
        }

        //visuals for testing
        for (List<Integer> row : grid){
            StringBuilder temp = new StringBuilder();
            for (int number : row){
                temp.append(number + " ");
            }
            System.out.println(temp);
        }

        //figure out the number of 2+ overlaps in the grid
        int overlaps = 0;
        for (List<Integer> row : grid) for (int num : row){
            if (num >= 2) overlaps++;
        }

        System.out.println(overlaps);
    }

}