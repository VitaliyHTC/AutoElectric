package com.vitaliyhtc.autoelectric.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;

import com.vitaliyhtc.autoelectric.R;

/**
 * Created by VitaliyHTC on 12.07.2016.
 */
public class ResourcesWebView extends AppCompatActivity {
    private WebView webView;

    @SuppressLint(value={"SetJavaScriptEnabled"})
    @TargetApi(value=16)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        String targetSource = intent.getStringExtra("targetSource");
        String targetTitle = intent.getStringExtra("targetTitle");
        if(targetTitle != null){
            getSupportActionBar().setTitle(targetTitle);
        }
        this.webView = (WebView)this.findViewById(R.id.ResourcesWebView);

        this.webView.getSettings().setJavaScriptEnabled(true);
        if(Build.VERSION.SDK_INT >= 16) {
            this.webView.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("Zoom_Enabled",true)){
            this.webView.getSettings().setBuiltInZoomControls(true);
        }
        if(sharedPreferences.getBoolean("keepScreenOn", false)){
            this.getWindow().addFlags(128);
        }else{
            this.getWindow().clearFlags(128);
        }
        if(savedInstanceState != null){
            this.webView.restoreState(savedInstanceState);
            return;
        }

        this.webView.loadUrl("file:///android_asset/" + targetSource);
    }

    @Override
    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (n2 == 4 && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(n2, keyEvent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        this.webView.saveState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
