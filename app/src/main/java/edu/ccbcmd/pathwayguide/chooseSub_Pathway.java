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

        editor.putString("SubPathTitle", ((Button)view).getText().toString());
        int pathway = DatabaseWrapper.getSettingsPathway();

        switch (pathway) {
            case CourseContract.PRE_ALLIED_HEALTH._PRE_ALLIED_HEALTH: {

                switch (view.getId()) {
                    case 0: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                        valid = true;
                        break;
                    }
                    case 1: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.OCCUPATIONAL_THERAPY_ASSISTANT_NAME);
                        valid = true;
                        break;
                    }
                    case 2: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.DENTAL_HYGIENE_NAME);
                        valid = true;
                        break;
                    }
                    case 3: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.MEDICAL_LAB_TECHNOLOGY_NAME);
                        valid = true;
                        break;
                    }
                    case 4: {
                        //DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.VETERINARY_TECHNOLOGY_NAME);
                        //valid = true;
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 5: {
                        //DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.EMT_NAME);
                        //valid = true;
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 6: {
                        //DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.RCT_NAME);
                        //valid = true;
                        Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 7: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.RADIOGRAPHY_NAME);
                        valid = true;
                        //Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
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
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
                        valid = true;
                        break;
                    }
                }
                break;
            }
            case CourseContract.TSM.TSM:{
                switch (view.getId()) {
                    case 0: {
                        DatabaseWrapper.setSettingsSubPathway(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME);
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
                break;
            }
        }
        if (valid) {

            final Intent intentt = new Intent(this, (Class) chooseCompletedClasses.class);
            this.startActivity(intentt);
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

        int pathway = DatabaseWrapper.getSettingsPathway();

        String [] subPath;
        int length;

        switch(pathway){
            case 100:{
                subPath = DatabaseWrapper.getSubPathways(CourseContract.PRE_ALLIED_HEALTH.PRE_ALLIED_HEALTH_NAME);
                break;

            }
            case 200:{
                subPath = DatabaseWrapper.getSubPathways(CourseContract.TSM.TSM_NAME);
                length = subPath.length;
                break;

            }
            default:{
                //Databaseway
                subPath = DatabaseWrapper.getSubPathways(CourseContract.PRE_ALLIED_HEALTH.PRE_ALLIED_HEALTH_NAME);
                length = subPath.length;
                break;
                //Old Way
                //subPath = getResources().getStringArray(R.array.PathwayCategoryPRE);

            }
        }

            for (int i = 0; i < subPath.length; ++i) {
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
