package com.androidbasic.connect4.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbasic.connect4.R;
import com.androidbasic.connect4.adapters.ImageAdapter;
import com.androidbasic.connect4.models.Player;
import com.androidbasic.connect4.utils.Constants;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class TwoPlayersActivity extends BaseActivity {

    private ImageAdapter imageAdapter;
    private ImageView turnArrow;
    private Button restartButton;
    private TextView textWinner;
    private Player player1;
    private Player player2;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        init();
    }

    private void init() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String player1Name = getIntent().getStringExtra(Constants.PLAYER_1_NAME_KEY);
        String player2Name = getIntent().getStringExtra(Constants.PLAYER_2_NAME_KEY);
        TextView player1Text = findViewById(R.id.player1TextView);
        TextView player2Text = findViewById(R.id.player2TextView);

        if (!StringUtils.isBlank(player1Name)) {
            player1Text.setText(player1Name);
        } else {
            player1Name = player1Text.getText().toString();
        }
        if (!StringUtils.isBlank(player2Name)) {
            player2Text.setText(player2Name);
        } else {

            player2Name = player2Text.getText().toString();
        }

        player1 = new Player(player1Name, Constants.FIRST_PLAYER_CHECKER, Constants
                .FIRST_PLAYER_COLOR, Constants.FIRST_PLAYER);
        player2 = new Player(player2Name, Constants.SECOND_PLAYER_CHECKER, Constants
                .SECOND_PLAYER_COLOR, Constants.SECOND_PLAYER);

        turnArrow = findViewById(R.id.turnChooser);
        turnArrow.setVisibility(View.VISIBLE);
        chooseFirstToPlay();

        imageAdapter = new ImageAdapter(TwoPlayersActivity.this);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);

        textWinner = findViewById(R.id.textWinner);
        textWinner.setVisibility(View.INVISIBLE);

        restartButton = findViewById(R.id.restartGame);
        restartButton.setOnClickListener(this);
        restartButton.setVisibility(View.INVISIBLE);
    }

    // Adapter onClick
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id == R.drawable.ic_checker_selectable) {
            placeChecker(position);
        }
    }

    // Button onClick
    @Override
    public void onClick(View v) {
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showBackConfirmation();
    }

    private void showBackConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TwoPlayersActivity.this);
        builder.setTitle(getResources().getString(R.string.connect_4_title))
                .setMessage("Are you sure you wanna run?")
                .setPositiveButton("Sure!", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("Wait, not yet", (DialogInterface dialog, int which)
                        -> dialog.dismiss())
                .create().show();
    }

    private void chooseFirstToPlay() {
        Random random = new Random();
        int randomValue = random.nextInt(11);
        Player chosen = randomValue % 2 == 0 ? player1 : player2;

        turnArrow.setColorFilter(ContextCompat.getColor(TwoPlayersActivity.this, chosen.getColor()),
                android.graphics.PorterDuff.Mode.SRC_IN);
        turnArrow.setScaleX(chosen.getTurn());
        current = chosen.getTurn();
    }

    private void placeChecker(int position) {
        Player player = playerInTurn();
        player.play(position, imageAdapter, turnArrow, TwoPlayersActivity.this);
        boolean win = player.checkWin();
        if (win) {
            showWinner(player);
            imageAdapter.setWinner(player.getWinPositions(), player.getColor());
        }
        changeTurn();
    }

    private void changeTurn() {
        if (current == player1.getTurn()) {
            current = player2.getTurn();
        } else {
            current = player1.getTurn();
        }

        turnArrow.setScaleX(playerInTurn().getTurn());
        turnArrow.setColorFilter(ContextCompat.getColor(TwoPlayersActivity.this,
                playerInTurn().getColor()), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private Player playerInTurn() {
        if (current == player1.getTurn()) {
            return player1;
        } else {
            return player2;
        }
    }

    private void showWinner(@NonNull Player player) {
        String text = getResources().getString(R.string.winner) + " " + player.getName();

        textWinner.setText(text);
        textWinner.setTextColor(getResources().getColor(player.getColor()));
        textWinner.setVisibility(View.VISIBLE);
        turnArrow.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.VISIBLE);
    }
}
