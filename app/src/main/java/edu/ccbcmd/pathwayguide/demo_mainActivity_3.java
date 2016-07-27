package edu.ccbcmd.pathwayguide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class demo_mainActivity_3 extends AppCompatActivity implements View.OnClickListener
{

    public Button b;
    public Button b1;
    public Button b2;
    public Button b3;



    private void presentShowcaseSequence(final int delay) {

        final MaterialShowcaseSequence materialShowcaseSequence = new MaterialShowcaseSequence(this);
        materialShowcaseSequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {

            public void onShow(final MaterialShowcaseView materialShowcaseView, final int n) {

            }
        });
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(this).setDelay(delay).setTarget(this.b2).setDismissText("Next").setContentText("Notice <put class name here> has turned green, indicating you have passed the class!").setTitleText("Good Job!").withRectangleShape().setShapePadding(pxToDp(5)).setMaskColour(Color.parseColor("#F1335075")).build());
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(this).setTarget(new View(this)).setDismissText("Let's Go!").setTitleText("Now Try Yourself").setContentText("Try to update <put course name here> to indicate that your are currently taking it!").withRectangleShape().setShapePadding(pxToDp(5)).setMaskColour(Color.parseColor("#F1335075")).build());
        materialShowcaseSequence.start();
    }

    public static int pxToDp(final int n) {

        return (int)(n * Resources.getSystem().getDisplayMetrics().density);
    }

    public void onClick(final View view) {

        new MaterialShowcaseView.Builder(this).setTarget(this.b1).withRectangleShape().setDismissText("Okay").setTitleText("Click Here").setContentText("Click on <put class name here> to open the classes detail window.").setShapePadding(pxToDp(5)).setMaskColour(Color.parseColor("#F1335075")).setDelay(100).show();
    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_demo_main_activity_2); //2130968611
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getSupportActionBar().hide();
        final Resources resources = this.getResources();
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout2); //2131624046
        this.findViewById(R.id.button2).setOnClickListener(this); //2131624022
        final float density = this.getResources().getDisplayMetrics().density;
        final int n = (int)(13 * density);
        final int n2 = (int)(2.2 * density);
        (this.b = new Button(this)).setText("CLASS 1");
        this.b.setTextColor(Color.parseColor("#ffffff"));
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.b.setPadding(n, n, n, n);
        this.b.setGravity(16);
        this.b.setGravity(1);
        this.b.setGravity(17);
        this.b.setTextSize(1, 14.0f);
        this.b.setTypeface(null, 1);
        layoutParams.setMargins(n2, n2, n2, n2);
        this.b.setLayoutParams(layoutParams);
        this.b.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
        this.b.setId(0);
        this.b.setBackgroundColor(Color.parseColor("#893f4e"));
        this.b.setOnClickListener(this);
        linearLayout.addView(this.b);
        final LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)this.b.getLayoutParams();
        layoutParams2.gravity = Gravity.CENTER; //17
        this.b.setLayoutParams(layoutParams2);
        (this.b1 = new Button(this)).setText("CLASS 2");
        final LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        this.b1.setPadding(n, n, n, n);
        this.b1.setGravity(16);
        this.b1.setGravity(1);
        this.b1.setGravity(17);
        this.b1.setTextSize(1, 14.0f);
        this.b1.setTypeface(null, 1);
        layoutParams3.setMargins(n2, n2, n2, n2);
        this.b1.setLayoutParams(layoutParams3);
        this.b1.setId(1);
        this.b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {

                demo_mainActivity_3.this.startActivity(new Intent(demo_mainActivity_3.this, (Class)demo_info_2.class));
            }
        });
        this.b1.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
        this.b1.setBackgroundColor(Color.parseColor("#644181"));
        linearLayout.addView(this.b1);
        final LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams)this.b1.getLayoutParams();
        layoutParams4.gravity = Gravity.CENTER; //17
        this.b1.setLayoutParams(layoutParams4);
        (this.b2 = new Button(this)).setText("CLASS 3");
        this.b2.setTextColor(Color.parseColor("#ffffff"));
        this.b2.setId(2);
        final LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        this.b2.setPadding(n, n, n, n);
        this.b2.setGravity(16);
        this.b2.setGravity(1);
        this.b2.setGravity(17);
        this.b2.setTextSize(1, 14.0f);
        this.b2.setTypeface(null, 1);
        layoutParams5.setMargins(n2, n2, n2, n2);
        this.b2.setLayoutParams(layoutParams5);
        this.b2.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
        this.b2.setBackgroundColor(Color.parseColor("#159b8a"));
        linearLayout.addView(this.b2);
        final LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams)this.b2.getLayoutParams();
        layoutParams6.gravity = Gravity.CENTER; //17
        this.b2.setLayoutParams(layoutParams6);
        this.b2.setOnClickListener(this);
        (this.b3 = new Button(this)).setText("CLASS 4");
        this.b3.setTextColor(Color.parseColor("#ffffff"));
        this.b3.setId(3);
        final LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        this.b3.setPadding(n, n, n, n);
        this.b3.setGravity(16);
        this.b3.setGravity(1);
        this.b3.setGravity(17);
        this.b3.setTextSize(1, 14.0f);
        this.b3.setTypeface(null, 1);
        layoutParams7.setMargins(n2, n2, n2, n2);
        this.b3.setLayoutParams(layoutParams7);
        this.b3.setWidth(Math.round(TypedValue.applyDimension(1, 100.0f, resources.getDisplayMetrics())));
        this.b3.setBackgroundColor(Color.parseColor("#159b8a"));
        linearLayout.addView(this.b3);
        final LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams)this.b3.getLayoutParams();
        layoutParams8.gravity = Gravity.CENTER; //17
        this.b3.setLayoutParams(layoutParams8);
        this.b3.setOnClickListener(this);
    }

    public void onResume() {

        super.onResume();
        this.presentShowcaseSequence(500);
    }
}
