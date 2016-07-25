package edu.ccbcmd.pathwayguide;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

public class chooseCurrentClasses extends AppCompatActivity
{

    public SharedPreferences prefs;


    public static int getColor(final Context context, final int n) {

        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(context, n);
        }
        return context.getResources().getColor(n);
    }

    private void loopQuestions(final ViewGroup viewGroup) {


        String[] courseLabels = loader.getCourseLabels();
        List<CheckBox> checkBoxesInProgress = new ArrayList<CheckBox>();
        for (int i = 0; i<viewGroup.getChildCount(); i++){
            checkBoxesInProgress.add((CheckBox) viewGroup.getChildAt(i));
        }

        SharedPreferences sharedPrefInProgress = getSharedPreferences("coursesInProgress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorIP = sharedPrefInProgress.edit();

        //To ensure no stragglers have it set to true without permission.
        /*
        for (int i = 0; i < loader.howManyCourses(); i++)
        {
            editorIP.putBoolean(courseLabels[i], false);
            editorIP.apply();
        }
*/

        //Here's where we actually set up the checkboxes
        for (int i = 0; i < checkBoxesInProgress.size(); i++) {
// TODO: 7/24/2016 Review efficiency. should be an easier way to do this.
                CheckBox box = checkBoxesInProgress.get(i);
                int id = box.getId();

                if (box.isChecked()) {
                    DatabaseWrapper.writeClassStatus(courseLabels[id], 1);
                }
                //if a checkbox was deselected, sets course from "IN_PROGRESS" to "NOT_COMPLETED"
            else if (DatabaseWrapper.getClassStatus(courseLabels[id]) == 1) {
                    DatabaseWrapper.writeClassStatus((courseLabels[id]), 0);
                }
        }

        this.startActivity(new Intent(this, (Class)MainActivity.class));
    }

    private CourseClassLoader loader;
    private static int length_of_classes;

    @TargetApi(23)
    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_choose_current_classes); //2130968605
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loader = new CourseClassLoader(getApplicationContext());
        List<CourseClass> courseList = loader.loadClassObjects();

        length_of_classes = loader.howManyCourses();

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Choose Classes");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getResources();
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);

        new RelativeLayout(this);
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout16); //2131624032
        int length = length_of_classes;
        for (int i = 0; i < length; ++i) {
            CourseClass course = courseList.get(i);

            if (course.getStatus() != 2 && !course.getPreReqs(0).equals("PERMISSION")) {
                final CheckBox checkBox = new CheckBox(this);
                checkBox.setText((course.getTitle() + ": " + course.getFullTitle()));
                if (course.getStatus() == 1){
                    checkBox.setChecked(true);
                }
                checkBox.setId(course.getPosition());
                checkBox.setButtonTintList(ColorStateList.valueOf(getColor(this, R.color.pathwayblue))); //2131558446
                linearLayout.addView(checkBox);
            }
        }
        new MaterialShowcaseView.Builder(this).setTarget(new View(this)).setDismissText("Okay").setTitleText("Please select the courses that you are currently taking").withRectangleShape().setMaskColour(Color.parseColor("#F1335075")).setContentText("Please check any of the courses that your are currently taking. If you are not currently taking any courses, please leave everything blank.").setDelay(100).show();
        this.findViewById(R.id.current).setOnClickListener(new View.OnClickListener() { //2131624033


            public void onClick(final View view) {

                loopQuestions((ViewGroup)chooseCurrentClasses.this.findViewById(R.id.linearLayout16));//2131624032
            }
        });
    }
}
