package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.EIAValuesTable;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.EIAValuesLines;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class CalcVoltageDivider extends CalcActivity implements View.OnClickListener {
    private UnitValue b;
    private UnitValue c;
    private UnitValue d;
    private UnitValue e;
    private UnitValue f;
    private UnitValue g;
    private UnitValue h;
    private UnitValue i;
    private UnitValue j;
    private UnitValue k;
    private CheckBox l;
    private Spinner m;
    private Spinner n;
    private EIAValuesTable o;

    static Spinner a(CalcVoltageDivider calc_vd) {
        return calc_vd.n;
    }
    static EIAValuesTable b(CalcVoltageDivider calc_vd) {
        return calc_vd.o;
    }
    static UnitValue c(CalcVoltageDivider calc_vd) {
        return calc_vd.e;
    }
    static UnitValue d(CalcVoltageDivider calc_vd) {
        return calc_vd.f;
    }

    public void a() {
        if (this.l.isChecked()) {
            double d2 = this.c.getUnitValue() * this.d.getUnitValue() / (this.c.getUnitValue() + this.d.getUnitValue());
            this.f.validateUnitValueDouble(this.e.getUnitValue() * d2 / (this.b.getUnitValue() + d2));
            this.j.setTextViewVisibility(true);
            this.j.validateUnitValueDouble(this.f.getUnitValue() * this.f.getUnitValue() / this.d.getUnitValue());
            this.k.validateUnitValueDouble(this.e.getUnitValue() / (d2 + this.b.getUnitValue()));
            this.i.validateUnitValueDouble(this.e.getUnitValue() * this.k.getUnitValue());
        } else {
            this.f.validateUnitValueDouble(this.e.getUnitValue() * this.c.getUnitValue() / (this.b.getUnitValue() + this.c.getUnitValue()));
            this.j.setTextViewVisibility(false);
            this.k.validateUnitValueDouble(this.e.getUnitValue() / (this.b.getUnitValue() + this.c.getUnitValue()));
            this.i.validateUnitValueDouble(this.e.getUnitValue() * this.k.getUnitValue());
        }
        this.g.validateUnitValueDouble(this.f.getUnitValue() / this.e.getUnitValue());
        this.h.validateUnitValueDouble(this.b.getUnitValue() / this.c.getUnitValue());
    }

    public void a(double d2) {
        if (this.m.getSelectedItemPosition() == 1) {
            d2 = this.b.getUnitValue() / d2;
            if (this.l.isChecked()) {
                this.o.calcError(this.d.getUnitValue() * d2 / (this.d.getUnitValue() - d2));
            } else {
                this.o.calcError(d2);
            }
            this.c.validateUnitValueDouble(this.o.t);
            return;
        }
        if (this.m.getSelectedItemPosition() == 2) {
            double d3 = this.l.isChecked() ? this.c.getUnitValue() * this.d.getUnitValue() / (this.c.getUnitValue() + this.d.getUnitValue()) : this.c.getUnitValue();
            this.o.calcError(d3 * d2);
            this.b.validateUnitValueDouble(this.o.t);
            return;
        }
        this.o.a(d2);
        d2 = this.o.i;
        if (!this.l.isChecked()) {
            this.c.validateUnitValueDouble(this.o.j);
        } else {
            double d4 = this.o.j;
            do {
                if (d4 <= this.d.getUnitValue()) {
                    if (this.d.getUnitValue() - d4 == 0.0) {
                        this.c.validateUnitValueDouble(1.0E9);
                        break;
                    }
                    this.o.calcError(this.d.getUnitValue() * d4 / (this.d.getUnitValue() - d4));
                    this.c.validateUnitValueDouble(this.o.t);
                    break;
                }
                d2 /= 10.0;
                d4 /= 10.0;
            } while (true);
        }
        this.b.validateUnitValueDouble(d2);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("part_R1", 1000.0f));
        this.c.validateUnitValueDouble((double)sharedPreferences.getFloat("part_R2", 1000.0f));
        this.d.validateUnitValueDouble((double)sharedPreferences.getFloat("part_RL", 100000.0f));
        this.e.validateUnitValueDouble((double)sharedPreferences.getFloat("part_Vin", 5.0f));
        this.l.setChecked(sharedPreferences.getBoolean("part_RLon", false));
        this.m.setSelection(sharedPreferences.getInt("part_spinLock", 0));
        this.n.setSelection(sharedPreferences.getInt("part_SpinSerie", 2));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("part_R1", (float)this.b.getUnitValue());
        editor.putFloat("part_R2", (float)this.c.getUnitValue());
        editor.putFloat("part_RL", (float)this.d.getUnitValue());
        editor.putFloat("part_Vin", (float)this.e.getUnitValue());
        editor.putBoolean("part_RLon", this.l.isChecked());
        editor.putInt("part_spinLock", this.m.getSelectedItemPosition());
        editor.putInt("part_SpinSerie", this.n.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent object) {
        super.onActivityResult(n2, n3, (Intent)object);
        if (n3 != -1) {
            return;
        }
        double d2 = object.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.part_R1, n2)) == R.id.part_R1) {
            this.b.validateUnitValueDouble(d2);
        } else if (n2 == R.id.part_R2) {
            this.c.validateUnitValueDouble(d2);
        } else if (n2 == R.id.part_RL) {
            this.d.validateUnitValueDouble(d2);
        } else if (n2 == R.id.part_Vin) {
            this.e.validateUnitValueDouble(d2);
        } else if (n2 == R.id.part_Vout) {
            if (d2 > this.e.getUnitValue()) {
                String string = String.format(this.getString(R.string.x_mustbe_greater_y), this.e.getUnitName(), this.f.getUnitName());
                Toast.makeText((Context)this.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
            } else {
                this.f.validateUnitValueDouble(d2);
                this.a(this.e.getUnitValue() / this.f.getUnitValue() - 1.0);
            }
        } else if (n2 == R.id.part_btnVratio) {
            this.g.validateUnitValueDouble(d2);
            this.a(1.0 / this.g.getUnitValue() - 1.0);
        } else if (n2 == R.id.part_btnRratio) {
            this.h.validateUnitValueDouble(d2);
            this.a(this.h.getUnitValue());
        }
        this.a();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.part_R1) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_R2) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_RL) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_Vin) {
            this.e.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_Vout) {
            this.f.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_btnVratio) {
            this.g.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.part_btnRratio) {
            this.h.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_voltage_divider);
        this.setTitle(R.string.list_calc_voltage_divider);
        this.b = new UnitValue("R1", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_R1), this);
        this.c = new UnitValue("R2", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_R2), this);
        this.d = new UnitValue("RL", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_RL), this);
        this.e = new UnitValue("Vin", "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_Vin), this);
        this.f = new UnitValue("Vout", "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_Vout), this);
        this.g = new UnitValue("Vout/Vin", "", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_btnVratio), this);
        this.h = new UnitValue("R1/R2", "", "\n", false, (Context)this, (TextView)this.findViewById(R.id.part_btnRratio), this);
        this.i = new UnitValue("Ptot", "W", " = ", true, (Context)this, (TextView)this.findViewById(R.id.part_Ptot), null);
        this.j = new UnitValue("P(RL)", "W", " = ", true, (Context)this, (TextView)this.findViewById(R.id.part_Pout), null);
        this.k = new UnitValue(this.getString(R.string.current), "A", " = ", true, (Context)this, (TextView)this.findViewById(R.id.part_Itot), null);
        this.l = (CheckBox)this.findViewById(R.id.chkEnableRL);
        this.n = (Spinner)this.findViewById(R.id.spinSerie);
        this.g.setTopLimit(1.0f);
        ArrayAdapter arrayAdapter0 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, EIAValuesTable.exxArray);
        arrayAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.n.setAdapter((SpinnerAdapter)arrayAdapter0);
        this.o = new EIAValuesTable(EIAValuesLines.c);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.none), "R1", "R2"});
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m = (Spinner)this.findViewById(R.id.part_spinner_fix);
        this.m.setAdapter((SpinnerAdapter)arrayAdapter1);
        this.readSharedPreferences();
        this.o.selectEIAValuesLine(this.n.getSelectedItemPosition());
        this.a();
        this.l.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new CalcVoltageDividerLoadListener(this));
        this.n.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcVoltageDividerEIAspinnerListener(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
