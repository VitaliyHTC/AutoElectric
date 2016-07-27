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
import com.vitaliyhtc.autoelectric.calc.CalcOhm;
import com.vitaliyhtc.autoelectric.calc.CalcPower;
import com.vitaliyhtc.autoelectric.calc.CalcVoltDrop;
import com.vitaliyhtc.autoelectric.calc.ConvEngine;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 24.06.2016.
 */
public class MainTab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_1,container,false);

        final ArrayList<MainTabListItem> mainTabListItems = generateData();
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

    private ArrayList<MainTabListItem> generateData(){
        ArrayList<MainTabListItem> models = new ArrayList<MainTabListItem>();
        models.add(new MainTabListItem(R.drawable.list_ohm,getString(R.string.list_calc_ohm), MainListItemType.Calculator, CalcOhm.class, null));
        models.add(new MainTabListItem(R.drawable.list_base_calculations,getString(R.string.list_MainBaseCalculationsList), MainListItemType.ItemsList, MainBaseCalculationsList.class, null));
        models.add(new MainTabListItem(R.drawable.list_wire,getString(R.string.list_calc_voltdrop), MainListItemType.Calculator, CalcVoltDrop.class, null));
        models.add(new MainTabListItem(R.drawable.list_power,getString(R.string.list_calc_power), MainListItemType.Calculator, CalcPower.class, null));
        models.add(new MainTabListItem(R.drawable.list_scania_v8,getString(R.string.list_conv_engine), MainListItemType.Calculator, ConvEngine.class, null));



        //models.add(new MainTabListItem(R.drawable.list_trollface,getString(R.string.list_conv_energy), MainListItemType.Calculator, ConvEnergy.class, null));
        //models.add(new MainTabListItem(R.drawable.list_iso_trailer,getString(R.string.iso_trailer), MainListItemType.ResourcesWebView, null, "iso_trailer.htm"));
        return models;
    }
}
