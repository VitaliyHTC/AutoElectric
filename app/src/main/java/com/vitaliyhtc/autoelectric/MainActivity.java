package com.vitaliyhtc.autoelectric;

import android.content.Intent;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    SlidingTabLayout slidingTabLayout;
    int numberOfTabs = 3;
    //set tabs titles in onCreate() method
    CharSequence tabsTitles[]=new CharSequence[numberOfTabs];

    public static String sDefSystemLanguage=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sDefSystemLanguage==null){sDefSystemLanguage = Locale.getDefault().getLanguage();}
        setLanguage();
        setContentView(R.layout.main_activity);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.alt_icon);

        //set tabs titles
        tabsTitles[0] = (CharSequence)getString(R.string.main_tab_calculators);
        tabsTitles[1] = (CharSequence)getString(R.string.main_tab_resources);
        tabsTitles[2] = (CharSequence)getString(R.string.main_tab_other);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        viewPagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(),tabsTitles,numberOfTabs);
        // Assigning ViewPager View and setting the adapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);
        // Assiging the Sliding Tab Layout View
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setDistributeEvenly(false); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        slidingTabLayout.setViewPager(viewPager);

    }

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
