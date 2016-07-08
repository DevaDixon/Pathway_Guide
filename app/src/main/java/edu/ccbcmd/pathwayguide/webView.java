package edu.ccbcmd.pathwayguide;

/**
 * Created by dixo8 on 6/24/2016.
 */



import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.InetAddress;

public class webView extends AppCompatActivity
{

    private Context c;
    private boolean isConnected;


    public static /* synthetic */ boolean access$000(final webView webView) {
        return webView.isConnected;
    }

    public void onCreate(final Bundle bundle) {

        super.onCreate(bundle);
        this.setContentView(R.layout.activity_web_view); //2130968623
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final String stringExtra = this.getIntent().getStringExtra("url");
        this.getSupportActionBar().setHomeButtonEnabled(true);
        final WebView webView = (WebView)this.findViewById(R.id.webView); //2131624059
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {

                webView.loadData("<h1 style='font-size:40px'>MESSAGE 2</h1><h4>Course Description could not be loaded. Please check your internet connection and try again</h4>", "text/html", "utf-8");
            }

            @TargetApi(23)
            public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {

                this.onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
            }

            public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                boolean booleanValue = false;

               if (!access$000(webView.this)) {
                    webView.loadData("<h1>MESSAGE 1</h1><h4>Course Description could not be loaded. Please check your internet connection and try again</h4>", "text/html", "utf-8");
                    return true;
                }
                return booleanValue;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollContainer(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollbarFadingEnabled(false);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        else {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        }
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/4.0");
        webView.loadUrl(stringExtra);
        Log.w("url:", stringExtra);
    }

    public void onResume() {


            super.onResume();
            this.c = this;
            if (this.c.getSystemService(CONNECTIVITY_SERVICE) != null) { //"connectivity"
                try {
                    if (InetAddress.getByName("google.com").equals("")) {
                        this.isConnected = false;
                        return;
                    }
                }
                catch (Exception ex) {
                    this.isConnected = false;
                    return;
                }
                this.isConnected = true;
            }

    }
}
