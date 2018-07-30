package com.androidbasic.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TwoPlayersActivity extends AppCompatActivity {

    public static final int BOARD_SIZE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        init();
        fillGridView();
    }

    private void init() {
        String player1Name = getIntent().getStringExtra(MainActivity.PLAYER_1_NAME_KEY);
        String player2Name = getIntent().getStringExtra(MainActivity.PLAYER_2_NAME_KEY);
        if (!StringUtils.isBlank(player1Name)) {
            ((TextView) findViewById(R.id.player1TextView)).setText(player1Name);
        }
        if (!StringUtils.isBlank(player2Name)) {
            ((TextView) findViewById(R.id.player2TextView)).setText(player2Name);
        }
    }

    private void fillGridView() {
        GridView gridView = findViewById(R.id.gridView);

        ImageAdapter imageAdapter = new ImageAdapter(TwoPlayersActivity.this);
        gridView.setAdapter(imageAdapter);
    }
}
