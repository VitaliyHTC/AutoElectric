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

import com.vitaliyhtc.autoelectric.activity.ResourcesWebView;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 24.06.2016.
 */
public class MainTab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_tab_2,container,false);

        final ArrayList<MainTab2ListItem> mainTab2ListItems = generateData();
        MainTab2ListAdapter mainTab2ListAdapter = new MainTab2ListAdapter(getContext(), mainTab2ListItems);
        final ListView myList = (ListView) view.findViewById(R.id.listView);
        myList.setAdapter(mainTab2ListAdapter);

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), ResourcesWebView.class);
                        intent.putExtra("targetSource", mainTab2ListItems.get(position).getTargetSource());
                        intent.putExtra("targetTitle", mainTab2ListItems.get(position).getTitle());
                        startActivity(intent);
                    }
                });

        return view;
    }

    private ArrayList<MainTab2ListItem> generateData(){
        ArrayList<MainTab2ListItem> models = new ArrayList<MainTab2ListItem>();
        models.add(new MainTab2ListItem(R.drawable.list_trollface,getString(R.string.index_htm), "index.htm"));
        models.add(new MainTab2ListItem(R.drawable.list_iso_trailer,getString(R.string.iso_trailer), "iso_trailer.htm"));

        //models.add(new MainTab2ListItem(R.drawable.list_trollface,getString(R.string.index_htm), "index.htm"));
        return models;
    }
}