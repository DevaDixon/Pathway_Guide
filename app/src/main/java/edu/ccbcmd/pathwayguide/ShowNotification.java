package edu.ccbcmd.pathwayguide;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ShowNotification extends Service
{

    private static final String TAG = "ShowNotification";


    public IBinder onBind(final Intent intent) {

        return null;
    }

    public void onCreate() {

        super.onCreate();
        ((NotificationManager)this.getSystemService(NOTIFICATION_SERVICE)).notify(0, new NotificationCompat.Builder(this).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class)MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT)).setContentTitle(("HELLO " + System.currentTimeMillis())).setContentText("PLEASE CHECK WE HAVE UPDATED NEWS").setDefaults(-1).setTicker("ticker message").setWhen(System.currentTimeMillis()).build()); //"notification",  134217728
        Log.i("ShowNotification", "Notification created");
    }
}
