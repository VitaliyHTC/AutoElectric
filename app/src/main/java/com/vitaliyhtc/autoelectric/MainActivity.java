package com.vitaliyhtc.autoelectric;

import android.content.Intent;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.vitaliyhtc.autoelectric.lib.SlidingTabLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int numberOfTabs = 3;

    private CharSequence tabsTitles[]=new CharSequence[numberOfTabs];

    public static String sDefSystemLanguage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sDefSystemLanguage==null){sDefSystemLanguage = Locale.getDefault().getLanguage();}
        setLanguage();
        setContentView(R.layout.main_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.alt_icon);



        tabsTitles[0] = (CharSequence)getString(R.string.main_tab_calculators);
        tabsTitles[1] = (CharSequence)getString(R.string.main_tab_resources);
        tabsTitles[2] = (CharSequence)getString(R.string.main_tab_other);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabsTitles, numberOfTabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setDistributeEvenly(false);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        slidingTabLayout.setViewPager(viewPager);

    }

    // String locale = context.getResources().getConfiguration().locale.getDisplayName();
    // https://developer.android.com/guide/topics/resources/localization.html
    private void setLanguage() {
        String language = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Language", "default");
        if (language.equals("default")) {
            if(sDefSystemLanguage!=null){
                language = sDefSystemLanguage;
            }else{
                language = Locale.getDefault().getLanguage();
            }
        }
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info) {
            Intent intent = new Intent(MainActivity.this, MainInfoActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
