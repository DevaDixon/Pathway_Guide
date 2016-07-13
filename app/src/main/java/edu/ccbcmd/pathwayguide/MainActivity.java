package edu.ccbcmd.pathwayguide;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


import android.widget.TextView;



public class MainActivity extends Activity implements View.OnClickListener
{

    public SharedPreferences prefs;
    public static CourseClassLoader courseClassLoader;
    public List<CourseClass> coursesList;

    public void onClick(View view) {

        Intent intent = new Intent(this, info.class);
        int position = coursesList.get(view.getId()).getPosition();
        this.prefs.edit().putString("choosenID", String.valueOf(position)).commit();
        this.startActivity(intent);
    }

    @TargetApi(16)
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main); //2130968616
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        courseClassLoader = new CourseClassLoader(getApplicationContext());

        coursesList = courseClassLoader.loadClassObjects();

        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        this.prefs.edit().putInt("zoom", 0).commit();


        Integer value = this.prefs.getInt("pathwayID", 0);
        Integer value2 = this.prefs.getInt("pathwaysubID", 0);
        String subPathTitle = this.prefs.getString("SubPathTitle", "null");
        String string = this.prefs.getString("notifydate", "00/00/0000");
        checkAlarm(string);


        if (value2 == -1 ) {
            this.startActivity(new Intent(this, (Class)choosePathway.class));
            return;
        }



        LinearLayout linearLayout2 = (LinearLayout)this.findViewById(R.id.linearLayout2); //2131624046
        final ScrollView scrollView = (ScrollView)this.findViewById(R.id.scrollView1); //2131624045
        scrollView.post(new Runnable() {

            @Override
            public void run() {

                scrollView.fullScroll(130);
            }
        });
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.grad); //2130837591
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));

        Resources resources = this.getResources();
        imageView.getLayoutParams().height = Math.round(TypedValue.applyDimension(1, 70.0f, resources.getDisplayMetrics()));
        Math.round(TypedValue.applyDimension(1, 70.0f, resources.getDisplayMetrics()));
        linearLayout2.addView(imageView);
        int i;
        for (int n7 = i = 0; coursesList.size() > i; ++i) {

            CourseClass curCourse = coursesList.get(i);

             float density = this.getResources().getDisplayMetrics().density;
             int n8 = (int)(13 * density);
             int n9 = (int)(2.2 * density);
             Button button = new Button(this);

            //id seems to serve as which one we are talking about in the list.
             int id = i; //choosePathway.subpathwayCoursePath[0][value2][i - 1];
            button.setText(curCourse.getTitle());

             LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            button.setPadding(n8, n8, n8, n8);
            button.setGravity(16);
            button.setGravity(1);
            button.setGravity(17);
            button.setTextSize(1, 14.0f);
            button.setTypeface(null, 1);
            if (i != n7) {
                layoutParams.setMargins(n9, n9, n9, n9);
            }
            else {
                layoutParams.setMargins(n9, (int)(5.0f * density), n9, n9);
            }

            button.setLayoutParams(layoutParams);
            button.setTag(id);
            button.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
            button.setId(id);
            button.setOnClickListener(this);

            //Length is asking how many courses?  No idea why?  It seems this is a catch case so that
            //bad pathways don't crash the program.
            int length = 1;// choosePathway.coursePreRec[value][id].length;


            //This is loading what status the course is.
            int n10 = -1; //_loadArrayInt[id];
            if (curCourse.getDone()){ n10 = 0;}
            if (curCourse.getIsOpenForRegistration()){n10 = 3;}
            if (curCourse.getInProgress()){n10 = 4;}



            button.setTextColor(Color.parseColor("#ffffff"));

            if (curCourse.getDone()) {
                button.setBackgroundColor(Color.parseColor("#159b8a")); //Green  DONE
            }
            else if (curCourse.getInProgress()) {
                button.setBackgroundColor(Color.parseColor("#644181"));  //purple Currently taking
            }
            else if (curCourse.getIsOpenForRegistration()) {
                button.setTextColor(Color.parseColor("#000000"));
                button.setBackgroundColor(Color.parseColor("#fcd054"));  //YelloW!  Available for register
            }
            else if (length == 0) {
                button.setTextColor(Color.parseColor("#000000"));
                button.setBackgroundColor(Color.parseColor("#fcd054"));
            //    Log.w("if/else", "=0");
            }
            else {

                    button.setBackgroundColor(Color.parseColor("#893f4e"));  //RED  NEED PERMISSION
            }
            linearLayout2.addView(button);
             LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)button.getLayoutParams();
            layoutParams2.gravity = Gravity.CENTER; //17
            button.setLayoutParams(layoutParams2);
        }
         float density2 = this.getResources().getDisplayMetrics().density;
         int n13 = (int)(13 * density2);
         int n14 = (int)(2.2 * density2);
         NonBreakingPeriodTextView nonBreakingPeriodTextView = new NonBreakingPeriodTextView(this);




        nonBreakingPeriodTextView.setText((courseClassLoader.getPathway()+ "\nPathway"), TextView.BufferType.EDITABLE);
         LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        nonBreakingPeriodTextView.setPadding(n13, n13, n13, n13);
        nonBreakingPeriodTextView.setGravity(16);
        nonBreakingPeriodTextView.setGravity(1);
        nonBreakingPeriodTextView.setGravity(17);
        nonBreakingPeriodTextView.setTextSize(1, 14.0f);
        nonBreakingPeriodTextView.setTypeface(null, 1);
        layoutParams3.setMargins(n14, n14, n14, n14);
        nonBreakingPeriodTextView.setLayoutParams(layoutParams3);
        nonBreakingPeriodTextView.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
        nonBreakingPeriodTextView.setTextColor(Color.parseColor("#ffffff"));
        linearLayout2.addView(nonBreakingPeriodTextView);
         LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams)nonBreakingPeriodTextView.getLayoutParams();
        layoutParams4.gravity = Gravity.CENTER; //17
        nonBreakingPeriodTextView.setLayoutParams(layoutParams4);
        (this.findViewById(R.id.zoomout)).setOnClickListener(new View.OnClickListener() { //2131624047


            public void onClick( View view) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class)MainActivityZoomOut.class));
            }
        });
        (this.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() { //2131624022


            public void onClick( View view) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, (Class)Settings.class));
            }
        });
    }

    public void onResume() {

        super.onResume();
        if (this.getIntent().getIntExtra("intVariableName", 1) == 0) {
             int int1 = this.prefs.getInt("opencount", 1);

            int n;
            if (int1 == 5) {
                this.startActivity(new Intent(this, (Class)blackboardReminder.class));
                n = 1;
            }
            else {
                n = int1 + 1;
            }
            this.prefs.edit().putInt("opencount", n).commit();
        }
        if (this.prefs.getBoolean("firstrun", true)) {
            this.startActivity(new Intent(this, (Class)choosePathway.class));
        }

        String string = this.prefs.getString("notifydate", "00/00/0000");
        setCheckAlarm(string);
    }

    public void checkAlarm(String notifyDate){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0); //11
        instance.set(Calendar.MINUTE, 0); //12
        instance.set(Calendar.SECOND, 0); //13
        instance.set(Calendar.MILLISECOND, 0); //14

        String[] split = notifyDate.split("/");
        String s = split[2];
        String s2 = split[0];
        String s3 = split[1];
        int int1 = Integer.parseInt(s);
        int int2 = Integer.parseInt(s2);
        int int3 = Integer.parseInt(s3);
        instance.set(Calendar.YEAR, int1); //1
        instance.set(Calendar.MONTH, int2); //2
        instance.set(Calendar.DATE, int3 - 1); //5

        Date time = instance.getTime();

        Date time2 = Calendar.getInstance().getTime();

        if (time2.before(time)) {
            //If current time is before set time.

            SharedPreferences.Editor edit = this.prefs.edit();

            Calendar instance2 = Calendar.getInstance();
            instance2.set(Calendar.HOUR_OF_DAY, 0);
            instance2.set(Calendar.MINUTE, 0);
            instance2.set(Calendar.SECOND, 0);
            instance2.set(Calendar.MILLISECOND, 0);
            int value3 = instance2.get(Calendar.MONTH); //2
            int value4 = instance2.get(Calendar.YEAR); //1
            Log.w("THIS YEAR", String.valueOf(value4));
            int n2;
            int n3;
            if (value3 < 4) {
                int n = 4;
                Log.w("THIS Month", "<4: year post is: " + value4);
                n2 = value4;
                n3 = n;
            }
            else if (value3 < 11) {
                int n4 = 11;
                n2 = value4;
                Log.w("THIS Month", "<10: year post is: " + n2);
                n3 = n4;
            }
            else {
                int n5 = 4;
                int n6 = value4 + 1;
                Log.w("THIS YEAR", "TRUE " + n6);
                n3 = n5;
                n2 = n6;
            }

            Calendar instance3 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance3.set(Calendar.DATE, 1); //5
            instance3.set(Calendar.MONTH, n3); //2
            instance3.set(Calendar.YEAR, n2); //1
            instance3.set(Calendar.HOUR_OF_DAY, 10); //11
            instance3.set(Calendar.MINUTE, 0); //12
            instance3.set(Calendar.SECOND, 0); //13

            AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE); //"alarm"
            Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            intent.addCategory("android.intent.category.DEFAULT");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT); //134217728

            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, instance3.getTimeInMillis(), broadcast);
            }
            else {
                alarmManager.set(0, instance3.getTimeInMillis(), broadcast);
            }
            edit.putString("notifydate", String.valueOf(n3) + "/01/" + String.valueOf(n2)).commit();
            Log.w("NEW ALERT SET DATE", String.valueOf(n3) + "/01/" + String.valueOf(n2));
            Log.w("Date Calc", "Date specified [" + time2 + "] is before today [" + time + "]");
        }
        else {
            Log.w("Date Calc", "Date specified [" + time2 + "] is NOT before today [" + time + "]");
        }
    }

    public void setCheckAlarm(String notifyDate){

        Calendar instance = Calendar.getInstance();

        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 1);
        instance.set(Calendar.MILLISECOND, 0);
        String[] split = notifyDate.split("/");
        String s = split[2];
        String s2 = split[0];
        String s3 = split[1];
        int int2 = Integer.parseInt(s);
        int int3 = Integer.parseInt(s2);
        int int4 = Integer.parseInt(s3);
        instance.set(Calendar.YEAR, int2);
        instance.set(Calendar.MONTH, int3);
        instance.set(Calendar.DATE, int4);//Why did this have minus one!??

        Date time = instance.getTime();
        Date time2 = Calendar.getInstance().getTime();

        if (time2.before(time)) {

            Calendar instance2 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

            instance2.set(Calendar.HOUR_OF_DAY, 0);
            instance2.set(Calendar.MINUTE, 0);
            instance2.set(Calendar.SECOND, 0);
            instance2.set(Calendar.MILLISECOND, 0);
            int value = instance2.get(Calendar.MONTH);
            int value2 = instance2.get(Calendar.YEAR);
            int n2 = 0;
            int n3 = 0;
            Log.w("THIS YEAR", String.valueOf(value));
            if (value < 4) {
                n2 = 4;
                Log.w("THIS Month", "<4: year post is: " + value2);
                n3 = value2;
            }
            else if (value < 10) {
                n2 = 11;
                Log.w("THIS Month", "<10: year post is: " + value2);
                n3 = value2;
            }
            else if (value == 11) {
                n2 = 4;
                n3 = value2 + 1;
                Log.w("THIS YEAR (bottom)", "True" + n3);
            }

            Calendar instance3 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance3.set(Calendar.DATE, 1); //5
            instance3.set(Calendar.MONTH, n2); //2
            instance3.set(Calendar.YEAR, n3); //1
            instance3.set(Calendar.HOUR_OF_DAY, 10); //11
            instance3.set(Calendar.MINUTE, 0); //12
            instance3.set(Calendar.SECOND, 0); //13

            AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE); //"alarm"
            Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            intent.addCategory("android.intent.category.DEFAULT");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, instance3.getTimeInMillis(), broadcast);
            }
            else {
                alarmManager.set(0, instance3.getTimeInMillis(), broadcast);
            }







            Calendar instance4 = Calendar.getInstance();
            int value3 = instance4.get(Calendar.MONTH);
            int value4 = instance4.get(Calendar.YEAR);
            switch (value3) {
                case 0: {
                    ++value3;
                    break;
                }
                case 1: {
                    ++value3;
                    break;
                }
                case 2: {
                    ++value3;
                    break;
                }
                case 3: {
                    value3 = 8;
                    break;
                }
                case 4: {
                    value3 = 8;
                    break;
                }
                case 5: {
                    value3 = 8;
                    break;
                }
                case 6: {
                    value3 = 8;
                    break;
                }
                case 7: {
                    ++value3;
                    break;
                }
                case 8: {
                    ++value3;
                    break;
                }
                case 9: {
                    ++value3;
                    break;
                }
                case 10: {
                    value3 = 1;
                    value4++;
                    break;
                }
                case 11: {
                    value3 = 1;
                    value4++;
                    break;
                }
            }

            instance4.set(Calendar.DATE, 1);
            instance4.set(Calendar.YEAR,value4);
            instance4.set(Calendar.MONTH, value3);
            instance4.set(Calendar.HOUR_OF_DAY, 10);
            instance4.set(Calendar.MINUTE, 0);
            instance4.set(Calendar.SECOND, 0);



            Intent intent2 = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            intent2.addCategory("android.intent.category.DEFAULT");
            PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, instance4.getTimeInMillis(), broadcast2);
            }
            else {
                alarmManager.set(0, instance4.getTimeInMillis(), broadcast2);
            }

            SharedPreferences.Editor edit = this.prefs.edit();
            edit.putString("notifydate", String.valueOf(n2) + "/01/" + String.valueOf(n3)).commit();
            Log.w("NEW ALERT SET DATE", String.valueOf(n2) + "/01/" + String.valueOf(n3));
            Log.w("Date Calc", "Date specified [" + time2 + "] is before today [" + time + "]");
        }
        else {
            Log.w("Date Calc", "Date specified [" + time2 + "] is NOT before today [" + time + "]");
        }



        AlarmManager alarmManager2 = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE); //"alarm"
        Calendar instance5 = Calendar.getInstance();
        int value5 = instance5.get(Calendar.MONTH);
        int value6 = instance5.get(Calendar.YEAR);
        switch (value5) {
            case 0: {
                ++value5;
                break;
            }
            case 1: {
                ++value5;
                break;
            }
            case 2: {
                ++value5;
                break;
            }
            case 3: {
                value5 = 8;
                break;
            }
            case 4: {
                value5 = 8;
                break;
            }
            case 5: {
                value5 = 8;
                break;
            }
            case 6: {
                value5 = 8;
                break;
            }
            case 7: {
                ++value5;
                break;
            }
            case 8: {
                ++value5;
                break;
            }
            case 9: {
                ++value5;
                break;
            }
            case 10: {
                value5 = 1;
                value6++;
                break;
            }
            case 11: {
                value5 = 1;
                value6++;
                break;
            }
        }
        Log.w("Cmonth", String.valueOf(value5));
        instance5.set(Calendar.DATE, 1);
        instance5.set(Calendar.MONTH, value5);
        instance5.set(Calendar.YEAR, value6);
        instance5.set(Calendar.MINUTE, 0);
        instance5.set(Calendar.SECOND, 0);

        Intent intent3 = new Intent("android.media.action.DISPLAY_Blackboard_NOTIFICATION");
        intent3.addCategory("android.intent.category.DEFAULT_Blackboard");
        PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 100, intent3, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager2.setExact(0, instance5.getTimeInMillis(), broadcast3);
            return;
        } else {
            alarmManager2.set(0, instance5.getTimeInMillis(), broadcast3);
        }
    }
}
