package edu.ccbcmd.pathwayguide;

        import android.content.pm.ActivityInfo;
        import android.widget.Button;

        import android.net.Uri;

        import android.view.View;
        import android.content.Intent;
        import android.os.Bundle;

        import android.support.v7.app.AppCompatActivity;
//Checked
public class blackboardReminder extends AppCompatActivity
{


    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_blackboard_reminder); //2130968602
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Button button = (Button)this.findViewById(R.id.buttonCollect); //2131624021
        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() { //2131624022


            public void onClick(final View view) {

                blackboardReminder.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://ccbcmd-bb.blackboard.com/")));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                blackboardReminder.this.startActivity(new Intent(blackboardReminder.this, (Class)MainActivity.class));
            }
        });
    }
}
