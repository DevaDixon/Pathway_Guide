package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class splash extends AppCompatActivity
{



    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_splash); //2130968622
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //The third instance of sharedpreferences is the particular pathway chosen.
        SharedPreferences pathwayPref = getApplicationContext().getSharedPreferences("pathway", Context.MODE_PRIVATE);

        int pathway = -1;
        final AnimationDrawable animationDrawable = new AnimationDrawable();
        long time = 6000L;

        if (pathwayPref.contains("PathwayChoice"))
        {
            pathway = pathwayPref.getInt("PathwayChoice", 100);
        } else { pathway = 0;}



        switch (pathway){
            case 0: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.health), 1000); //2130837595
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.tech), 1000); //2130837602
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.business), 1000); //2130837583
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.social), 1000); //2130837600
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.arts), 1000); //2130837580
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.splash), 1000); //2130837601
                break;
            }
            case 100: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.health), 1000); //2130837595
                time = 1000L;
                break;
            }
            case 200: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.tech), 1000); //2130837602
                time = 1000L;
                break;
            }
            case 300: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.business), 1000); //2130837602
                time = 1000L;
                break;
            }
            case 400: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.social), 1000); //2130837602
                time = 1000L;
                break;
            }
            case 500: {
                animationDrawable.addFrame(this.getResources().getDrawable(R.drawable.arts), 1000); //2130837602
                time = 1000L;
                break;
            }
        }


        animationDrawable.setOneShot(true);
        final ImageView imageView = (ImageView)this.findViewById(R.id.imageView); //2131624058
        if (Build.VERSION.SDK_INT < 16) {
            imageView.setImageDrawable(animationDrawable);
        }
        else {
            imageView.setImageDrawable(animationDrawable);
        }
        animationDrawable.start();

        // set-up the database
        Thread th = new Thread() {
            public void run() {
                DatabaseWrapper.db = new PathwaysDBHelper(splash.this).getWritableDatabase();
            }
        };
        th.start();

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                final SharedPreferences sharedPreferences = splash.this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
                Log.w("First Run:", String.valueOf(sharedPreferences.getBoolean("firstrun", true)));
                if (sharedPreferences.getBoolean("firstrun", true)) {
                    splash.this.startActivity(new Intent(splash.this, (Class)demo_MainActivity.class));
                }
                else {
                    final Intent intent = new Intent(splash.this, (Class)MainActivity.class);
                    intent.putExtra("intVariableName", 0);
                    splash.this.startActivity(intent);
                }
                splash.this.finish();
            }
        }, time);
    }
}
