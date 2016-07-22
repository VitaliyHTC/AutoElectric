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

public class ConvEngine extends CalcActivity implements View.OnClickListener {
    private UnitValue b; //lb-ft
    private UnitValue c; //Nm
    private UnitValue d; //hp
    private UnitValue e; //W

    private void calcLbFt() {
        this.c.validateUnitValueDouble(this.b.getUnitValue() * 1.35581794833);
    }

    private void calcNm() {
        this.b.validateUnitValueDouble(this.c.getUnitValue() / 1.35581794833);
    }

    private void calcHp() {
        this.e.validateUnitValueDouble((this.d.getUnitValue() * 1000 ) / 1.3410220888);
    }

    private void calcKw() {
        this.d.validateUnitValueDouble((this.e.getUnitValue() * 1.3410220888) / 1000);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("engine_torque_lbft", 650.0f));
        this.d.validateUnitValueDouble((double)sharedPreferences.getFloat("engine_power_hp", 650.0f));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("engine_torque_lbft", (float)this.b.getUnitValue());
        editor.putFloat("engine_power_hp", (float)this.d.getUnitValue());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.torque_lbft, n2)) == R.id.torque_lbft) {
            this.b.validateUnitValueDouble(d2);
            this.calcLbFt();
            return;
        }
        if (n2 == R.id.torque_nm) {
            this.c.validateUnitValueDouble(d2);
            this.calcNm();
            return;
        }
        if (n2 == R.id.power_hp) {
            this.d.validateUnitValueDouble(d2);
            this.calcHp();
            return;
        }
        if (n2 == R.id.power_kw) {
            this.e.validateUnitValueDouble(d2);
            this.calcKw();
            return;
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.torque_lbft) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.torque_nm) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.power_hp) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.power_kw) {
            this.e.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_engine_torque_power);
        this.setTitle(R.string.list_conv_engine);
        this.b = new UnitValue("", "lb-ft", "", false, (Context)this, (TextView)this.findViewById(R.id.torque_lbft), this);
        this.c = new UnitValue("", "Nm", "", false, (Context)this, (TextView)this.findViewById(R.id.torque_nm), this);
        this.d = new UnitValue("", "hp", "", false, (Context)this, (TextView)this.findViewById(R.id.power_hp), this);
        this.e = new UnitValue("", "W", "", false, (Context)this, (TextView)this.findViewById(R.id.power_kw), this);
        this.readSharedPreferences();
        this.calcLbFt();
        this.calcHp();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
