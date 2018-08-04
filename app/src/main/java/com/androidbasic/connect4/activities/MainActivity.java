package com.androidbasic.connect4.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.androidbasic.connect4.R;
import com.androidbasic.connect4.utils.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


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

    @SuppressLint("InflateParams")
    private void showPlayersDialog() {
        View view = getLayoutInflater().inflate(R.layout.players_name_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Two Players")
                .setMessage("Enter your names")
                .setPositiveButton("Start", (dialog, which) -> {
                    EditText player1Edit = view.findViewById(R.id.editTextPlayerName1);
                    EditText player2Edit = view.findViewById(R.id.editTextPlayerName2);

                    Intent intent = new Intent(MainActivity.this, TwoPlayersActivity.class);
                    intent.putExtra(Constants.PLAYER_1_NAME_KEY, player1Edit.getText().toString());
                    intent.putExtra(Constants.PLAYER_2_NAME_KEY, player2Edit.getText().toString());
                    startActivity(intent);
                })
                .setNegativeButton("Go back", (dialog, which) -> dialog.dismiss())
                .setView(view);
        builder.create().show();
    }
}
