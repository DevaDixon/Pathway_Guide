package edu.ccbcmd.pathwayguide;




/**
 * Created by dixo8 on 6/24/2016.
 */

        import android.content.pm.ActivityInfo;
        import android.os.Build;

        import android.content.res.Resources;

        import java.util.Date;

        import android.view.Gravity;
        import android.widget.ImageButton;
        import android.graphics.Color;
        import android.widget.TextView;
        import android.util.TypedValue;
        import android.widget.ImageView;
        import android.widget.ScrollView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.app.PendingIntent;
        import android.app.AlarmManager;

        import java.util.List;
        import java.util.Locale;
        import java.util.TimeZone;
        import java.util.Calendar;
        import android.util.Log;

        import android.content.Context;
        import android.view.View;
        import android.content.Intent;
        import android.os.Bundle;
        import android.content.SharedPreferences;
        import android.app.Activity;

public class MainActivityZoomOut extends Activity implements View.OnClickListener
{

    public SharedPreferences prefs;
    public static CourseClassLoader courseClassLoader;
    public List<CourseClass> coursesList;

    public void onClick(final View view) {

        final Intent intent = new Intent(this, (Class)info.class);
        final String value = String.valueOf(view.getId());
        this.prefs.edit().putString("choosenID", value).commit();
        intent.putExtra("arrayID", value);
        this.startActivity(intent);
    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main_activity_zoom_out);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        courseClassLoader = new CourseClassLoader(getApplicationContext());
        coursesList = courseClassLoader.loadClassObjects();


        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        this.prefs.edit().putInt("zoom", 1).commit();

        final Integer pathID = this.prefs.getInt("pathwayID", 0);
        final Integer pathSubID = this.prefs.getInt("pathwaysubID", 0);

        setUpAlarm();

