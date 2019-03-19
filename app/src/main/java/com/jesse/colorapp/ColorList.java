package com.jesse.colorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorList extends AppCompatActivity {
    ArrayList<SavedColor> savedColors;
    ColorListAdapter adapter;
    ListView colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        savedColors = getIntent().getExtras().getParcelableArrayList("savedColors");
        colorList = findViewById(R.id.colorList);

        adapter = new ColorListAdapter(this, savedColors);
        colorList.setAdapter(adapter);
    }
}
