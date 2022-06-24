package com.example.tamz_zodiac_2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    View buttons[] = {null, null};
    ImageView myWallpaper;
    int wallpaperId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttons[0] = findViewById(R.id.button01);
        buttons[1] = findViewById(R.id.button02);

        for(View button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    if(view == buttons[0]) {
                        intent.putExtra("result", "BUTTON - 1");
                        setResult(1, intent);
                        finish();
                    }
                    if(view == buttons[1]) {
                        intent.putExtra("result", "BUTTON - 2");
                        setResult(2, intent);
                        finish();
                    }
                }
            });
        }

        myWallpaper = findViewById(R.id.wallpaper);
        wallpaperId = getIntent().getIntExtra("wallpaperId", 0);

        if(wallpaperId == 1) {
            myWallpaper.setImageResource(R.drawable.wall01);
        }
        else if(wallpaperId == 2) {
            myWallpaper.setImageResource(R.drawable.wall02);
        }
    }
}
