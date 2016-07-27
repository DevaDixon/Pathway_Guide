package edu.ccbcmd.pathwayguide;

import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.content.Context;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;

import android.content.SharedPreferences;

import android.app.Activity;

public class alert extends Activity
{

    public SharedPreferences prefs;



    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_alert); //2130968601
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);

        final int int3 = Integer.parseInt(this.prefs.getString("choosenID", "0"));

        final CourseClass course = MainActivity.courseClassLoader.getXMLOrder(int3);
        final Button button = (Button)this.findViewById(R.id.buttonCollect); //2131624021
        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() { //2131624022

            public void onClick(final View view) {
                DatabaseWrapper.writeClassStatus(CourseClassLoader.courseLabels[int3],2);
                alert.this.startActivity(new Intent(alert.this, (Class)MainActivity.class));
            }
        });


        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                Context context = getApplicationContext();

                DatabaseWrapper.writeClassStatus(CourseClassLoader.courseLabels[int3],0);

                alert.this.startActivity(new Intent(alert.this, (Class)MainActivity.class));
            }
        });
    }
}
