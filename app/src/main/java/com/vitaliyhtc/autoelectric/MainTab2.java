package com.vitaliyhtc.autoelectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vitaliyhtc.autoelectric.activity.ResourcesWebView;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 24.06.2016.
 */
public class MainTab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_tab_2,container,false);

        final ArrayList<MainTabListItem> mainTabListItems = MainListItemsConfig.generateListForMainTab2(getContext());
        MainTabListAdapter mainTabListAdapter = new MainTabListAdapter(getContext(), mainTabListItems);
        final ListView myList = (ListView) view.findViewById(R.id.listView);
        myList.setAdapter(mainTabListAdapter);

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainTabListItem mainTabListItem = mainTabListItems.get(position);
                        MainListItemType mainListItemType = mainTabListItem.getMainListItemType();
                        if(mainListItemType.equals(MainListItemType.Calculator) ||
                                mainListItemType.equals(MainListItemType.ItemsList)){
                            Class targetActivityClass = mainTabListItem.getTargetActivityClass();
                            Intent intent = new Intent(getActivity(), targetActivityClass);
                            startActivity(intent);
                        }else if(mainListItemType.equals(MainListItemType.ResourcesWebView)){
                            Intent intent = new Intent(getActivity(), ResourcesWebView.class);
                            intent.putExtra("targetSource", mainTabListItem.getTargetSource());
                            intent.putExtra("targetTitle", mainTabListItem.getTitle());
                            startActivity(intent);
                        }else{
                            Toast.makeText(getContext(), "Ooops! There is no such ListItem Type!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }

}
