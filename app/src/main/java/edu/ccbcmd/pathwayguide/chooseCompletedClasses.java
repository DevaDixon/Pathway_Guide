package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import materialshowcaseview.MaterialShowcaseView;

public class chooseCompletedClasses extends AppCompatActivity
{
    public SharedPreferences prefs;





    public static /* synthetic */ void access$000(final chooseCompletedClasses chooseCompletedClasses, final ViewGroup viewGroup) {

       chooseCompletedClasses.loopQuestions(viewGroup);
    }

    public static final int getColor(final Context context, final int n) {


        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(context, n);
        }
        return context.getResources().getColor(n);
    }

    private void loopQuestions(final ViewGroup viewGroup) {
        //Nick's Code
        for (int i = 0; i < viewGroup.getChildCount(); ++i) {
            final CheckBox checkBox = (CheckBox)viewGroup.getChildAt(i);
            int n;
            if (checkBox.isChecked()) {
                n = 1;
            }
            else {
                n = 0;
            }
            checkBox.getId();
            if (n == 1) {
                this.getSharedPreferences("preferencename", 0).edit().putInt("courseStat_" + i, 0).commit();
            }
        }

        //Getting a handle for the shared preference editor
        SharedPreferences sharedPrefDone = getSharedPreferences("courses", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPrefDone.edit();

        String[] courseLabels = getResources().getStringArray(R.array.AlliedHealthPathway);
        List<CheckBox> checkBoxesDone = new ArrayList<CheckBox>();
        for (int i = 0; i<viewGroup.getChildCount(); i++){
            checkBoxesDone.add((CheckBox) viewGroup.getChildAt(i));
        }
        //My code
        int counter = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            if (checkBoxesDone.size()>0&& checkBoxesDone.size()>counter) {
                CheckBox box = checkBoxesDone.get(counter);
                counter++;

                if(box.isChecked() ) {

                    editor.putBoolean(courseLabels[i], true);
                    editor.commit();
                } else {

                    editor.putBoolean(courseLabels[i], false);
                    editor.commit();
                }
            }
        }




        this.startActivity(new Intent(this, (Class)chooseCurrentClasses.class));
    }

    @TargetApi(23)
    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_choose_completed_classes); //2130968604
        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Choose Completed Courses");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getResources();
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        this.prefs.edit().putBoolean("firstrun", false).commit();
        final Integer pathID = this.prefs.getInt("pathwayID", 0);
        final Integer pathSubID = this.prefs.getInt("pathwaysubID", 0);
        new RelativeLayout(this);
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout15); //2131624031
        for (int length = choosePathway.subpathwayCoursePath[0][pathID].length, i = 0; i < length; ++i) {
            /*why not just use loop counter?*/
            final int id = choosePathway.subpathwayCoursePath[0][pathID][i];
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText((choosePathway.courseNum[pathSubID][id] + ": " + choosePathway.courseName[pathSubID][id]));
            checkBox.setId(id);
            if (Build.VERSION.SDK_INT >= 16) {
                checkBox.setButtonTintList(ColorStateList.valueOf(getColor(this, R.color.pathwayblue))); //2131558446
            }
            linearLayout.addView(checkBox);
        }
        new MaterialShowcaseView.Builder(this).setTarget(new View(this)).setDismissText("Okay").setTitleText("Please select the courses that you have completed").withRectangleShape().setMaskColour(Color.parseColor("#F1335075")).setContentText("Please select the courses that you are sure you have successfully completed. If you are a transfer student, talk with your advisor to determine what courses will transfer.").setDelay(100).show();
        this.findViewById(R.id.completed).setOnClickListener(new View.OnClickListener() { //2131624030


            public void onClick(final View view) {
                chooseCompletedClasses.access$000(chooseCompletedClasses.this, (ViewGroup)chooseCompletedClasses.this.findViewById(R.id.linearLayout15));//2131624031
            }
        });
    }
}
