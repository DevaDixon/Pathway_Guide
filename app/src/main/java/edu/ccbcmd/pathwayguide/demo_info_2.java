package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */


import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;
import android.graphics.Color;
import materialshowcaseview.MaterialShowcaseView;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import com.github.amlcurran.showcaseview.ShowcaseView;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class demo_info_2 extends AppCompatActivity implements View.OnClickListener
{
    private int counter;
    public SharedPreferences prefs;
    private ShowcaseView showcaseView;


    public static int pxToDp(final int n) {

        return (int)(n * Resources.getSystem().getDisplayMetrics().density);
    }



    public void onClick(final View view) {

        new MaterialShowcaseView.Builder(this).setTarget(this.findViewById(R.id.colorChange)).setDismissText("Okay").setTitleText("Click Here").withRectangleShape().setShapePadding(pxToDp(5)).setMaskColour(Color.parseColor("#F1335075")).setContentText("Click on 'I'm currently taking this class' to update the course status to indicate that your are currently taking the class.").setDelay(100).show(); //2131624038
    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_demo_info_2); //2130968609
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        (this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0)).getInt("pathwayID", -1);
        this.prefs.getInt("pathwaysubID", -1);
        Integer.parseInt(this.prefs.getString("choosenID", "0")); // FIXME: 6/29/2016 is this meant to be used?
        ((TextView)this.findViewById(R.id.textView)).setText("Transitioning to College"); //2131624036
        ((TextView)this.findViewById(R.id.description)).setText("Credits: 1\nRecommended Semester: 1st*\n\n*This course may not be required for transfer students. Ask your adviser for more information."); //2131624042
        this.getSupportActionBar().setTitle("ACDV 101");
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fcd054")));
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        this.findViewById(R.id.button).setOnClickListener(this); //2131624037
        this.findViewById(R.id.button3).setOnClickListener(this); //2131624041
        final Button button = (Button)this.findViewById(R.id.colorChange); //2131624038
        button.setText("I'm currently taking this class");
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(final View view) {

                demo_info_2.this.startActivity(new Intent(demo_info_2.this, (Class)demo_mainActivity_3.class));
            }
        });
    }

    public boolean onOptionsItemSelected(final MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 16908332: {
                this.finish();
                return true;
            }
        }
    }
}
