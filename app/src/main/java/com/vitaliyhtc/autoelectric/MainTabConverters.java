package com.vitaliyhtc.autoelectric;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vitaliyhtc.autoelectric.activity.ResourcesWebView;

import java.util.ArrayList;

public class MainTabConverters extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_tab_list);
        this.setTitle(R.string.list_MainTabConverters);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<MainListItem> mainListItems = MainListItemsConfig.generateListForMainTabConverters((Context)this);
        MainListAdapter mainListAdapter = new MainListAdapter((Context)this, mainListItems);
        final ListView myListView = (ListView) this.findViewById(R.id.listView);
        myListView.setAdapter(mainListAdapter);

        final Context context = (Context)this;

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainListItem mainListItem = mainListItems.get(position);
                        MainListItemType mainListItemType = mainListItem.getMainListItemType();
                        if(mainListItemType.equals(MainListItemType.Calculator) ||
                                mainListItemType.equals(MainListItemType.ItemsList)){
                            Class targetActivityClass = mainListItem.getTargetActivityClass();
                            Intent intent = new Intent(context, targetActivityClass);
                            startActivity(intent);
                        }else if(mainListItemType.equals(MainListItemType.ResourcesWebView)){
                            Intent intent = new Intent(context, ResourcesWebView.class);
                            intent.putExtra("targetSource", mainListItem.getTargetSource());
                            intent.putExtra("targetTitle", mainListItem.getTitle());
                            startActivity(intent);
                        }else{
                            Toast.makeText(context, "Ooops! There is no such ListItem Type!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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

}