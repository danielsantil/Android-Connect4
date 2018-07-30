package com.androidbasic.connect4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private Integer[] vectorIds = {
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_selectable
    };

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return TwoPlayersActivity.BOARD_SIZE;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        // if position represents the last row, draw checker_selectable, else draw checker_empty
        position = position >= 35 ? 1 : 0;
        imageView.setImageResource(vectorIds[position]);
        return imageView;
    }
}
