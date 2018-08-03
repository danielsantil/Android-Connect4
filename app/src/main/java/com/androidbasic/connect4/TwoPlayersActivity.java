package com.androidbasic.connect4;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class TwoPlayersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageAdapter imageAdapter;
    private ImageView turnArrow;
    private int player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        init();

        turnArrow = findViewById(R.id.turnChooser);
        chooseFirstToPlay();

        imageAdapter = new ImageAdapter(TwoPlayersActivity.this);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);
    }

    private void init() {
        String player1Name = getIntent().getStringExtra(Constants.PLAYER_1_NAME_KEY);
        String player2Name = getIntent().getStringExtra(Constants.PLAYER_2_NAME_KEY);
        if (!StringUtils.isBlank(player1Name)) {
            ((TextView) findViewById(R.id.player1TextView)).setText(player1Name);
        }
        if (!StringUtils.isBlank(player2Name)) {
            ((TextView) findViewById(R.id.player2TextView)).setText(player2Name);
        }
    }

    private void chooseFirstToPlay() {
        Random random = new Random();
        int turn = random.nextInt(11);
        int color;
        if (turn % 2 == 0) {
            player = Constants.FIRST_PLAYER;
            color = Constants.FIRST_PLAYER_COLOR;
        } else {
            player = Constants.SECOND_PLAYER;
            color = Constants.SECOND_PLAYER_COLOR;
        }
        turnArrow.setColorFilter(ContextCompat.getColor(TwoPlayersActivity.this, color),
                android.graphics.PorterDuff.Mode.SRC_IN);
        turnArrow.setScaleX(player);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id == R.drawable.ic_checker_selectable) {
            placeChecker(position);
        }
    }

    private void placeChecker(int position) {
        int checker, color;
        if (player == Constants.FIRST_PLAYER) {
            checker = Constants.FIRST_PLAYER_CHECKER;
            color = Constants.FIRST_PLAYER_COLOR;
        } else {
            checker = Constants.SECOND_PLAYER_CHECKER;
            color = Constants.SECOND_PLAYER_COLOR;
        }
        turnArrow.setColorFilter(ContextCompat.getColor(TwoPlayersActivity.this, color),
                android.graphics.PorterDuff.Mode.SRC_IN);
        imageAdapter.setNewVector(position, checker);
        imageAdapter.notifyDataSetChanged();
        changeTurn();
    }

    private void changeTurn() {
        int color;
        if (player == Constants.FIRST_PLAYER) {
            player = Constants.SECOND_PLAYER;
            color = Constants.SECOND_PLAYER_COLOR;
        } else {
            player = Constants.FIRST_PLAYER;
            color = Constants.FIRST_PLAYER_COLOR;
        }
        turnArrow.setScaleX(player);
        turnArrow.setColorFilter(ContextCompat.getColor(TwoPlayersActivity.this, color),
                android.graphics.PorterDuff.Mode.SRC_IN);
    }

}
