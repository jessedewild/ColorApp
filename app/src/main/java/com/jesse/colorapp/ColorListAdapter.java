package com.jesse.colorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ColorListAdapter extends ArrayAdapter {
    List<SavedColor> savedColors;
    LayoutInflater layoutInflater;
    TextView colorCodeView;
    View colorView;

    public ColorListAdapter(Context context, List<SavedColor> objects) {
        super(context, R.layout.activity_color_list, objects);
        savedColors = objects;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.color_list_layout, parent, false);
        }

        colorCodeView = convertView.findViewById(R.id.colorCode);
        colorView = convertView.findViewById(R.id.color);

        SavedColor color = savedColors.get(position);

        String string = String.format("%d Red - %d Green - %d Blue", color.getFirstColorInt(), color.getSecondColorInt(), color.getThirdColorInt());
        colorCodeView.setText(string);
        colorView.setBackgroundColor(android.graphics.Color.rgb(color.getFirstColorInt(), color.getSecondColorInt(), color.getThirdColorInt()));

        return convertView;
    }
}
