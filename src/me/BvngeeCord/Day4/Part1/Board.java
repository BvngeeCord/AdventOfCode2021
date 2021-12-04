package me.BvngeeCord.Day4.Part1;

import java.util.List;

public class Board {

    private List<List<Integer>> boardState;

    public Board(List<List<Integer>> values){
        boardState = values;
    }

    private void setBoardStateAt(int x, int y, int value){
        boardState.get(y).set(x, value);
    }

    public void replaceAll(int targetNumber, int newValue){
        for (int i=0; i<boardState.toArray().length; i++){
            for (int j=0; j<boardState.get(i).toArray().length; j++){
                if (this.getBoardStateAt(j, i) == targetNumber){
                    System.out.println(i + " " + j);
                    System.out.println(this.getBoardStateAt(j, i));
                    this.setBoardStateAt(j, i, newValue);
                }
            }
        }
    }

    public int getBoardStateAt(int x, int y){
        return boardState.get(y).get(x);
    }

    public int hasWon(){
        //columns
        for (int i=0; i<5; i++){
            boolean winningColumn = true;
            for (int j=0; j<5 && winningColumn; j++){
                if (getBoardStateAt(i, j) != -1) winningColumn = false;
            }
            if (winningColumn) return winningScore();
        }
        //rows
        for (int h=0; h<5; h++){
            boolean winningRow = true;
            for (int g=0; g<5 && winningRow; g++){
                if (getBoardStateAt(g, h) != -1) winningRow = false;
            }
            if (winningRow) return winningScore();
        }
        return -1;
    }

    private int winningScore(){
        int score = 0;
        for (List<Integer> list : boardState){
            for (Integer number : list){
                if (number != -1) score += number;
            }
        }
        return score;
    }

}