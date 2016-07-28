package com.vitaliyhtc.autoelectric;

import android.content.Context;

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
 * Created by VitaliyHTC on 27.07.2016.
 */
public class MainListItemsConfig {


    protected static ArrayList<MainTabListItem> generateListForMainTab1(Context context){
        ArrayList<MainTabListItem> models = new ArrayList<MainTabListItem>();
        models.add(new MainTabListItem(R.drawable.list_ohm,context.getString(R.string.list_calc_ohm), MainListItemType.Calculator, CalcOhm.class, null));
        models.add(new MainTabListItem(R.drawable.list_base_calculations,context.getString(R.string.list_MainBaseCalculationsList), MainListItemType.ItemsList, MainBaseCalculationsList.class, null));
        models.add(new MainTabListItem(R.drawable.list_wire,context.getString(R.string.list_calc_voltdrop), MainListItemType.Calculator, CalcVoltDrop.class, null));
        models.add(new MainTabListItem(R.drawable.list_power,context.getString(R.string.list_calc_power), MainListItemType.Calculator, CalcPower.class, null));
        models.add(new MainTabListItem(R.drawable.list_scania_v8,context.getString(R.string.list_conv_engine), MainListItemType.Calculator, ConvEngine.class, null));

        return models;
    }


    protected static ArrayList<MainTabListItem> generateListForMainBaseCalculationsList(Context context){
        ArrayList<MainTabListItem> models = new ArrayList<MainTabListItem>();
        models.add(new MainTabListItem(R.drawable.list_resistor,context.getString(R.string.list_calc_voltage_divider), MainListItemType.Calculator, CalcVoltageDivider.class, null));
        models.add(new MainTabListItem(R.drawable.list_resistor,context.getString(R.string.list_calc_sepa), MainListItemType.Calculator, CalcSerPar.class, null));
        models.add(new MainTabListItem(R.drawable.list_cap_solid,context.getString(R.string.list_calc_cap_chg), MainListItemType.Calculator, CalcCapacitorCharge.class, null));
        models.add(new MainTabListItem(R.drawable.list_freq,context.getString(R.string.list_conv_freq), MainListItemType.Calculator, ConvFreq.class, null));
        models.add(new MainTabListItem(R.drawable.list_db,context.getString(R.string.list_conv_db), MainListItemType.Calculator, ConvDb.class, null));
        models.add(new MainTabListItem(R.drawable.list_energy,context.getString(R.string.list_conv_energy), MainListItemType.Calculator, ConvEnergy.class, null));

        return models;
    }


    protected static ArrayList<MainTabListItem> generateListForMainTab2(Context context){
        ArrayList<MainTabListItem> models = new ArrayList<MainTabListItem>();
        models.add(new MainTabListItem(R.drawable.list_trollface,context.getString(R.string.index_htm), MainListItemType.ResourcesWebView, null, "index.htm"));
        models.add(new MainTabListItem(R.drawable.list_iso_trailer,context.getString(R.string.list_iso_trailer), MainListItemType.ResourcesWebView, null, "iso_trailer.htm"));
        models.add(new MainTabListItem(R.drawable.list_wire, context.getString(R.string.list_wire_current_amp_nec), MainListItemType.ResourcesWebView, null, "ampacity_NEC.htm"));
        models.add(new MainTabListItem(R.drawable.list_resistance, context.getString(R.string.list_resist_table), MainListItemType.ResourcesWebView, null, "resistivity.htm"));
        models.add(new MainTabListItem(R.drawable.list_si, context.getString(R.string.list_si_units_prefixes), MainListItemType.ResourcesWebView, null, "si_metric_prefix.htm"));
        models.add(new MainTabListItem(R.drawable.list_resistor, context.getString(R.string.list_resist_eia), MainListItemType.ResourcesWebView, null, "eia_values_table.htm"));

        return models;
    }

}
