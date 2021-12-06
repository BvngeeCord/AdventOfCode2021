package me.BvngeeCord.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        try{
            File file = new File("C:\\Users\\Jack\\Documents\\GitHub\\AdventOfCode\\src\\me\\BvngeeCord\\Day4\\list.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
            System.out.println(list);

        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }

        List<Integer> numbers = Arrays.stream(list.get(0).split(",")).toList().stream().map(Integer::parseInt).toList();
        System.out.println(numbers);

        ArrayList<Board> boards = new ArrayList<>(5);

        List<List<Integer>> tempList = new ArrayList<>(5);

        for (int n=2; n<list.toArray().length;n++){

            if (list.get(n).toCharArray().length>1){
                tempList.add(new ArrayList<>(Arrays.stream(list.get(n).split(" ")).toList().stream().filter(item -> !item.equals("")).map(Integer::parseInt).toList()));
            } else {
                boards.add(new Board(tempList));
                tempList = new ArrayList<>(5);
            }

        }
        System.out.println("part one: " + partOne(numbers, boards));
        System.out.println("part two: " + partTwo(numbers, boards));

    }

    public static int partOne(List<Integer> numbers, List<Board> boards){
        for (Integer number : numbers){
            for (Board board : boards){
                board.replaceAll(number, -1);
                if (board.hasWon() != -1) {
                    return board.hasWon() * number;
                }
            }
        }
        return -1;
    }

    public static int partTwo(List<Integer> numbers, List<Board> boards){
        int i = 0;
        int num = 0;
        for (Integer number : numbers){
            for (Board board : boards){
                boolean isDone = false;
                for (int n = 0; n < 5 && !isDone; n++) for (int h = 0; h < 5 && !isDone; h++) if (board.getBoardStateAt(n, h) == -2) isDone = true;
                if (!isDone) board.replaceAll(number, -1);
                if (board.hasWon() != -1) {
                    board.replaceAll(-1, -2);
                    i = boards.indexOf(board);
                    num = number;
                }
            }
        }
        boards.get(i).replaceAll(-2, -1);
        return boards.get(i).hasWon() * num;
    }
}