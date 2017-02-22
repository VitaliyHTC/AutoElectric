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

public class MainTab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_list,container,false);

        final ArrayList<MainListItem> mainListItems = MainListItemsConfig.generateListForMainTab1(getContext());
        MainListAdapter mainListAdapter = new MainListAdapter(getContext(), mainListItems);
        final ListView myListView = (ListView) view.findViewById(R.id.listView);
        myListView.setAdapter(mainListAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainListItem mainListItem = mainListItems.get(position);
                        MainListItemType mainListItemType = mainListItem.getMainListItemType();
                        if(mainListItemType.equals(MainListItemType.Calculator) ||
                                mainListItemType.equals(MainListItemType.ItemsList)){
                            Class targetActivityClass = mainListItem.getTargetActivityClass();
                            Intent intent = new Intent(getActivity(), targetActivityClass);
                            startActivity(intent);
                        }else if(mainListItemType.equals(MainListItemType.ResourcesWebView)){
                            Intent intent = new Intent(getActivity(), ResourcesWebView.class);
                            intent.putExtra("targetSource", mainListItem.getTargetSource());
                            intent.putExtra("targetTitle", mainListItem.getTitle());
                            startActivity(intent);
                        }else{
                            Toast.makeText(getContext(), "Ooops! There is no such ListItem Type!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }

}
