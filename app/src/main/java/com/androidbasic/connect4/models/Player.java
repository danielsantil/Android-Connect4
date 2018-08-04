package com.androidbasic.connect4.models;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.androidbasic.connect4.adapters.ImageAdapter;

import java.util.Arrays;
import java.util.List;

public class Player {

    private static final int ROW = 6;
    private static final int COL = 7;

    private String name;
    private int checker;
    private int color;
    private int turn;
    private int[][] plays = new int[ROW][COL];
    private int playsCounter = 0;
    private List<Integer> winPositions;

    public Player(String name, int checker, int color, int turn) {
        this.name = name;
        this.checker = checker;
        this.color = color;
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getTurn() {
        return turn;
    }

    public List<Integer> getWinPositions() {
        return winPositions;
    }

    public void play(int position, ImageAdapter imageAdapter, ImageView turnArrow, Context
            context) {
        turnArrow.setColorFilter(ContextCompat.getColor(context, color),
                android.graphics.PorterDuff.Mode.SRC_IN);
        imageAdapter.setNewVector(position, checker);

        int counter = 0;

        rowFor:
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (counter == position) {
                    plays[row][col] = 1;
                    playsCounter++;
                    break rowFor;
                } else {
                    counter++;
                }
            }
        }
        Log.d("Player " + name, Arrays.deepToString(plays).replace("], ", "]\n"));
    }

    public boolean checkWin() {
        if (playsCounter < 4) {
            return false;
        }
        int pos1, pos2, pos3, pos4;
        int counter = 0;

        //check vertical match
        for (int row = 0; row < ROW - 3; row++) {
            for (int col = 0; col < COL; col++) {
                if (plays[row][col] == 1
                        && plays[row + 1][col] == 1
                        && plays[row + 2][col] == 1
                        && plays[row + 3][col] == 1) {
                    pos1 = counter;
                    pos2 = pos1 + 7; // plus 7 because we're counting jumps between rows
                    pos3 = pos2 + 7;
                    pos4 = pos3 + 7;
                    winPositions = Arrays.asList(pos1, pos2, pos3, pos4);
                    return true;
                } else {
                    counter++;
                }
            }
        }
        counter = 0; // reset the counter

        // check horizontal match
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL - 3; col++) {
                if (plays[row][col] == 1
                        && plays[row][col + 1] == 1
                        && plays[row][col + 2] == 1
                        && plays[row][col + 3] == 1) {
                    pos1 = counter;
                    pos2 = pos1 + 1; // we look for adjacent positions
                    pos3 = pos2 + 1;
                    pos4 = pos3 + 1;
                    winPositions = Arrays.asList(pos1, pos2, pos3, pos4);
                    return true;
                } else {
                    counter++;
                }
            }
            counter += 3; // we need to count the columns left behind in condition (col < COL-3)
        }
        counter = 0; // reset the counter

        // check for descending diagonal
        for (int row = 0; row < ROW - 3; row++) {
            for (int col = 0; col < COL - 3; col++) {
                if (plays[row][col] == 1
                        && plays[row + 1][col + 1] == 1
                        && plays[row + 2][col + 2] == 1
                        && plays[row + 3][col + 3] == 1) {
                    pos1 = counter;
                    pos2 = pos1 + 8; // we look for positions below plus 1 position
                    pos3 = pos2 + 8;
                    pos4 = pos3 + 8;
                    winPositions = Arrays.asList(pos1, pos2, pos3, pos4);
                    return true;
                } else {
                    counter++;
                }
            }
            counter += 3; // we need to count the columns left behind in condition (col < COL-3)
        }

        counter = 21; // we start from 21 to compensate initialization of the next for at row = 3
        // check for ascending diagonal
        for (int row = 3; row < ROW; row++) {
            for (int col = 0; col < COL - 3; col++) {
                if (plays[row][col] == 1
                        && plays[row - 1][col + 1] == 1
                        && plays[row - 2][col + 2] == 1
                        && plays[row - 3][col + 3] == 1) {
                    pos1 = counter;
                    pos2 = pos1 - 6; // we look for positions above minus 1 position
                    pos3 = pos2 - 6;
                    pos4 = pos3 - 6;
                    winPositions = Arrays.asList(pos1, pos2, pos3, pos4);
                    return true;
                } else {
                    counter++;
                }
            }
            counter += 3; // we need to count the columns left behind in condition (col < COL-3)
        }

        return false;
    }

}
