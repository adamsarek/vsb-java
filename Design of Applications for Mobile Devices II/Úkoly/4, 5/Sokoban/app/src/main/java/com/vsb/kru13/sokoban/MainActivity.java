package com.vsb.kru13.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    SokoView sokoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sokoView = findViewById(R.id.sokoView);

        // Load levels
        AssetManager assetManager = getAssets();
        InputStream input;
        try {
            input = assetManager.open("levels.txt");

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            String rawLevels = new String(buffer);

            sokoView.setLevels(rawLevels);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
