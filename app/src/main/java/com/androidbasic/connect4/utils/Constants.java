package com.androidbasic.connect4.utils;

import android.util.SparseIntArray;

import com.androidbasic.connect4.R;

public class Constants {
    public static final String PLAYER_1_NAME_KEY = "player1Name";
    public static final String PLAYER_2_NAME_KEY = "player2Name";
    public static final int FIRST_PLAYER = -1;
    public static final int FIRST_PLAYER_CHECKER = R.drawable.ic_checker_red;
    private static final int FIRST_PLAYER_CHECKER_WIN = R.drawable.ic_checker_red_winner;
    public static final int FIRST_PLAYER_COLOR = R.color.redBackground;
    public static final int SECOND_PLAYER = 1;
    public static final int SECOND_PLAYER_CHECKER = R.drawable.ic_checker_yellow;
    private static final int SECOND_PLAYER_CHECKER_WIN = R.drawable.ic_checker_yellow_winner;
    public static final int SECOND_PLAYER_COLOR = R.color.yellowBackground;
    public static final int BOARD_SIZE = 42;
    public static final SparseIntArray checkers = new SparseIntArray();

    static {
        checkers.put(FIRST_PLAYER_COLOR, FIRST_PLAYER_CHECKER_WIN);
        checkers.put(SECOND_PLAYER_COLOR, SECOND_PLAYER_CHECKER_WIN);
    }
}
