package com.example.tamz_zodiac_2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener{
    SharedPreferences shared;

    static String zodiacNames[] = {"Kozoroh", "Vodnář", "Ryby", "Beran", "Býk", "Blíženci", "Rak", "Lev", "Panna", "Váhy", "Štír", "Střelec"};
    static String zodiacLinks[] = {"kozoroh", "vodnar", "ryby", "beran", "byk", "blizenci", "rak", "lev", "panna", "vahy", "stir", "strelec"};
    static int zodiacSymbols[] = {
        R.drawable.kozoroh01,
        R.drawable.vodnar02,
        R.drawable.ryby03,
        R.drawable.beran04,
        R.drawable.byk05,
        R.drawable.blizenci06,
        R.drawable.rak07,
        R.drawable.lev08,
        R.drawable.panna09,
        R.drawable.vahy10,
        R.drawable.stir11,
        R.drawable.strelec12
    };
    static int breakDate[] = {21, 21, 21, 21, 22, 22, 23, 23, 23, 24, 23, 22};

    DatePicker myDate;
    TextView myText;
    TextView myText2;
    ImageView myWallpaper;
    ImageView myImage;
    int globalMonth;
    int globalDay;
    int zodiacId;
    int wallpaperId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = getApplicationContext().getSharedPreferences("savedData", Context.MODE_PRIVATE);

        int year = shared.getInt("year", 2015);
        int month = shared.getInt("month", 0);
        int day = shared.getInt("day", 1);
        myDate = findViewById(R.id.myDatePicker);
        myDate.init(year, month, day, this);

        myText = findViewById(R.id.textView);
        myText.setText("Zadej Datum");

        myText2 = findViewById(R.id.textView2);

        wallpaperId = shared.getInt("wallpaper", 0);
        myWallpaper = findViewById(R.id.wallpaper);
        if(wallpaperId == 1) {
            myWallpaper.setImageResource(R.drawable.wall01);
        }
        else if(wallpaperId == 2) {
            myWallpaper.setImageResource(R.drawable.wall02);
        }
        if(wallpaperId > 0) {
            myText.setTextColor(Color.argb(255, 255, 255, 255));
            myText2.setTextColor(Color.argb(255, 255, 255, 255));
        }

        myImage = findViewById(R.id.imageView);
        myImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(view == myImage) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            // overlay
                            myImage.getDrawable().setColorFilter(Color.argb(100, 0, 255, 0), PorterDuff.Mode.SRC_ATOP);
                            myImage.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            // clear the overlay
                            myImage.getDrawable().clearColorFilter();
                            myImage.invalidate();
                            // open website
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://horoskopy.cz/" + zodiacLinks[zodiacId]));
                            startActivity(browserIntent);
                        }
                    }
                }
                return true;
            }
        });

        globalMonth = month;
        globalDay = day;

        if(globalDay < breakDate[globalMonth]) {
            zodiacId = globalMonth;
        }
        else {
            zodiacId = (globalMonth + 1 >= 12 ? 0 : globalMonth + 1);
        }

        myImage.setImageResource(zodiacSymbols[zodiacId]);
        myText2.setText(zodiacNames[zodiacId]);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        Log.d("mesic ", " " + monthOfYear);
        globalMonth = monthOfYear;
        globalDay = dayOfMonth;

        if(globalDay < breakDate[globalMonth]) {
            zodiacId = globalMonth;
        }
        else {
            zodiacId = (globalMonth + 1 >= 12 ? 0 : globalMonth + 1);
        }

        myImage.setImageResource(zodiacSymbols[zodiacId]);
        myText2.setText(zodiacNames[zodiacId]);

        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("year", year);
        editor.putInt("month", monthOfYear);
        editor.putInt("day", dayOfMonth);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.about:
                Intent myIntentAbout = new Intent(this, Main2Activity.class);
                myIntentAbout.putExtra("wallpaperId", wallpaperId);
                startActivity(myIntentAbout);
                return true;
            case R.id.settings:
                Intent myIntentSettings = new Intent(this, Main3Activity.class);
                myIntentSettings.putExtra("wallpaperId", wallpaperId);
                startActivityForResult(myIntentSettings, 123);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed (123)
        if(requestCode == 123) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, requestCode + " " + result, Toast.LENGTH_LONG).show();

            wallpaperId = resultCode;

            if(wallpaperId == 1) {
                myWallpaper.setImageResource(R.drawable.wall01);
            }
            else if(wallpaperId == 2) {
                myWallpaper.setImageResource(R.drawable.wall02);
            }
            if(wallpaperId > 0) {
                myText.setTextColor(Color.argb(255, 255, 255, 255));
                myText2.setTextColor(Color.argb(255, 255, 255, 255));
            }

            SharedPreferences.Editor editor = shared.edit();
            editor.putInt("wallpaper", wallpaperId);
            editor.commit();
        }
    }
}
