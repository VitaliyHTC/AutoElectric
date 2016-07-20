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
            double d2 = this.c.h() * this.d.h() / (this.c.h() + this.d.h());
            this.f.a(this.e.h() * d2 / (this.b.h() + d2));
            this.j.a(true);
            this.j.a(this.f.h() * this.f.h() / this.d.h());
            this.k.a(this.e.h() / (d2 + this.b.h()));
            this.i.a(this.e.h() * this.k.h());
        } else {
            this.f.a(this.e.h() * this.c.h() / (this.b.h() + this.c.h()));
            this.j.a(false);
            this.k.a(this.e.h() / (this.b.h() + this.c.h()));
            this.i.a(this.e.h() * this.k.h());
        }
        this.g.a(this.f.h() / this.e.h());
        this.h.a(this.b.h() / this.c.h());
    }

    public void a(double d2) {
        if (this.m.getSelectedItemPosition() == 1) {
            d2 = this.b.h() / d2;
            if (this.l.isChecked()) {
                this.o.b(this.d.h() * d2 / (this.d.h() - d2));
            } else {
                this.o.b(d2);
            }
            this.c.a(this.o.t);
            return;
        }
        if (this.m.getSelectedItemPosition() == 2) {
            double d3 = this.l.isChecked() ? this.c.h() * this.d.h() / (this.c.h() + this.d.h()) : this.c.h();
            this.o.b(d3 * d2);
            this.b.a(this.o.t);
            return;
        }
        this.o.a(d2);
        d2 = this.o.i;
        if (!this.l.isChecked()) {
            this.c.a(this.o.j);
        } else {
            double d4 = this.o.j;
            do {
                if (d4 <= this.d.h()) {
                    if (this.d.h() - d4 == 0.0) {
                        this.c.a(1.0E9);
                        break;
                    }
                    this.o.b(this.d.h() * d4 / (this.d.h() - d4));
                    this.c.a(this.o.t);
                    break;
                }
                d2 /= 10.0;
                d4 /= 10.0;
            } while (true);
        }
        this.b.a(d2);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("part_R1", 1000.0f));
        this.c.a((double)sharedPreferences.getFloat("part_R2", 1000.0f));
        this.d.a((double)sharedPreferences.getFloat("part_RL", 100000.0f));
        this.e.a((double)sharedPreferences.getFloat("part_Vin", 5.0f));
        this.l.setChecked(sharedPreferences.getBoolean("part_RLon", false));
        this.m.setSelection(sharedPreferences.getInt("part_spinLock", 0));
        this.n.setSelection(sharedPreferences.getInt("part_SpinSerie", 2));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("part_R1", (float)this.b.h());
        editor.putFloat("part_R2", (float)this.c.h());
        editor.putFloat("part_RL", (float)this.d.h());
        editor.putFloat("part_Vin", (float)this.e.h());
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
            this.b.a(d2);
        } else if (n2 == R.id.part_R2) {
            this.c.a(d2);
        } else if (n2 == R.id.part_RL) {
            this.d.a(d2);
        } else if (n2 == R.id.part_Vin) {
            this.e.a(d2);
        } else if (n2 == R.id.part_Vout) {
            if (d2 > this.e.h()) {
                String string = String.format(this.getString(R.string.x_mustbe_greater_y), this.e.j(), this.f.j());
                Toast.makeText((Context)this.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
            } else {
                this.f.a(d2);
                this.a(this.e.h() / this.f.h() - 1.0);
            }
        } else if (n2 == R.id.part_btnVratio) {
            this.g.a(d2);
            this.a(1.0 / this.g.h() - 1.0);
        } else if (n2 == R.id.part_btnRratio) {
            this.h.a(d2);
            this.a(this.h.h());
        }
        this.a();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.part_R1) {
            this.b.a(intent, string);
        } else if (n2 == R.id.part_R2) {
            this.c.a(intent, string);
        } else if (n2 == R.id.part_RL) {
            this.d.a(intent, string);
        } else if (n2 == R.id.part_Vin) {
            this.e.a(intent, string);
        } else if (n2 == R.id.part_Vout) {
            this.f.a(intent, string);
        } else if (n2 == R.id.part_btnVratio) {
            this.g.a(intent, string);
        } else if (n2 == R.id.part_btnRratio) {
            this.h.a(intent, string);
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
        this.g.a(1.0f);
        ArrayAdapter arrayAdapter0 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, EIAValuesTable.exxArray);
        arrayAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.n.setAdapter((SpinnerAdapter)arrayAdapter0);
        this.o = new EIAValuesTable(EIAValuesLines.c);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.none), "R1", "R2"});
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.m = (Spinner)this.findViewById(R.id.part_spinner_fix);
        this.m.setAdapter((SpinnerAdapter)arrayAdapter1);
        this.readSharedPreferences();
        this.o.a(this.n.getSelectedItemPosition());
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
