package me.BvngeeCord.Day4.Part1;

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
            File file = new File("C:\\Users\\Jack\\Documents\\GitHub\\test\\src\\me\\BvngeeCord\\Day4\\Part1\\list.txt");
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

        //run game and check for winners after each number
        for (Integer number : numbers){
            for (Board board : boards){
                board.replaceAll(number, -1);
                if (board.hasWon() != -1) {
                    System.out.println("won " + board.hasWon() * number);
                    System.exit(1);
                }
            }
        }
    }
}
