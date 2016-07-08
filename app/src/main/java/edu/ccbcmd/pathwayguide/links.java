package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */



import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class links extends AppCompatActivity
{

    public SharedPreferences prefs;


    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_links); //2130968615
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("Links");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.findViewById(R.id.settings).setVisibility(View.INVISIBLE); //2131624052, 4
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_settings, R.id.settings, new String[] { "CCBC", "Blackboard", "My CCBC", "Simon", "Book Store", "Meet With An Advisor", "Resources For Students", "Course Catalog", "Flex-Reg", "Tutoring and Academic Coaching", "Disability Programs and Services" }); //2130968621, 2131624052
        final ListView listView = (ListView)this.findViewById(R.id.settingslist); //2131624050
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {

                switch (n) {

                    case 0: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://ccbcmd.edu")));
                        break;
                    }
                    case 1: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://ccbcmd-bb.blackbaord.com")));
                        break;
                    }
                    case 2: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://myccbc.ccbcmd.edu/_layouts/ccbc/default.aspx?ReturnUrl=%2f_layouts%2fAuthenticate.aspx%3fSource%3d%252F&Source=%2F")));
                        break;
                    }
                    case 3: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://simon.ccbcmd.edu/pls/PROD/twbkwbis.P_WWWLogin")));
                        break;
                    }
                    case 4: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.bookstore.ccbcmd.edu/catonsville/main/")));
                        break;
                    }
                    case 5: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/resources-for-students/academic-advisement")));
                        break;
                    }
                    case 6: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/resources-for-students/")));
                        break;
                    }
                    case 7: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://catalog.ccbcmd.edu/")));
                        break;
                    }
                    case 8: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Registering-for-Classes/Flexreg.aspx")));
                        break;
                    }
                    case 9: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Tutoring-and-Academic-Coaching.aspx")));
                        break;
                    }
                    case 10: {
                        links.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.ccbcmd.edu/Resources-for-Students/Disability-Programs-and-Services.aspx")));
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });
    }
}
