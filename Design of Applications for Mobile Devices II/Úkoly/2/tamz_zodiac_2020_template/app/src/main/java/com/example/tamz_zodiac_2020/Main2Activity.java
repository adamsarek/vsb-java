package com.example.tamz_zodiac_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    TextView textView4;
    TextView textView5;
    ImageView myWallpaper;
    int wallpaperId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView =findViewById(R.id.textView);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        myWallpaper = findViewById(R.id.wallpaper);
        wallpaperId = getIntent().getIntExtra("wallpaperId", 0);

        if(wallpaperId == 1) {
            myWallpaper.setImageResource(R.drawable.wall01);
        }
        else if(wallpaperId == 2) {
            myWallpaper.setImageResource(R.drawable.wall02);
        }
        if(wallpaperId > 0) {
            textView.setTextColor(Color.argb(255, 255, 255, 255));
            textView4.setTextColor(Color.argb(255, 255, 255, 255));
            textView5.setTextColor(Color.argb(255, 255, 255, 255));
        }
    }
}
