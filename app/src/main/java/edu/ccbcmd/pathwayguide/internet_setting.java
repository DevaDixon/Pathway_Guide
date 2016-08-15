package edu.ccbcmd.pathwayguide;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

//Checked and pasted
public class internet_setting extends AppCompatActivity
{

    public SharedPreferences prefs;

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_internet_setting); //2130968614
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Internet Setting");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        final Switch switch1 = (Switch)this.findViewById(R.id.switch1); //2131624048
        if (this.prefs.getInt("internet", 1) == 1) {
            switch1.setChecked(true);
        }
        else {
            switch1.setChecked(false);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {

                if (b) {
                    internet_setting.this.prefs.edit().putInt("internet", 1).commit();
                    internet_setting.this.prefs.getInt("internet", 1);
                    return;
                }
                internet_setting.this.prefs.edit().putInt("internet", 0).commit();
                internet_setting.this.prefs.getInt("internet", 1);
            }
        });
    }
}
