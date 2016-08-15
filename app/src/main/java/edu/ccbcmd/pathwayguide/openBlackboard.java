package edu.ccbcmd.pathwayguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//Checked and pasted
public class openBlackboard extends AppCompatActivity
{

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.finish();
        this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://ccbcmd-bb.blackboard.com")));
    }
}
