package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class ConvEnergy extends CalcActivity implements View.OnClickListener {
    private UnitValue b; //Wh
    private UnitValue c; //BTU
    private UnitValue d; //J
    private UnitValue e; //cal

    private void calcWh() {
        this.c.validateUnitValueDouble(this.b.getUnitValue() * 3.41214163312794);
        this.d.validateUnitValueDouble(this.b.getUnitValue() * 3600.0);
        this.e.validateUnitValueDouble(this.b.getUnitValue() * 859.84522785899);
    }

    private void calcBTU() {
        this.b.validateUnitValueDouble(this.c.getUnitValue() / 3.41214163312794);
        this.d.validateUnitValueDouble(this.c.getUnitValue() * 1055.05585262);
        this.e.validateUnitValueDouble(this.c.getUnitValue() * 252.19021687207);
    }

    private void calcJ() {
        this.b.validateUnitValueDouble(this.d.getUnitValue() / 3600.0);
        this.c.validateUnitValueDouble(this.d.getUnitValue() / 1055.05585262);
        this.e.validateUnitValueDouble(this.d.getUnitValue() / 4.1868);
    }

    private void calcCal() {
        this.b.validateUnitValueDouble(this.e.getUnitValue() / 859.84522785899);
        this.c.validateUnitValueDouble(this.e.getUnitValue() / 252.19021687207);
        this.d.validateUnitValueDouble(this.e.getUnitValue() * 4.1868);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("energy_Wh", 25.0f));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("energy_Wh", (float)this.b.getUnitValue());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.energy_Wh, n2)) == R.id.energy_Wh) {
            this.b.validateUnitValueDouble(d2);
            this.calcWh();
            return;
        }
        if (n2 == R.id.energy_BTU) {
            this.c.validateUnitValueDouble(d2);
            this.calcBTU();
            return;
        }
        if (n2 == R.id.energy_J) {
            this.d.validateUnitValueDouble(d2);
            this.calcJ();
            return;
        }
        if (n2 == R.id.energy_cal) {
            this.e.validateUnitValueDouble(d2);
            this.calcCal();
            return;
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.energy_Wh) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.energy_BTU) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.energy_J) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.energy_cal) {
            this.e.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_energy);
        this.setTitle(R.string.list_conv_energy);
        this.b = new UnitValue("", "Wh", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_Wh), this);
        this.c = new UnitValue("", "BTU", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_BTU), this);
        this.d = new UnitValue("", "J", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_J), this);
        this.e = new UnitValue("", "cal", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_cal), this);
        this.readSharedPreferences();
        this.calcWh();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