        if (pathSubID == -1) {
            this.startActivity(new Intent(this, (Class)choosePathway.class));
        }
        else {


            final LinearLayout linearLayout2 = (LinearLayout)this.findViewById(R.id.linearLayout2); //2131624046
            final ScrollView scrollView = (ScrollView)this.findViewById(R.id.scrollView1); //2131624045
            scrollView.post(new Runnable() {

                @Override
                public void run() {

                    scrollView.fullScroll(130);
                }
            });

            final int heightPixels = this.getResources().getDisplayMetrics().heightPixels;
            final ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.grad); //2130837591
            imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));

            final Resources resources = this.getResources();
            imageView.getLayoutParams().height = Math.round(TypedValue.applyDimension(1, 70.0f, resources.getDisplayMetrics()));
            final int round = Math.round(TypedValue.applyDimension(1, 70.0f, resources.getDisplayMetrics()));
            linearLayout2.addView(imageView);

            final int length = coursesList.size();

            final int n7 = (heightPixels - round * 2) / length;

            for (int i = 0; i < length; i++) {
                final CourseClass course = coursesList.get(i);
                boolean courseAdded = false;

                if (course.getDone()) {
                    final TextView textView6 = new TextView(this);
                    final int id6 = course.getPosition();
                    textView6.setText(course.getTitle());
                    textView6.setTextColor(Color.parseColor("#ffffff"));
                    final LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-2, -2);

                        layoutParams11.setMargins(5, 5, 5, 0);
                        layoutParams11.height = n7;

                    textView6.setLayoutParams(layoutParams11);
                    textView6.setTag(id6);
                    textView6.setGravity(16);
                    textView6.setGravity(1);
                    textView6.setGravity(17);
                    textView6.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
                    textView6.setId(id6);
                    textView6.setOnClickListener(this);

                    textView6.setBackgroundColor(Color.parseColor("#159b8a"));
                    linearLayout2.addView(textView6);
                    final LinearLayout.LayoutParams layoutParams12 = (LinearLayout.LayoutParams)textView6.getLayoutParams();
                    layoutParams12.gravity = Gravity.CENTER; //17
                    textView6.setLayoutParams(layoutParams12);
                    courseAdded = true;
                }

                if (course.getInProgress() &&!courseAdded) {
                    final TextView textView5 = new TextView(this);

                    textView5.setText(course.getTitle());
                    textView5.setTextColor(Color.parseColor("#ffffff"));
                    final LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-2, -2);

                        layoutParams9.setMargins(5, 5, 5, 0);
                        layoutParams9.height = n7;

                    textView5.setLayoutParams(layoutParams9);
                    textView5.setTag(i);
                    textView5.setGravity(16);
                    textView5.setGravity(1);
                    textView5.setGravity(17);
                    textView5.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
                    textView5.setId(i);
                    textView5.setOnClickListener(this);

                    textView5.setBackgroundColor(Color.parseColor("#644181"));
                    linearLayout2.addView(textView5);
                    final LinearLayout.LayoutParams layoutParams10 = (LinearLayout.LayoutParams)textView5.getLayoutParams();
                    layoutParams10.gravity = Gravity.CENTER; //17
                    textView5.setLayoutParams(layoutParams10);
                    courseAdded = true;
                }

                if (course.getIsOpenForRegistration() && !courseAdded) {
                    final TextView textView2 = new TextView(this);
                    final int id2 = course.getPosition();
                    textView2.setText(course.getTitle());
                    textView2.setTextColor(Color.parseColor("#ffffff"));
                    final LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);

                        layoutParams3.setMargins(5, 5, 5, 0);
                        layoutParams3.height = n7;

                    textView2.setLayoutParams(layoutParams3);
                    textView2.setTag(id2);
                    textView2.setGravity(16);
                    textView2.setGravity(1);
                    textView2.setGravity(17);
                    textView2.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
                    textView2.setId(id2);
                    textView2.setOnClickListener(this);

                    textView2.setBackgroundColor(Color.parseColor("#fcd054"));
                    textView2.setTextColor(Color.parseColor("#000000"));
                    linearLayout2.addView(textView2);
                    final LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams)textView2.getLayoutParams();
                    layoutParams4.gravity = Gravity.CENTER; //17
                    textView2.setLayoutParams(layoutParams4);
                    courseAdded = true;
                }

                if (!courseAdded) {

                        final TextView textView = new TextView(this);
                        final int id = course.getPosition();
                        textView.setText(course.getTitle());
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                        if (i != length) {
                            layoutParams.setMargins(5, 0, 5, 0);
                            layoutParams.height = n7;
                        }
                        else {
                            layoutParams.setMargins(5, 5, 5, 0);
                            layoutParams.height = n7;
                        }
                        textView.setLayoutParams(layoutParams);
                        textView.setTag(id);
                        textView.setGravity(16);
                        textView.setGravity(1);
                        textView.setGravity(17);
                        textView.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
                        textView.setId(id);
                        textView.setOnClickListener(this);

                        textView.setBackgroundColor(Color.parseColor("#893f4e"));
                        linearLayout2.addView(textView);
                        final LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)textView.getLayoutParams();
                        layoutParams2.gravity = Gravity.CENTER; //17
                        textView.setLayoutParams(layoutParams2);

                }
            }
        }


        this.findViewById(R.id.zoom).setOnClickListener(new View.OnClickListener() { //2131624053


            public void onClick(final View view) {
                MainActivityZoomOut.this.startActivity(new Intent(MainActivityZoomOut.this, (Class)MainActivity.class));
            }
        });
        this.findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() { //2131624054

            public void onClick(final View view) {
                MainActivityZoomOut.this.startActivity(new Intent(MainActivityZoomOut.this, (Class)Settings.class));
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (this.getIntent().getIntExtra("intVariableName", 1) == 0) {
            final int int1 = this.prefs.getInt("opencount", 1);
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
        final String string = this.prefs.getString("notifydate", "00/00/0000");
        final Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0); //11
        instance.set(Calendar.MINUTE, 0); //12
        instance.set(Calendar.SECOND, 1); //13
        instance.set(Calendar.MILLISECOND, 0); //14
        final Date time = instance.getTime();
        instance.getTimeInMillis();
        final String[] split = string.split("/");
        final String s = split[2];
        final String s2 = split[0];
        final String s3 = split[1];
        final int int2 = Integer.parseInt(s);
        final int int3 = Integer.parseInt(s2);
        final int int4 = Integer.parseInt(s3);
        instance.set(Calendar.YEAR, int2); //1
        instance.set(Calendar.MONTH, int3); //2
        instance.set(Calendar.DATE, int4 - 1); //5
        final Date time2 = instance.getTime();
        if (time2.before(time)) {
            final SharedPreferences.Editor edit = this.prefs.edit();
            final Calendar instance2 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance2.set(Calendar.HOUR_OF_DAY, 0); //11
            instance2.set(Calendar.MINUTE, 0); //12
            instance2.set(Calendar.SECOND, 0); //13
            instance2.set(Calendar.MILLISECOND, 0); //14
            int value = Calendar.getInstance().get(Calendar.MONTH); //2
            int value2 = Calendar.getInstance().get(Calendar.YEAR); //1
            int n2 = 0;
            int n3 = 0;
            Log.w("THIS YEAR", String.valueOf(value));
            if (value < 4) {
                n2 = 4;
                n3 = value2;
                Log.w("THIS Month", "<4: year post is: " + n3);
            }
            else if (value < 10) {
                n2 = 11;
                n3 = value2;
                Log.w("THIS Month", "<10: year post is: " + n3);
            }
            else if (value == 11) {
                n2 = 4;
                n3 = value2 + 1;
                Log.w("THIS YEAR (bottom)", "True" + n3);
            }
            final AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE); //"alarm"
            final Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            intent.addCategory("android.intent.category.DEFAULT");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
            final Calendar instance3 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance3.set(Calendar.DATE, 1); //5
            instance3.set(Calendar.MONTH, n2); //2
            instance3.set(Calendar.YEAR, n3); //1
            instance3.set(Calendar.HOUR_OF_DAY, 10); //11
            instance3.set(Calendar.MINUTE, 0); //12
            instance3.set(Calendar.SECOND, 0); //13
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, instance3.getTimeInMillis(), broadcast);
            }
            else {
                alarmManager.set(0, instance3.getTimeInMillis(), broadcast);
            }
            edit.putString("notifydate", String.valueOf(n2) + "/01/" + String.valueOf(n3)).commit();
            Log.w("NEW ALERT SET DATE", String.valueOf(n2) + "/01/" + String.valueOf(n3));
            Log.w("Date Calc", "Date specified [" + time2 + "] is before today [" + time + "]");
            return;
        }
        Log.w("Date Calc", "Date specified [" + time2 + "] is NOT before today [" + time + "]");
    }

    private void setUpAlarm() {
        String string = this.prefs.getString("notifydate", "00/00/0000");

        final Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0); //11
        instance.set(Calendar.MINUTE, 0); //12
        instance.set(Calendar.SECOND, 1); //13
        instance.set(Calendar.MILLISECOND, 0); //14
        final Date time = instance.getTime();
        instance.getTimeInMillis();
        final String[] split = string.split("/");
        final String s = split[2];
        final String s2 = split[0];
        final String s3 = split[1];
        final int int1 = Integer.parseInt(s);
        final int int2 = Integer.parseInt(s2);
        final int int3 = Integer.parseInt(s3);
        instance.set(Calendar.YEAR, int1); //1
        instance.set(Calendar.MONTH, int2); //2
        instance.set(Calendar.DATE, int3 - 1); //5
        final Date time2 = instance.getTime();
        if (time2.before(time)) {
            final SharedPreferences.Editor edit = this.prefs.edit();
            final Calendar instance2 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance2.set(Calendar.HOUR_OF_DAY, 0); //11
            instance2.set(Calendar.MINUTE, 0); //12
            instance2.set(Calendar.SECOND, 0); //13
            instance2.set(Calendar.MILLISECOND, 0); //14
            int value3 = Calendar.getInstance().get(Calendar.MONTH); //2
            int value4 = Calendar.getInstance().get(Calendar.YEAR); //1
            Log.w("THIS YEAR", String.valueOf(value3));
            int n2;
            int n3;
            if (value3 < 4) {
                final int n = 4;
                Log.w("THIS Month", "<4: year post is: " + value4);
                n2 = value4;
                n3 = n;
            } else if (value3 < 11) {
                final int n4 = 11;
                n2 = value4;
                Log.w("THIS Month", "<10: year post is: " + n2);
                n3 = n4;
            } else {
                final int n5 = 4;
                final int n6 = value4 + 1;
                Log.w("THIS YEAR", "TRUE " + n6);
                n3 = n5;
                n2 = n6;
            }
            final AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE); //"alarm"
            final Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            intent.addCategory("android.intent.category.DEFAULT");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
            final Calendar instance3 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            instance3.set(Calendar.DATE, 1); //5
            instance3.set(Calendar.MONTH, n3); //2
            instance3.set(Calendar.YEAR, n2); //1
            instance3.set(Calendar.HOUR_OF_DAY, 10); //11
            instance3.set(Calendar.MINUTE, 0); //12
            instance3.set(Calendar.SECOND, 0); //13
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, instance3.getTimeInMillis(), broadcast);
            } else {
                alarmManager.set(0, instance3.getTimeInMillis(), broadcast);
            }
            edit.putString("notifydate", String.valueOf(n3) + "/01/" + String.valueOf(n2)).commit();
            Log.w("NEW ALERT SET DATE", String.valueOf(n3) + "/01/" + String.valueOf(n2));
            Log.w("Date Calc", "Date specified [" + time2 + "] is before today [" + time + "]");
        } else {
            Log.w("Date Calc", "Date specified [" + time2 + "] is NOT before today [" + time + "]");
        }
    }


}
