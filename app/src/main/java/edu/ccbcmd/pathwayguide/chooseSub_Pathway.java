package edu.ccbcmd.pathwayguide;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import android.graphics.drawable.Drawable;

import android.content.Context;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;

import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;

public class chooseSub_Pathway extends AppCompatActivity implements View.OnClickListener
{

    //This is the access to the database
    PathwaysDBHelper dataBase;

    public SharedPreferences prefs;

    public void onClick(final View view) {

        //This shared preferences allows us to record the user choices. THIS shared preferences variable will be
        //for the courses that are done.
        SharedPreferences pathwayPref = getApplicationContext().getSharedPreferences("pathway", Context.MODE_PRIVATE);
        //The editor so we can save those preferences.
        SharedPreferences.Editor editor = pathwayPref.edit();
        boolean valid = false;
        switch (pathwayPref.getInt("PathwayChoice",100)) {
            case CourseContract.PRE_ALLIED_HEALTH._PRE_ALLIED_HEALTH: {
                switch (view.getId()) {
                    case 0: {
                        editor.putInt("PathwaySubChoice", CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN);
                        valid = true;
                        break;
                    }
                    case 1: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 4: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 5: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 6: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 7: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 8: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 9: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 10: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 11: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default: {
                        editor.putInt("PathwaySubChoice", CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN);
                        valid = true;
                        break;
                    }
                }
            }
            case CourseContract.TSM.TSM:{
                switch (view.getId()) {
                    case 0: {
                        editor.putInt("PathwaySubChoice", CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT);
                        valid = true;
                        break;
                    }
                    case 1: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 4: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 5: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 6: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 7: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 8: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 9: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 10: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 11: {
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default:{
                        editor.putInt("PathwaySubChoice", CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT);
                        valid=true;
                        break;
                    }
                }
            }
        }
        if (valid) {

            final Intent intent = new Intent(this, (Class) chooseCompletedClasses.class);
            this.startActivity(intent);
            return;
        }




    }

    public void onCreate(final Bundle bundle) {


            super.onCreate(bundle);
            this.setContentView(R.layout.activity_choose_sub__pathway); //2130968607
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            this.getSupportActionBar().show();
            this.getSupportActionBar().setTitle("Choose Your Pathway");
            this.getSupportActionBar().setHomeButtonEnabled(true);

            final Resources resources = this.getResources();
            this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header ))); //2130837594


            final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout4); //2131624035

        //This shared preferences allows us to record the user choices. THIS shared preferences variable will be
        //for the courses that are done.
        SharedPreferences pathwayPref = getApplicationContext().getSharedPreferences("pathway", Context.MODE_PRIVATE);

        int pathway = pathwayPref.getInt("PathwayChoice",-1);

        //Initializing the database
        dataBase = new PathwaysDBHelper(getApplicationContext());
        DatabaseWrapper wrapper = new DatabaseWrapper();

        int length;
        String[] subPath;
        switch(pathway){
            case 100:{
                //Databaseway
                subPath = wrapper.getSubPathways(CourseContract.PRE_ALLIED_HEALTH.PRE_ALLIED_HEALTH_NAME);
                length = subPath.length;
                break;
                //Old Way
                //subPath = getResources().getStringArray(R.array.PathwayCategoryPRE);

            }
            case 200:{
                subPath = wrapper.getSubPathways(CourseContract.TSM.TSM_NAME);
                length = subPath.length;
                break;
                //Old Way
                //subPath = getResources().getStringArray(R.array.PathwayCategoryTSM);

            }
            default:{
                //Databaseway
                subPath = wrapper.getSubPathways(CourseContract.PRE_ALLIED_HEALTH.PRE_ALLIED_HEALTH_NAME);
                length = subPath.length;
                break;
                //Old Way
                //subPath = getResources().getStringArray(R.array.PathwayCategoryPRE);

            }
        }
            for (int i = 0; i < length; ++i) {
                final Button button = new Button(this);
                button.setOnClickListener(this);
                button.setText(subPath[i]);
                final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(5, 5, 5, 5);
                button.setLayoutParams(layoutParams);
                button.setTag(i);
                button.setWidth(Math.round(TypedValue.applyDimension(1, 220.0f, this.getResources().getDisplayMetrics())));
                button.setId(i);
                linearLayout.addView(button);
                final LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)button.getLayoutParams();
                layoutParams2.gravity = Gravity.CENTER; //17
                button.setLayoutParams(layoutParams2);
            }

    }
}
