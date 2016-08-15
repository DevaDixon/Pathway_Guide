package edu.ccbcmd.pathwayguide;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.widget.Toast;
import android.widget.Button;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

//Checked
public class demo_alert extends Activity
{

    public void onBackPressed() {

        final Intent intent = new Intent();
        intent.putExtra("edittextvalue", "value_here");
        this.setResult(-1, intent);
        this.finish();
    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_demo_alert); //2130968608
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Button button = (Button)this.findViewById(R.id.buttonCollect); //2131624021
        final Button button2 = (Button)this.findViewById(R.id.button2); //2131624022
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                final Toast text = Toast.makeText(demo_alert.this, "For this walkthrough, please select 'I Passed'", Toast.LENGTH_SHORT); //0
                text.setGravity(17, 0, 0);
                text.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                demo_alert.this.startActivity(new Intent(demo_alert.this, (Class)demo_mainActivity_2.class));
            }
        });
    }

    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {

        if (Integer.parseInt(Build.VERSION.SDK) > 5 && n == 4 && keyEvent.getRepeatCount() == 0) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
}
