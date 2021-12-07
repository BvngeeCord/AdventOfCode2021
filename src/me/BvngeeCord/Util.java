package me.BvngeeCord;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    public static ArrayList<String> getPuzzleInput(int day){
        ArrayList<String> list = new ArrayList<>();
        try{
            //File file = new File("C:\\Users\\Jack\\Documents\\GitHub\\AdventOfCode\\src\\me\\BvngeeCord\\Day" + day + "\\list.txt");
            File file = new File("src/me/BvngeeCord/Day" + day + "/list.txt");
            System.out.println(file);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        return list;
    }

}
