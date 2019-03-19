package com.jesse.colorapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText redValue, greenValue, blueValue;
    View redColor, greenColor, blueColor, colorView;
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int[] currentColorValues; // [red][green][blue]
    ArrayList<SavedColor> savedColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savedColors = new ArrayList<>();
        setComponents();

        /**
         * SeekBar methods
         */
        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redSeekBar.setProgress(progress);
                currentColorValues[0] = progress;
                redValue.setText(String.valueOf(currentColorValues[0]));
                setAllColorComponents();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenSeekBar.setProgress(progress);
                currentColorValues[1] = progress;
                greenValue.setText(String.valueOf(currentColorValues[1]));
                setAllColorComponents();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueSeekBar.setProgress(progress);
                currentColorValues[2] = progress;
                blueValue.setText(String.valueOf(currentColorValues[2]));
                setAllColorComponents();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        /**
         * EditText methods
         */
        redValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                currentColorValues[0] = Integer.parseInt(s.toString());
                redSeekBar.setProgress(currentColorValues[0]);
                setAllColorComponents();
            }
        });

        greenValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                currentColorValues[1] = Integer.parseInt(s.toString());
                greenSeekBar.setProgress(currentColorValues[1]);
                setAllColorComponents();
            }
        });

        blueValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                currentColorValues[2] = Integer.parseInt(s.toString());
                blueSeekBar.setProgress(currentColorValues[2]);
                setAllColorComponents();
            }
        });
    }

    private void setAllColorComponents() {
        redColor.setBackgroundColor(Color.rgb(currentColorValues[0], 0, 0));
        greenColor.setBackgroundColor(Color.rgb(0, currentColorValues[1], 0));
        blueColor.setBackgroundColor(Color.rgb(0, 0, currentColorValues[2]));

        colorView.setBackgroundColor(Color.rgb(currentColorValues[0], currentColorValues[1], currentColorValues[2]));
    }

    public void getRGBCode(View view) {
        String string = String.format("RBG: %d Red - %d Green - %d Blue", currentColorValues[0], currentColorValues[1], currentColorValues[2]);
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void setComponents() {
        currentColorValues = new int[3];
        colorView = findViewById(R.id.colorView);

        redValue = findViewById(R.id.redValue);
        greenValue = findViewById(R.id.greenValue);
        blueValue = findViewById(R.id.blueValue);

        redColor = findViewById(R.id.redColor);
        greenColor = findViewById(R.id.greenColor);
        blueColor = findViewById(R.id.blueColor);

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        currentColorValues[0] = redSeekBar.getProgress();
        currentColorValues[1] = greenSeekBar.getProgress();
        currentColorValues[2] = blueSeekBar.getProgress();

        redValue.setText(String.valueOf(currentColorValues[0]));
        greenValue.setText(String.valueOf(currentColorValues[1]));
        blueValue.setText(String.valueOf(currentColorValues[2]));

        setAllColorComponents();
    }

    public void saveCurrentColor(View view) {
        savedColors.add(new SavedColor(currentColorValues));
        Log.i("COLOR SAVED", currentColorValues[0] + " " + currentColorValues[1] + " " + currentColorValues[2]);
    }

    public void openColorList(View view) {
        Intent intent = new Intent(this, ColorList.class);
        intent.putParcelableArrayListExtra("savedColors", savedColors);
        startActivity(intent);
    }
}
