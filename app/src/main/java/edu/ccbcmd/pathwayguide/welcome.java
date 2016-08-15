package edu.ccbcmd.pathwayguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//Checked and pasted.
public class welcome extends AppCompatActivity
{

    public SharedPreferences prefs;

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_welcome); //2130968624
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Welcome");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header)));
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        final Button button = (Button)this.findViewById(R.id.button4);
        final Button button2 = (Button)this.findViewById(R.id.button5);
        final Button button3 = (Button)this.findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                welcome.this.startActivity(new Intent(welcome.this, (Class)choosePathway.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                welcome.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Academic-Advisement.aspx")));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                welcome.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/pathways")));
            }
        });
    }
}
