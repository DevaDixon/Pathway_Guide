package edu.ccbcmd.pathwayguide;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;

import android.widget.Button;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;

import android.net.Uri;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

//Checked
public class cantRegister extends AppCompatActivity
{

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_cant_register); //2130968603
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("What's the issue?");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header ))); //2130837594
        this.getSupportActionBar().setHomeButtonEnabled(true);
        final Button button = (Button)this.findViewById(R.id.cantMoney); //2131624028
        final Button button2 = (Button)this.findViewById(R.id.cantKids); //2131624027
        final Button button3 = (Button)this.findViewById(R.id.other); //2131624026
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                cantRegister.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/financialaid")));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                cantRegister.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Childcare-Services.aspx")));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                cantRegister.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/resources-for-students")));
            }
        });
    }
}
