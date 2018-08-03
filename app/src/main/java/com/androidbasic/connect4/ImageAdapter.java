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
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_empty,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable,
            R.drawable.ic_checker_selectable
    };

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setNewVector(int position, int res) {
        vectorIds[position] = res;
        int newAvailablePosition = position - 7;
        if (newAvailablePosition >= 0) {
            vectorIds[newAvailablePosition] = R.drawable.ic_checker_selectable;
        }
    }

    @Override
    public int getCount() {
        return Constants.BOARD_SIZE;
    }

    @Override
    public Object getItem(int position) {
        return vectorIds[position];
    }

    @Override
    public long getItemId(int position) {
        return vectorIds[position];
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

        imageView.setImageResource(vectorIds[position]);
        return imageView;
    }
}
