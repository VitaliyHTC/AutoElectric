package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class CalcVoltDrop extends CalcActivity implements View.OnClickListener {
    double b;
    double c;
    double d;
    double e;
    private UnitValue f;
    private UnitValue g;
    private UnitValue h;
    private UnitValue i;
    private UnitValue j;
    private UnitValue k;
    private UnitValue l;
    private UnitValue m;
    private Spinner n;
    private Spinner o;
    private Spinner p;
    private Spinner q;
    private TextView r;
    private TextView s;
    private TextView t;
    private String[] u = new String[]{"AWG", "kcmil", "mm (r)", "mm\u00b2"};

    private String a(double d2) {
        String string;
        String string2 = string = Double.toString((double)Math.round(d2 * 100.0) / 100.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string2 = string.substring(0, string.length() - 2);
        }
        return "\u200e(" + string2 + "%)";
    }

    private void a(int n2) {
        switch (n2) {
            default: {
                this.b = 0.0;
                return;
            }
            case 0: {
                this.b = 10.66;
                return;
            }
            case 1: {
                this.b = 20.76;
            }
        }
    }

    static void a(CalcVoltDrop calcVoltDrop) {
        calcVoltDrop.c();
    }

    static void a(CalcVoltDrop calcVoltDrop, int n2) {
        calcVoltDrop.a(n2);
    }

    private String b(double d2) {
        String string;
        String string2 = string = Double.toString((double)Math.round(d2 * 1000.0) / 1000.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string2 = string.substring(0, string.length() - 2);
        }
        return string2;
    }

    private void b() {
        switch (this.o.getSelectedItemPosition()) {
            default: {
                return;
            }
            case 0: {
                double d2 = 5.0 * Math.pow(92.0, (36.0 - this.l.h()) / 39.0);
                this.d = d2 * d2;
                return;
            }
            case 1: {
                this.d = Double.parseDouble(this.l.e()) * 1000.0;
                return;
            }
            case 2: {
                this.d = 3.141592653589793 * this.l.h() * this.l.h() * 40000.0 / 20.26829916389991;
                return;
            }
            case 3: {
                this.d = this.l.h() * 40000.0 / 20.26829916389991;
            }
        }
    }

    private void b(int n2) {
        switch (n2) {
            default: {
                this.c = 0.0;
                return;
            }
            case 0: {
                this.c = 1.0;
                return;
            }
            case 1: {
                this.c = 1.0;
                return;
            }
            case 2: {
                this.c = 0.866;
                return;
            }
            case 3: {
                this.c = 0.5;
            }
        }
    }

    static void b(CalcVoltDrop calcVoltDrop) {
        calcVoltDrop.b();
    }

    static void b(CalcVoltDrop calcVoltDrop, int n2) {
        calcVoltDrop.b(n2);
    }

    private void c() {
        double d2 = this.d * 3.141592653589793 / 4.0 * 2.54 * 2.54 / 10000.0;
        double d3 = Math.sqrt(d2 / 3.141592653589793) * 2.0;
        this.s.setText((CharSequence)("\u200e" + Long.toString(Math.round(this.d)) + " cmil (" + this.b(d2) + " mm\u00b2)"));
        this.t.setText((CharSequence)(String.valueOf(this.getString(R.string.diameter)) + ": \u200e" + this.b(d3 / 25.4) + " inch, " + this.b(d3) + " mm"));
        d2 = this.f.h() / 0.3048;
        this.i.a(d2 * (this.e * this.b) * this.h.h() / this.d * this.c);
        if (this.i.h() > this.g.h()) {
            this.i.a(this.g.h());
        }
        this.j.a(this.i.h() / this.g.h() * 100.0);
        this.r.setText((CharSequence)this.a(this.j.h()));
        this.k.a(this.g.h() - this.i.h());
        this.m.a(this.i.h() * this.h.h());
    }

    private void c(int n2) {
        switch (n2) {
            default: {
                this.e = 2.0;
                return;
            }
            case 1: {
                this.e = 1.0;
            }
        }
    }

    static void c(CalcVoltDrop calcVoltDrop, int n2) {
        calcVoltDrop.c(n2);
    }

    private void d() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.g.a((double)sharedPreferences.getFloat("voltdrop_V", 12.0f));
        this.h.a((double)sharedPreferences.getFloat("voltdrop_I", 2.0f));
        this.f.a((double)sharedPreferences.getFloat("voltdrop_Length", 10.0f));
        this.p.setSelection(sharedPreferences.getInt("voltdrop_spinVoltType", 0));
        this.n.setSelection(sharedPreferences.getInt("voltdrop_spinMaterial", 0));
        this.o.setSelection(sharedPreferences.getInt("voltdrop_spinDiam", 0));
        this.l.a((double)sharedPreferences.getFloat("voltdrop_Wsize", 18.0f));
        this.q.setSelection(sharedPreferences.getInt("voltdrop_spinLength", 0));
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("voltdrop_V", (float)this.g.h());
        editor.putFloat("voltdrop_I", (float)this.h.h());
        editor.putFloat("voltdrop_Length", (float)this.f.h());
        editor.putInt("voltdrop_spinMaterial", this.n.getSelectedItemPosition());
        editor.putInt("voltdrop_spinVoltType", this.p.getSelectedItemPosition());
        editor.putInt("voltdrop_spinDiam", this.o.getSelectedItemPosition());
        editor.putFloat("voltdrop_Wsize", (float)this.l.h());
        editor.putInt("voltdrop_spinLength", this.q.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.voltdrop_btn_lenght, n2)) == R.id.voltdrop_btn_lenght) {
            this.f.a(d2);
        } else if (n2 == R.id.voltdrop_btn_volt) {
            this.g.a(d2);
        } else if (n2 == R.id.voltdrop_btn_amp) {
            this.h.a(d2);
        } else if (n2 == R.id.voltdrop_size) {
            this.l.a(d2);
            this.b();
        }
        this.c();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.voltdrop_btn_lenght) {
            this.f.a(intent, string);
        } else if (n2 == R.id.voltdrop_btn_volt) {
            this.g.a(intent, string);
        } else if (n2 == R.id.voltdrop_btn_amp) {
            this.h.a(intent, string);
        } else if (n2 == R.id.voltdrop_size) {
            this.l.a(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_volt_drop);
        this.setTitle(R.string.list_calc_voltdrop);
        this.f = new UnitValue(this.getString(R.string.length), "m", "\n", false, (Context)this, (TextView)this.findViewById(R.id.voltdrop_btn_lenght), this);
        this.g = new UnitValue(this.getString(R.string.voltage), "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.voltdrop_btn_volt), this);
        this.h = new UnitValue(this.getString(R.string.load), "A", "\n", false, (Context)this, (TextView)this.findViewById(R.id.voltdrop_btn_amp), this);
        this.l = new UnitValue("Wire size", "", "", true, (Context)this, (TextView)this.findViewById(R.id.voltdrop_size), this, false);
        this.i = new UnitValue("", "V", "", true, (Context)this, (TextView)this.findViewById(R.id.voltdrop_voltdrop), null);
        this.k = new UnitValue("", "V", "", true, (Context)this, (TextView)this.findViewById(R.id.voltdrop_voltend), null);
        this.j = new UnitValue("", "", "", true, (Context)this, null, null);
        this.m = new UnitValue(this.getString(R.string.pcb_track_loss), "W", " ", true, (Context)this, (TextView)this.findViewById(R.id.voltdrop_ploss), null);
        this.r = (TextView)this.findViewById(R.id.voltdrop_voltdrop_perc);
        this.s = (TextView)this.findViewById(R.id.voltdrop_cmil_mm);
        this.t = (TextView)this.findViewById(R.id.voltdrop_diametr);
        this.n = (Spinner)this.findViewById(R.id.voltdrop_material);
        this.p = (Spinner)this.findViewById(R.id.voltdrop_volt_type);
        this.o = (Spinner)this.findViewById(R.id.voltdrop_awg);
        this.q = (Spinner)this.findViewById(R.id.voltdrop_spin_lenght);
        ArrayAdapter arrayAdapter0 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.copper), this.getString(R.string.aluminum)});
        arrayAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.n.setAdapter((SpinnerAdapter)arrayAdapter0);
        this.n.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcVoltDrop0Listener(this));
        ArrayAdapter arrayAdapter1 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.voltage_DC), this.getString(R.string.voltage_AC, new Object[]{1}), this.getString(R.string.voltage_ACw, new Object[]{3, 3}), this.getString(R.string.voltage_ACw, new Object[]{3, 4})});
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.p.setAdapter((SpinnerAdapter)arrayAdapter1);
        this.p.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcVoltDrop1Listener(this));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, this.u);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.o.setAdapter((SpinnerAdapter)arrayAdapter2);
        this.o.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcVoltDrop2Listener(this));
        ArrayAdapter arrayAdapter3 = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.volt_drop_distance), this.getString(R.string.volt_drop_length)});
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.q.setAdapter((SpinnerAdapter)arrayAdapter3);
        this.q.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcVoltDrop3Listener(this));
        this.d();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}