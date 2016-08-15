package edu.ccbcmd.pathwayguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

//Checked and pasted.
public class splash extends AppCompatActivity
{
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

        }
    };


    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_splash); //2130968622
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.health));
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart(){
        super.onStart();
        // set-up the database
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DatabaseWrapper.db = new PathwaysDBHelper(splash.this).getWritableDatabase();
                handler.sendEmptyMessage(0);
            }
        };

        final Resources resources = getResources();

        new Thread(runnable).start();



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int pathway = DatabaseWrapper.getSettingsPathway();


                final AnimationDrawable animationDrawable = new AnimationDrawable();

                switch (pathway){
                    default: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.health), 500); //2130837595
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.tech), 500); //2130837602
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.business), 500); //2130837583
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.social), 500); //2130837600
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.arts), 500); //2130837580
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.splash), 500); //2130837601
                        break;
                    }
                    case 100: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.health), 3000); //2130837595
                        break;
                    }
                    case 200: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.tech), 3000); //2130837602
                        break;
                    }
                    case 300: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.business), 3000); //2130837602
                        break;
                    }
                    case 400: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.social), 3000); //2130837602
                        break;
                    }
                    case 500: {
                        animationDrawable.addFrame(resources.getDrawable(R.drawable.arts), 3000); //2130837602
                        break;
                    }
                }


                animationDrawable.setOneShot(true);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                if (Build.VERSION.SDK_INT < 16) {
                    imageView.setImageDrawable(animationDrawable);
                }
                else {
                    imageView.setImageDrawable(animationDrawable);
                }
                animationDrawable.start();
            }
        }, 100);

        handler.postDelayed( new Runnable() {


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
        }, 3000);
    }
}
