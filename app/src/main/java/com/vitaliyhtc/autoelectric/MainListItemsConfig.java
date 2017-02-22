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

public class MainListItemsConfig {


    protected static ArrayList<MainListItem> generateListForMainTab1(Context context){
        ArrayList<MainListItem> models = new ArrayList<MainListItem>();
        models.add(new MainListItem(R.drawable.list_calculations,context.getString(R.string.list_MainBaseCalculationsList), MainListItemType.ItemsList, MainTabCalculationsSubList.class, null));
        models.add(new MainListItem(R.drawable.list_converters,context.getString(R.string.list_MainTabConverters), MainListItemType.ItemsList, MainTabConverters.class, null));
        models.add(new MainListItem(R.drawable.list_wire,context.getString(R.string.list_calc_voltdrop), MainListItemType.Calculator, CalcVoltDrop.class, null));
        models.add(new MainListItem(R.drawable.list_scania_v8,context.getString(R.string.list_conv_engine), MainListItemType.Calculator, ConvEngine.class, null));

        return models;
    }


    protected static ArrayList<MainListItem> generateListForMainTabCalculationsSubList(Context context){
        ArrayList<MainListItem> models = new ArrayList<MainListItem>();
        models.add(new MainListItem(R.drawable.list_resistor,context.getString(R.string.list_calc_ohm), MainListItemType.Calculator, CalcOhm.class, null));
        models.add(new MainListItem(R.drawable.list_resistor,context.getString(R.string.list_calc_voltage_divider), MainListItemType.Calculator, CalcVoltageDivider.class, null));
        models.add(new MainListItem(R.drawable.list_resistor,context.getString(R.string.list_calc_sepa), MainListItemType.Calculator, CalcSerPar.class, null));
        models.add(new MainListItem(R.drawable.list_cap_solid,context.getString(R.string.list_calc_cap_chg), MainListItemType.Calculator, CalcCapacitorCharge.class, null));
        models.add(new MainListItem(R.drawable.list_power,context.getString(R.string.list_calc_power), MainListItemType.Calculator, CalcPower.class, null));

        return models;
    }


    protected static ArrayList<MainListItem> generateListForMainTabConverters(Context context){
        ArrayList<MainListItem> models = new ArrayList<MainListItem>();
        models.add(new MainListItem(R.drawable.list_freq,context.getString(R.string.list_conv_freq), MainListItemType.Calculator, ConvFreq.class, null));
        models.add(new MainListItem(R.drawable.list_db,context.getString(R.string.list_conv_db), MainListItemType.Calculator, ConvDb.class, null));
        models.add(new MainListItem(R.drawable.list_energy,context.getString(R.string.list_conv_energy), MainListItemType.Calculator, ConvEnergy.class, null));

        return models;
    }


    protected static ArrayList<MainListItem> generateListForMainTab2(Context context){
        ArrayList<MainListItem> models = new ArrayList<MainListItem>();
        models.add(new MainListItem(R.drawable.list_trollface,context.getString(R.string.index_htm), MainListItemType.ResourcesWebView, null, "index.htm"));
        models.add(new MainListItem(R.drawable.list_iso_trailer,context.getString(R.string.list_iso_trailer), MainListItemType.ResourcesWebView, null, "iso_trailer.htm"));
        models.add(new MainListItem(R.drawable.list_wire, context.getString(R.string.list_wire_current_amp_nec), MainListItemType.ResourcesWebView, null, "ampacity_NEC.htm"));
        models.add(new MainListItem(R.drawable.list_wire, context.getString(R.string.list_awg_table), MainListItemType.ResourcesWebView, null, "awg.htm"));
        models.add(new MainListItem(R.drawable.list_fuse_blade, context.getString(R.string.list_fuses_automotive), MainListItemType.ResourcesWebView, null, "fuse_automotive_def.htm"));
        models.add(new MainListItem(R.drawable.list_resistance, context.getString(R.string.list_resist_table), MainListItemType.ResourcesWebView, null, "resistivity.htm"));
        models.add(new MainListItem(R.drawable.list_si, context.getString(R.string.list_si_units_prefixes), MainListItemType.ResourcesWebView, null, "si_metric_prefix.htm"));
        models.add(new MainListItem(R.drawable.list_resistor, context.getString(R.string.list_resist_eia), MainListItemType.ResourcesWebView, null, "eia_values_table.htm"));
        models.add(new MainListItem(R.drawable.list_cap_ceramic, context.getString(R.string.list_capacitor_mark), MainListItemType.ResourcesWebView, null, "capacitor_mark.htm"));
        models.add(new MainListItem(R.drawable.list_cap_ceramic, context.getString(R.string.list_capacitor_stand), MainListItemType.ResourcesWebView, null, "capacitor_stand.htm"));

        return models;
    }

}
