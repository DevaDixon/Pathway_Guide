package edu.ccbcmd.pathwayguide;


import java.net.InetAddress;

import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.widget.Toast;
        import android.annotation.TargetApi;

        import android.webkit.WebResourceError;

        import android.webkit.WebResourceRequest;

        import android.webkit.WebViewClient;
        import android.webkit.WebView;
        import java.util.Calendar;
import java.util.List;

import android.content.DialogInterface;

        import android.widget.Button;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.Color;
        import android.util.Log;
        import android.widget.TextView;
        import android.graphics.Point;

        import android.view.MenuItem;

        import android.net.Uri;

        import android.graphics.Bitmap;

        import android.view.View;
        import android.content.Intent;
        import android.os.Bundle;

        import android.content.SharedPreferences;
        import android.widget.ProgressBar;
        import android.content.Context;

        import android.support.v7.app.AppCompatActivity;


public class info extends AppCompatActivity
{

    private Context c;
    private boolean isConnected;
    private ProgressBar mPbar;
    public SharedPreferences prefs;


    public static /* synthetic */ boolean access$000(final info info) {

        return info.isConnected;
    }

    public static /* synthetic */ ProgressBar access$100(final info info) {

        return info.mPbar;
    }


    private int getScale() {

        final Point point = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(point);
        return (int)((point.x / 355.0) * 100.0);
    }

    private CourseClassLoader loader;

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);

        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        final String string = this.prefs.getString("choosenID", "0");
        final int int3 = Integer.parseInt(string);
        c = getApplicationContext();

        this.mPbar = (ProgressBar)this.findViewById(R.id.progressBar2); //2131624040

        loader = new CourseClassLoader(getApplicationContext());
        final CourseClass course = loader.getXMLOrder(int3);

        boolean doubleCourse = course.getIsDoubleCourse();

        if(doubleCourse){
            Intent intent = new Intent(c,InfoForDoubleCourses.class);
            startActivity(intent);
            return;
        }

        this.setContentView(R.layout.activity_info); //2130968613
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ((TextView)this.findViewById(R.id.textView)).setText(course.getFullTitle()); //2131624036
        this.getSupportActionBar().setTitle(course.getTitle());

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


        final Button button = (Button) findViewById(R.id.button);
        if (course.getAnyPreReqs()) {
            button.setText("Register");
        }
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

                if (course.getMeetWithAdvisor()) {
                    info.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://simon.ccbcmd.edu/pls/PROD/twbkwbis.P_WWWLogin")));
                    return;
                }
               info.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://simon.ccbcmd.edu/pls/PROD/twbkwbis.P_WWWLogin")));
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


        final String[] courseLabels = loader.getCourseLabels();

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

                if (course.getDone()) {

                    DatabaseWrapper.writeClassStatus(courseLabels[int3],0);
                    info.this.startActivity(new Intent(info.this, (Class)MainActivity.class));
                    return;
                }

                if (course.getInProgress()) {
                    info.this.startActivity(new Intent(info.this, (Class)alert.class));
                    return;
                }




                if (course.getIsOpenForRegistration()) {
                    //editorIP.putBoolean(courseLabels[int3],true);
                    //editorIP.apply();
                    DatabaseWrapper.writeClassStatus(courseLabels[int3],1);
                    info.this.startActivity(new Intent(info.this, (Class)MainActivity.class));
                    return;
                }

                if (course.getAnyPreReqs()) {
                    SharedPreferences pathwayPermission = getApplicationContext().getSharedPreferences("permission",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pathwayPermission.edit();
                    editor.putBoolean("permission"+course.getTitle(),true);
                    editor.apply();
                    info.this.startActivity(new Intent(info.this, (Class)MainActivity.class));
                    return;
                }

                info.this.startActivity(new Intent(info.this, (Class)MainActivity.class));
            }
        });

        String value2;
        if ( course.getTitle().length()< 7)
            value2 = String.valueOf("http://www.ccbcmd.edu/Programs-and-Courses-Finder/course/"+course.getTitle().substring(0,4));
        else
            value2 = String.valueOf("http://www.ccbcmd.edu/Programs-and-Courses-Finder/course/"+ course.getTitle().substring(0,4) + "/" + course.getTitle().substring(4,7));
        this.getSupportActionBar().setHomeButtonEnabled(true);
        final WebView webView = (WebView)this.findViewById(R.id.descriptionwebview);
        webView.loadData("<h1>Loading, please wait...</h1>", "text/html", "utf-8");

        // Here we implement the data on/off feature.
        final Integer value3 = info.this.prefs.getInt("internet", 1);
        if (value3 == 1) {
            webView.loadUrl(value2);
        } else {
            webView.loadData("<h1 >Internet Use Disabled</h1><h3>You have disabled internet. To view course descriptions, go to internet settings found in the menu.</h3>", "text/html", "utf-8");
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
}