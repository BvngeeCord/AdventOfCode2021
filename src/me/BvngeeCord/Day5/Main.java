package me.BvngeeCord.Day5;

import me.BvngeeCord.Util;

import java.awt.Point;
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
            pointList.add(tempPointList);
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

        System.out.println("part one " + partOne(pointList, grid, false));
        // (since part one only does straight lines and part two only does diagonals, grid doesn't need to be reset)
        System.out.println("part two " + partTwo(pointList, grid, true));

    }

    public static int partOne(List<List<Point>> pointList, List<List<Integer>> grid, boolean visuals){
        //add points to grid
        for (List<Point> twoPoints : pointList){
            if (twoPoints.get(0).getX()==twoPoints.get(1).getX() || twoPoints.get(0).getY()==twoPoints.get(1).getY()){
                boolean vertical = twoPoints.get(0).getX() == twoPoints.get(1).getX();

                int max = vertical ? (int)Math.max(twoPoints.get(0).getY(), twoPoints.get(1).getY()) : (int)Math.max(twoPoints.get(0).getX(), twoPoints.get(1).getX());
                int min = vertical ? (int)Math.min(twoPoints.get(0).getY(), twoPoints.get(1).getY()) : (int)Math.min(twoPoints.get(0).getX(), twoPoints.get(1).getX());

                for (int n=min; n<=max; n++){
                    if (vertical) {
                        grid.get(n).set((int)twoPoints.get(0).getX(), grid.get(n).get((int)twoPoints.get(0).getX())+1);
                    } else {
                        grid.get((int)twoPoints.get(0).getY()).set(n, grid.get((int)twoPoints.get(0).getY()).get(n)+1);
                    }
                }
            }
        }

        //visuals for testing
        if (visuals) visuals(grid);

        //figure out the number of 2+ overlaps in the grid
        return calculateOverlaps(grid);
    }

    public static int partTwo(List<List<Point>> pointList, List<List<Integer>> grid, boolean visuals){
        //add points to grid
        for (List<Point> twoPoints : pointList){
            final int x0 = (int)twoPoints.get(0).getX();
            final int x1 = (int)twoPoints.get(1).getX();
            final int y0 = (int)twoPoints.get(0).getY();
            final int y1 = (int)twoPoints.get(1).getY();
            if (x0 != x1 && y0 != y1){
                boolean positive = (y1-y0)/(x1-x0) > 0;
                for (int i = 0; i <= Math.abs(twoPoints.get(0).getX()-twoPoints.get(1).getX()); i++) {
                    final int n = positive ? Math.min(x0, x1) + i : Math.max(x0, x1) - i;
                    grid.get(Math.min(y0, y1) + i).set(n, grid.get(Math.min(y0, y1) + i).get(n) + 1);
                }
            }
        }

        //visuals for testing
        if (visuals) visuals(grid);

        //figure out the number of 2+ overlaps in the grid
        return calculateOverlaps(grid);
    }

    public static void visuals(List<List<Integer>> grid){
        for (List<Integer> row : grid){
            StringBuilder temp = new StringBuilder();
            for (int number : row){
                temp.append(number).append(" ");
            }
            System.out.println(temp);
        }
    }

    public static int calculateOverlaps(List<List<Integer>> grid){
        int overlaps = 0;
        for (List<Integer> row : grid) for (int num : row){
            if (num >= 2) overlaps++;
        }
        return overlaps;
    }

}