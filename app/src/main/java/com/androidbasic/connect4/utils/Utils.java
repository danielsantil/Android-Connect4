package com.androidbasic.connect4.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.androidbasic.connect4.R;

public class Utils {

    public static void showBackConfirmation(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.connect_4_title))
                .setMessage("Are you sure you wanna run?")
                .setPositiveButton("Sure!", (dialog, which) -> activity.onBackPressed())
                .setNegativeButton("Wait, not yet", (DialogInterface dialog, int which)
                        -> dialog.dismiss())
                .create().show();
    }
}
