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

    public SharedPreferences prefs;
    protected String[] subPaths;
    public void onClick(final View view) {
        //This shared preferences allows us to record the user choices. THIS shared preferences variable will be
        //for the courses that are done.
        SharedPreferences pathwayPref = getApplicationContext().getSharedPreferences("pathway", Context.MODE_PRIVATE);
        //The editor so we can save those preferences.
        SharedPreferences.Editor editor = pathwayPref.edit();
<<<<<<< HEAD
=======
        boolean valid = false;

        editor.putString("SubPathTitle", ((Button)view).getText().toString());
        int pathway = DatabaseWrapper.getSettingsPathway();

        switch (pathway) {
            case CourseContract.PRE_ALLIED_HEALTH._PRE_ALLIED_HEALTH: {
                switch (view.getId()) {
                    case 0: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN);
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
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN);
                        valid = true;
                        break;
                    }
                }
            }
            case CourseContract.TSM.TSM:{
                switch (view.getId()) {
                    case 0: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT);
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
>>>>>>> refs/remotes/origin/master

        // TODO: 7/23/2016 Remove conditional when pathways are ready to be implemented
        if (((Button)view).getText().toString().equals("Nursing") || ((Button)view).getText().toString().equals("Information Technology")) {
            editor.putString("SubPathTitle", subPaths[view.getId()]).apply();
            final Intent intentt = new Intent(this, (Class) chooseCompletedClasses.class);
            this.startActivity(intentt);
        }
        else {
            Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
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

<<<<<<< HEAD
        String pathTitle = pathwayPref.getString("PathTitle", "null");
=======
        int pathway = DatabaseWrapper.getSettingsPathway();


        //subPaths = wrapper.getSubPathways(pathwayTitle);
        String [] subPath;
        int length;
>>>>>>> refs/remotes/origin/master

        subPaths = DatabaseWrapper.getSubPathways(pathTitle);



            for (int i = 0; i < subPaths.length; ++i) {
                final Button button = new Button(this);
                button.setOnClickListener(this);
                button.setText(subPaths[i]);
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
