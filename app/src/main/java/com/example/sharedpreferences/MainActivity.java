package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private SeekBar seekBar;

    private SharedPreferences sharedPreferences;

    private final String FILE_NAME = "MY_FILE";
    private final String KEY_VALUE = "KEY_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);

        textView.setText(String.valueOf(readSharedPreferences()));
        seekBar.setProgress(readSharedPreferences());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                writeSharedPreferences(seekBar);
            }
        });
    }

    private void writeSharedPreferences(SeekBar seekBar) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_VALUE, seekBar.getProgress());
        editor.apply();
    }

    private int readSharedPreferences() {
        return sharedPreferences.getInt(KEY_VALUE, 0);
    }
}