package com.surahcube.app;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            webView = new WebView(this);
            WebSettings s = webView.getSettings();
            s.setJavaScriptEnabled(true);
            s.setDomStorageEnabled(true);      // surah text cache (localStorage)
            s.setAllowFileAccess(true);
            s.setMediaPlaybackRequiresUserGesture(false);

            webView.setWebViewClient(new WebViewClient());
            // allow the in-page geolocation (used for local Fajr time)
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onGeolocationPermissionsShowPrompt(String origin,
                        GeolocationPermissions.Callback callback) {
                    callback.invoke(origin, true, false);
                }
            });
            setContentView(webView);

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }

            webView.loadUrl("file:///android_asset/index.html");
        } catch (Throwable t) {
            showError(t);
        }
    }

    /** Show a readable error instead of crashing ("keeps stopping"). */
    private void showError(Throwable t) {
        TextView tv = new TextView(this);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        int pad = (int) (24 * getResources().getDisplayMetrics().density);
        tv.setPadding(pad, pad, pad, pad);
        tv.setBackgroundColor(Color.rgb(11, 16, 32));
        StringBuilder sb = new StringBuilder("Surah Cube could not start.\n\n");
        sb.append(t.getClass().getSimpleName()).append(": ").append(t.getMessage()).append("\n\n");
        for (StackTraceElement el : t.getStackTrace()) {
            sb.append("  at ").append(el.toString()).append("\n");
        }
        sb.append("\nIf this mentions WebView, please install/update "
                + "'Android System WebView' or Chrome from the Play Store.");
        tv.setText(sb.toString());
        ScrollView sv = new ScrollView(this);
        sv.addView(tv);
        setContentView(sv);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
