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

    private  CourseClassLoader loader;
    private void loopQuestions(final ViewGroup viewGroup) {


        //Getting a handle for the shared preference editor
        SharedPreferences sharedPrefDone = getSharedPreferences("courses", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPrefDone.edit();


        String[] courseLabels = loader.getCourseLabels();
        List<CheckBox> checkBoxesDone = new ArrayList<CheckBox>();

        for (int i = 0; i<viewGroup.getChildCount(); i++){
            checkBoxesDone.add((CheckBox) viewGroup.getChildAt(i));
        }
        for (int i = 0; i< courseLabels.length; i++){
            editor.putBoolean(courseLabels[i], false);
            editor.apply();
        }

        int counter = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            if (checkBoxesDone.size()>0&& checkBoxesDone.size()>counter) {
                CheckBox box = checkBoxesDone.get(counter);
                counter++;

                if(box.isChecked() ) {
                    DatabaseWrapper.writeClassStatus(courseLabels[i],2);
                    editor.putBoolean(courseLabels[i], true);
                    editor.commit();
                } else {
                    DatabaseWrapper.writeClassStatus(courseLabels[i],0);
                    editor.putBoolean(courseLabels[i], false);
                    editor.commit();
                }
            }
        }

        this.startActivity(new Intent(this, (Class)chooseCurrentClasses.class));
    }

    //This is the access to the database
    PathwaysDBHelper dataBase;
    static private int length_of_courses;


    @TargetApi(23)
    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_choose_completed_classes); //2130968604
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Choose Completed Courses");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        //Initializing the database
        dataBase = new PathwaysDBHelper(getApplicationContext());
        DatabaseWrapper wrapper = new DatabaseWrapper();
        loader = new CourseClassLoader(getApplicationContext());
        length_of_courses = loader.howManyCourses();
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        this.prefs.edit().putBoolean("firstrun", false).commit();

        new RelativeLayout(this);
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout15); //2131624031

        //Resetting the permission flags
        SharedPreferences pathwayPermission = getApplicationContext().getSharedPreferences("permission",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pathwayPermission.edit();
        String[] courseNames = loader.getCourseLabels();
        for (int i = 0; i< length_of_courses; i++){
            editor.putBoolean("permission"+courseNames[i],false);
            editor.apply();
        }

        for ( int i = 0; i < length_of_courses; ++i) {

            CourseClass course = loader.getXMLOrder(i);


            if (!course.getPreReqs(0).equals("PERMISSION")) {

                final int id = course.getPosition();
                final CheckBox checkBox = new CheckBox(this);
                checkBox.setText((course.getTitle() + ": " + course.getFullTitle()));
                checkBox.setId(id);

                if (course.getDone()){
                    checkBox.setChecked(true);
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    checkBox.setButtonTintList(ColorStateList.valueOf(getColor(this, R.color.pathwayblue))); //2131558446
                }
                linearLayout.addView(checkBox);
            }
        }
        new MaterialShowcaseView.Builder(this).setTarget(new View(this)).setDismissText("Okay").setTitleText("Please select the courses that you have completed").withRectangleShape().setMaskColour(Color.parseColor("#F1335075")).setContentText("Please select the courses that you are sure you have successfully completed. If you are a transfer student, talk with your advisor to determine what courses will transfer.").setDelay(100).show();
        this.findViewById(R.id.completed).setOnClickListener(new View.OnClickListener() { //2131624030


            public void onClick(final View view) {
                chooseCompletedClasses.access$000(chooseCompletedClasses.this, (ViewGroup)chooseCompletedClasses.this.findViewById(R.id.linearLayout15));//2131624031
            }
        });
    }
}
