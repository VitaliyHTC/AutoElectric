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

import com.vitaliyhtc.autoelectric.calc.CalcCapacitorCharge;
import com.vitaliyhtc.autoelectric.calc.CalcOhm;
import com.vitaliyhtc.autoelectric.calc.CalcPower;
import com.vitaliyhtc.autoelectric.calc.CalcSerPar;
import com.vitaliyhtc.autoelectric.calc.CalcVoltDrop;
import com.vitaliyhtc.autoelectric.calc.CalcVoltageDivider;
import com.vitaliyhtc.autoelectric.calc.ConvDb;
import com.vitaliyhtc.autoelectric.calc.ConvEnergy;
import com.vitaliyhtc.autoelectric.calc.ConvEngine;
import com.vitaliyhtc.autoelectric.calc.ConvFreq;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 24.06.2016.
 */
public class MainTab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_1,container,false);

        final ArrayList<MainTab1ListItem> mainTab1ListItems = generateData();
        MainTab1ListAdapter mainTab1ListAdapter = new MainTab1ListAdapter(getContext(), mainTab1ListItems);
        final ListView myList = (ListView) view.findViewById(R.id.listView);
        myList.setAdapter(mainTab1ListAdapter);

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Class targetActivityClass = mainTab1ListItems.get(position).getTargetActivityClass();
                        Intent intent = new Intent(getActivity(), targetActivityClass);
                        startActivity(intent);
                    }
                });

        return view;
    }

    private ArrayList<MainTab1ListItem> generateData(){
        ArrayList<MainTab1ListItem> models = new ArrayList<MainTab1ListItem>();
        models.add(new MainTab1ListItem(R.drawable.list_ohm,getString(R.string.list_calc_ohm), CalcOhm.class));
        models.add(new MainTab1ListItem(R.drawable.list_resistor,getString(R.string.list_calc_voltage_divider), CalcVoltageDivider.class));
        models.add(new MainTab1ListItem(R.drawable.list_resistor,getString(R.string.list_calc_sepa), CalcSerPar.class));
        models.add(new MainTab1ListItem(R.drawable.list_cap_solid,getString(R.string.list_calc_cap_chg), CalcCapacitorCharge.class));
        models.add(new MainTab1ListItem(R.drawable.list_wire,getString(R.string.list_calc_voltdrop), CalcVoltDrop.class));
        models.add(new MainTab1ListItem(R.drawable.list_power,getString(R.string.list_calc_power), CalcPower.class));
        models.add(new MainTab1ListItem(R.drawable.list_freq,getString(R.string.list_conv_freq), ConvFreq.class));
        models.add(new MainTab1ListItem(R.drawable.list_db,getString(R.string.list_conv_db), ConvDb.class));
        models.add(new MainTab1ListItem(R.drawable.list_energy,getString(R.string.list_conv_energy), ConvEnergy.class));
        models.add(new MainTab1ListItem(R.drawable.list_scania_v8,getString(R.string.list_conv_engine), ConvEngine.class));

        //models.add(new MainTab1ListItem(R.drawable.list_trollface,getString(R.string.list_conv_energy), ConvEnergy.class));
        return models;
    }
}
