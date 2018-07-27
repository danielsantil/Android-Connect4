package com.androidbasic.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.onePlayerButton).setOnClickListener(this);
        findViewById(R.id.twoPlayersButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onePlayerButton:
                Toast.makeText(this, "Available soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.twoPlayersButton:
                Toast.makeText(this, "Not available yet", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
