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
import com.vitaliyhtc.autoelectric.calc.CalcCapacitorCharge;
import com.vitaliyhtc.autoelectric.calc.CalcSerPar;
import com.vitaliyhtc.autoelectric.calc.CalcVoltageDivider;
import com.vitaliyhtc.autoelectric.calc.ConvDb;
import com.vitaliyhtc.autoelectric.calc.ConvEnergy;
import com.vitaliyhtc.autoelectric.calc.ConvFreq;

import java.util.ArrayList;

/**
 * Created by VitaliyHTC on 27.07.2016.
 */
public class MainBaseCalculationsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_base_calculations_list);
        this.setTitle(R.string.list_MainBaseCalculationsList);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<MainTabListItem> mainTabListItems = generateData();
        MainTabListAdapter mainTabListAdapter = new MainTabListAdapter((Context)this, mainTabListItems);
        final ListView myList = (ListView) this.findViewById(R.id.listView);
        myList.setAdapter(mainTabListAdapter);

        final Context context = (Context)this;

        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainTabListItem mainTabListItem = mainTabListItems.get(position);
                        MainListItemType mainListItemType = mainTabListItem.getMainListItemType();
                        if(mainListItemType.equals(MainListItemType.Calculator) ||
                                mainListItemType.equals(MainListItemType.ItemsList)){
                            Class targetActivityClass = mainTabListItem.getTargetActivityClass();
                            Intent intent = new Intent(context, targetActivityClass);
                            startActivity(intent);
                        }else if(mainListItemType.equals(MainListItemType.ResourcesWebView)){
                            Intent intent = new Intent(context, ResourcesWebView.class);
                            intent.putExtra("targetSource", mainTabListItem.getTargetSource());
                            intent.putExtra("targetTitle", mainTabListItem.getTitle());
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


    private ArrayList<MainTabListItem> generateData(){
        ArrayList<MainTabListItem> models = new ArrayList<MainTabListItem>();
        models.add(new MainTabListItem(R.drawable.list_resistor,getString(R.string.list_calc_voltage_divider), MainListItemType.Calculator, CalcVoltageDivider.class, null));
        models.add(new MainTabListItem(R.drawable.list_resistor,getString(R.string.list_calc_sepa), MainListItemType.Calculator, CalcSerPar.class, null));
        models.add(new MainTabListItem(R.drawable.list_cap_solid,getString(R.string.list_calc_cap_chg), MainListItemType.Calculator, CalcCapacitorCharge.class, null));
        models.add(new MainTabListItem(R.drawable.list_freq,getString(R.string.list_conv_freq), MainListItemType.Calculator, ConvFreq.class, null));
        models.add(new MainTabListItem(R.drawable.list_db,getString(R.string.list_conv_db), MainListItemType.Calculator, ConvDb.class, null));
        models.add(new MainTabListItem(R.drawable.list_energy,getString(R.string.list_conv_energy), MainListItemType.Calculator, ConvEnergy.class, null));



        //models.add(new MainTabListItem(R.drawable.list_trollface,getString(R.string.list_conv_energy), MainListItemType.Calculator, ConvEnergy.class, null));
        //models.add(new MainTabListItem(R.drawable.list_iso_trailer,getString(R.string.iso_trailer), MainListItemType.ResourcesWebView, null, "iso_trailer.htm"));
        return models;
    }
}
