package com.androidbasic.connect4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PLAYER_1_NAME_KEY = "player1Name";
    public static final String PLAYER_2_NAME_KEY = "player2Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.twoPlayersButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showPlayersDialog();
    }

    private void showPlayersDialog() {
        View view = getLayoutInflater().inflate(R.layout.players_name_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Two Players")
                .setMessage("Enter your names")
                .setPositiveButton("Start", (dialog, which) -> {
                    EditText player1Edit = view.findViewById(R.id.editTextPlayerName1);
                    EditText player2Edit = view.findViewById(R.id.editTextPlayerName2);

                    Intent intent = new Intent(MainActivity.this, TwoPlayersActivity.class);
                    intent.putExtra(PLAYER_1_NAME_KEY, player1Edit.getText().toString());
                    intent.putExtra(PLAYER_2_NAME_KEY, player2Edit.getText().toString());
                    startActivity(intent);
                })
                .setNegativeButton("Go back", (dialog, which) -> dialog.dismiss())
                .setView(view);
        builder.create().show();
    }
}
