package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class splash
        extends AppCompatActivity
{
    public splash() {}

    splash(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException)
    {
        this();
    }

    public void onCreate(Bundle _paramBundle)
    {
        Object localObject = $change;
        if (localObject != null)
        {
            ((IncrementalChange)localObject).access$dispatch("onCreate.(Landroid/os/Bundle;)V", new Object[] { this, _paramBundle });
            return;
        }
        super.onCreate(_paramBundle);
        setContentView(2130968622);
        AnimationDrawable paramBundle =  new AnimationDrawable();
        paramBundle.addFrame(getResources().getDrawable(R.drawable.splash), 2000);
        paramBundle.addFrame(getResources().getDrawable(R.drawable.social), 1500);
        paramBundle.addFrame(getResources().getDrawable(R.drawable.tech), 1500);
        paramBundle.addFrame(getResources().getDrawable(R.drawable.health), 1500);
        paramBundle.addFrame(getResources().getDrawable(R.drawable.arts), 1500);
        paramBundle.addFrame(getResources().getDrawable(R.drawable.business), 2000);
        paramBundle.setOneShot(true);
        localObject = (ImageView)findViewById(2131624058);
        if (Build.VERSION.SDK_INT < 16) {
            ((ImageView)localObject).setImageDrawable(paramBundle);
        }
        for (;;)
        {
            paramBundle.start();
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    Object localObject = $change;
                    if (localObject != null)
                    {
                        ((IncrementalChange)localObject).access$dispatch("run.()V", new Object[] { this });
                        return;
                    }
                    localObject = splash.this.getSharedPreferences("com.mycompany.CCBCPathway", 0);
                    Log.w("First Run:", String.valueOf(((SharedPreferences)localObject).getBoolean("firstrun", true)));
                    if (((SharedPreferences)localObject).getBoolean("firstrun", true)) {
                        splash.this.startActivity(new Intent(splash.this, demo_MainActivity.class));
                    }
                    for (;;)
                    {
                        splash.this.finish();
                        return;
                        localObject = new Intent(splash.this, MainActivity.class);
                        ((Intent)localObject).putExtra("intVariableName", 0);
                        splash.this.startActivity((Intent)localObject);
                    }
                }
            }, 10500L);
            return;
            ((ImageView)localObject).setImageDrawable(paramBundle);
        }
    }
}
