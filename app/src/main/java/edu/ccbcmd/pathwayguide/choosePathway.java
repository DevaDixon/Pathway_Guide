package edu.ccbcmd.pathwayguide;




import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class choosePathway extends AppCompatActivity implements View.OnClickListener {


    public SharedPreferences prefs;

    protected String[] pathwaysTop;



    public void onClick(final View view) {


        //This shared preferences allows us to record the user choices. THIS shared preferences variable will be
        //for the courses that are done.
        SharedPreferences pathwayPref = getApplicationContext().getSharedPreferences("pathway", Context.MODE_PRIVATE);
        //The editor so we can save those preferences.
        SharedPreferences.Editor editor = pathwayPref.edit();


        // TODO: 7/18/2016 REMOVE BEFORE RELEASE: conditional intentionally limits to first 2 paths since DB not complete as of now.
        if (view.getId() == 0 ||((Integer)view.getId()) == 1) {
            String pathText = pathwaysTop[view.getId()];
            editor.putString("PathTitle", pathText).apply();
            final Intent intent = new Intent(this, (Class) chooseSub_Pathway.class);
            intent.putExtra("arrayID", String.valueOf(view.getId()));
            this.startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "This pathway is not supported yet", Toast.LENGTH_LONG).show();
        }

    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_choose_pathway);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //The fifth instance of sharedpreferences is to get the double class status
        SharedPreferences pathwayDoubleCourse = getSharedPreferences("DoubleCourse",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pathwayDoubleCourse.edit();

        String[] removeCourses = DatabaseWrapper.getSubPathwayClasses(CourseContract.PRE_ALLIED_HEALTH.ALLIED_HEALTH_NURSING_ASN_NAME);
        for (int i = 0; i< removeCourses.length;i++){
            editor.remove("Double"+removeCourses[i]).apply();
        }
        removeCourses = DatabaseWrapper.getSubPathwayClasses(CourseContract.TSM.TSM_COMPUTER_SCIENCE_IT_NAME);
        for (int i = 0; i< removeCourses.length;i++){
            editor.remove("Double"+removeCourses[i]).apply();
        }

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Choose Your Pathway");

        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594

        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        if (this.prefs.getBoolean("firstrun", true)) {

            this.getSupportActionBar().setHomeButtonEnabled(false);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            this.getSupportActionBar().setHomeButtonEnabled(true);
        }

        final LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout3);


        //Retrieves the pathway names from DB
        pathwaysTop = DatabaseWrapper.getAllPathways();

        int length = pathwaysTop.length;

        for (int i = 0; i < length; ++i) { // TODO: 7/18/2016 Is ++i intentional? does it make a difference in java?
            final Button button = new Button(this);
            button.setOnClickListener(this);
            button.setText(pathwaysTop[i]);
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            if (i != length) { // TODO: 7/18/2016 Why is index being compared to array length? will always be less.
                layoutParams.setMargins(5, 5, 5, 5);
            } else {
                layoutParams.setMargins(5, 25, 5, 5);
            }
            button.setLayoutParams(layoutParams);
            button.setTag(i);
            button.setWidth(Math.round(TypedValue.applyDimension(1, 220.0f, this.getResources().getDisplayMetrics())));
            button.setId(i);
            linearLayout.addView(button);
            final LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams2.gravity = Gravity.CENTER; //17
            button.setLayoutParams(layoutParams2);
        }

    }

    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        boolean booleanValue = true;




        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 16908332: {
                final Integer value = this.prefs.getInt("zoom", 0);
                if (value == 0) {
                    this.startActivity(new Intent(this, (Class) MainActivity.class));
                    return true;
                }
                if (value == 1) {
                    this.startActivity(new Intent(this, (Class) MainActivityZoomOut.class));
                    return true;
                }
                break;
            }
        }

        return booleanValue;
    }





}


