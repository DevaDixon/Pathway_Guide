package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */



        import android.content.pm.ActivityInfo;
        import android.content.res.Resources;
        import android.app.PendingIntent;
        import android.app.AlarmManager;
        import android.os.Build;
        import android.util.Log;
        import java.util.Calendar;
        import java.util.Locale;
        import java.util.TimeZone;

        import android.widget.Button;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.BitmapFactory;

        import android.graphics.drawable.Drawable;

        import android.content.Context;

        import android.view.MenuItem;

        import android.net.Uri;

        import android.view.View;
        import android.content.Intent;
        import android.os.Bundle;

        import android.content.SharedPreferences;

        import android.support.v7.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity
{

    public SharedPreferences prefs;

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.prefs = this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
        this.setContentView(R.layout.activity_notification); //2130968619
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().show();
        this.getSupportActionBar().setTitle("It's time to register!");
        final Resources resources = this.getResources();
        this.getSupportActionBar().setHomeButtonEnabled(true);
        this.getSupportActionBar().setBackgroundDrawable(new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.header))); //2130837594
        this.findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() { //2131624057


            public void onClick(final View view) {

                NotificationActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://ccbcmd-bb.blackboard.com")));
            }
        });
        this.findViewById(R.id.cant).setOnClickListener(new View.OnClickListener() { //2131624055


            public void onClick(final View view) {

                NotificationActivity.this.startActivity(new Intent(NotificationActivity.this, (Class)cantRegister.class));
            }
        });
        this.findViewById(R.id.this1).setOnClickListener(new View.OnClickListener() { //2131624056
           
            public void onClick(final View view) {
              
                final Calendar instance = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                instance.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR)); //1, 1
                instance.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH)); //2, 2
                instance.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE)); //5, 5
                instance.add(Calendar.DATE, 1); //5
                final int value = instance.get(Calendar.MONTH); //2
                final int value2 = instance.get(Calendar.YEAR); //1
                final int value3 = instance.get(Calendar.DATE); //5
                Log.w("Notify Cal", value + "/" + value3 + "/" + value2);
                final AlarmManager alarmManager = (AlarmManager)NotificationActivity.this.getSystemService(Context.ALARM_SERVICE); //"alarm"
                final Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                intent.addCategory("android.intent.category.DEFAULT");
                final PendingIntent broadcast = PendingIntent.getBroadcast(NotificationActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT); //134217728
                final Calendar instance2 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                instance2.set(Calendar.DATE, value3); //5
                instance2.set(Calendar.MONTH, value); //2
                instance2.set(Calendar.YEAR, value2); //1
                instance2.set(Calendar.HOUR_OF_DAY, 10); //11
                instance2.set(Calendar.MINUTE, 0); //12
                instance2.set(Calendar.SECOND, 0); //13
                if (Build.VERSION.SDK_INT >= 19) {
                    alarmManager.setExact(0, instance2.getTimeInMillis(), broadcast);
                }
                else {
                    alarmManager.set(0, instance2.getTimeInMillis(), broadcast);
                }
                NotificationActivity.this.startActivity(new Intent(NotificationActivity.this, (Class)MainActivity.class));
            }
        });
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
}
