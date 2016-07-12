package edu.ccbcmd.pathwayguide;


import android.app.Activity;
import android.app.Application;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.InetAddress;


public class InfoForDoubleCourses extends AppCompatActivity
{

    private Context c;
    private boolean isConnected;
    private ProgressBar mPbar;
    public SharedPreferences prefs;


    public static /* synthetic */ boolean access$000(final InfoForDoubleCourses info) {

        return info.isConnected;
    }

    public static /* synthetic */ ProgressBar access$100(final InfoForDoubleCourses info) {

        return info.mPbar;
    }


    private CourseClassLoader loader;

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_info_for_double_courses); //2130968613
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        final String string = this.prefs.getString("choosenID", "0");
        final int int3 = Integer.parseInt(string);
        c = getApplicationContext();

        this.mPbar = (ProgressBar)this.findViewById(R.id.progressBar2); //2131624040

        loader = new CourseClassLoader(getApplicationContext());
        final CourseClass course = loader.getXMLOrder(int3);
        ((TextView)this.findViewById(R.id.textView)).setText(course.getFullTitle()); //2131624036


        if(course.getIsDoubleCourse()){

            this.getSupportActionBar().setTitle("Select Course"); //Green

            String[] coursesDouble = course.getDoubleCourse();

            final CourseClass[] data = new CourseClass[coursesDouble.length];
            for(int i = 0; i < coursesDouble.length; i++){
                data[i] = loader.getCourseByName(coursesDouble[i], c);
            }
            CourseAdapter adapter = new CourseAdapter(c, R.layout.list_view_header_row, data);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO MUST FIX THIS! IT MUST FIND THE NAME OF THE course AND FIND THAT course TO PASS ON.

                    CourseClass newCourse = data[position];
                    runRest(newCourse);
                }
            });
        }
        else {
            runRest(course);
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
                        this.startActivity(new Intent(this, (Class)MainActivity.class));
                        return true;
                    }
                    if (value == 1) {
                        this.startActivity(new Intent(this, (Class)MainActivityZoomOut.class));
                        return true;
                    }
                    break;
                }
            }

        return booleanValue;
    }

    public void onResume() {


            super.onResume();
            this.c = this;
            if (this.c.getSystemService(CONNECTIVITY_SERVICE) != null) { //"connectivity"
                try {
                    if (InetAddress.getByName("google.com").equals("")) {
                        return;
                    }
                }
                catch (Exception ex) {}
            }

    }

    public void runRest(final CourseClass course){
        ((TextView)findViewById(R.id.textView)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.colorChange)).setVisibility(View.VISIBLE);
        ((WebView)findViewById(R.id.descriptionwebview)).setVisibility(View.VISIBLE);
        ((ProgressBar)findViewById(R.id.progressBar2)).setVisibility(View.INVISIBLE);
        ((ListView)findViewById(R.id.listView)).setVisibility(View.INVISIBLE);

        this.getSupportActionBar().setTitle(course.getTitle());
        ((TextView)this.findViewById(R.id.textView)).setText(course.getFullTitle());

        if (course.getDone()) {
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#159b8a"))); //Green

        }
        else if (course.getInProgress()) {
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#644181"))); //Purple

        }
        else if (course.getIsOpenForRegistration()) {
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fcd054"))); //Yellow
        }

        else {
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#893f4e"))); //RED
        }

        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);


        final Button button = (Button) findViewById(R.id.button); //2131624037
        if (course.getMeetWithAdvisor()) {
            button.setText("Meet with an Adviser"); //WHY IS THIS BUTTON MEET WITH AN ADVISOR? MAYBE I"M NOT FOLLOWING.
        }
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

                final String replace = course.getTitle().replace(" ", "/");

                if (course.getMeetWithAdvisor()) {
                    InfoForDoubleCourses.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Academic-Advisement.aspx")));
                    return;
                }
                InfoForDoubleCourses.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Programs-and-Courses-Finder/course/" + replace)));
            }
        });


        final Button button2 = (Button)this.findViewById(R.id.colorChange); //2131624038
        if (course.getDone()) {
            button.setVisibility(View.INVISIBLE); //4
            button2.setText("I have not successfully completed this class");
        }
        else if (course.getInProgress()) {
            button2.setText("Class End Results");
        }
        else if (course.getIsOpenForRegistration()) {
            button2.setText("I am currently taking this class");
        }
        else if (course.getAnyPreReqs()) {
            button2.setText("I have permission to take this class");
        }

        //Getting a handle for the shared preference editor
        SharedPreferences sharedPrefDone = getSharedPreferences("courses", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefIP = getSharedPreferences("coursesInProgress", Context.MODE_PRIVATE);
        //The fifth instance of sharedpreferences is to get the double class status
        SharedPreferences pathwayDoubleCourse = getSharedPreferences("DoubleCourse",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorDone = sharedPrefDone.edit();
        final SharedPreferences.Editor editorIP = sharedPrefIP.edit();
        final SharedPreferences.Editor editorDblClass = pathwayDoubleCourse.edit();


        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

                if (course.getDone()) {
                    editorDblClass.putString(("Double"+"GENMATH"),course.getTitle());
                    editorDblClass.apply();
                    editorDone.putBoolean(course.getTitle(), false);
                    editorDone.apply();
                    InfoForDoubleCourses.this.startActivity(new Intent(InfoForDoubleCourses.this, (Class)MainActivity.class));
                    return;
                }

                if (course.getInProgress()) {
                    editorDblClass.putString(("Double"+"GENMATH"),course.getTitle());
                    editorDblClass.apply();
                    InfoForDoubleCourses.this.startActivity(new Intent(InfoForDoubleCourses.this, (Class)alert.class));
                    return;
                }




                if (course.getIsOpenForRegistration()) {
                    editorDblClass.putString(("Double"+"GENMATH"),course.getTitle());
                    editorDblClass.apply();
                    editorIP.putBoolean(course.getTitle(),true);
                    editorIP.apply();
                    InfoForDoubleCourses.this.startActivity(new Intent(InfoForDoubleCourses.this, (Class)MainActivity.class));
                    return;
                }

                if (course.getAnyPreReqs()) {
                    SharedPreferences pathwayPermission = getApplicationContext().getSharedPreferences("permission",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pathwayPermission.edit();
                    editor.putBoolean("permission"+course.getTitle(),true);
                    editor.apply();
                    InfoForDoubleCourses.this.startActivity(new Intent(InfoForDoubleCourses.this, (Class)MainActivity.class));
                    return;
                }

                InfoForDoubleCourses.this.startActivity(new Intent(InfoForDoubleCourses.this, (Class)MainActivity.class));
            }
        });

        Log.e("Webview",  course.getTitle().substring(0,4) + "/" + course.getTitle().substring(4,7));
        final String value2 = String.valueOf("http://www.ccbcmd.edu/Programs-and-Courses-Finder/course/"+ course.getTitle().substring(0,4) + "/" + course.getTitle().substring(4,7));
        this.getSupportActionBar().setHomeButtonEnabled(true);
        final WebView webView = (WebView)this.findViewById(R.id.descriptionwebview);
        webView.loadData("<h1>Loading, please wait...</h1>", "text/html", "utf-8");

        // Here we implement the data on/off feature.
        final Integer value3 = InfoForDoubleCourses.this.prefs.getInt("internet", 1);
        if (value3 == 1) {
            webView.loadUrl(value2);
        } else {
            webView.loadData("<h1 >Internet Use Disabled</h1><h3>You have disabled internet. To view course descriptions, go to internet settings found in the menu.</h3>", "text/html", "utf-8");
        }
    }

    private class CourseAdapter extends ArrayAdapter<CourseClass> {

        Context context;
        int layoutResourceId;
        CourseClass data[] = null;

        public CourseAdapter(Context context, int layoutResourceId, CourseClass[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_view_header_row, parent, false);

            TextView txtTitle = (TextView)row.findViewById(R.id.txtHeader);
            TextView txtDesc =  (TextView) row.findViewById(R.id.txtFullDesc);


            txtTitle.setText(data[position].getTitle());
            txtDesc.setText(data[position].getFullTitle());

            return row;
        }
    }


}