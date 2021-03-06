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
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class ConvFreq extends CalcActivity implements View.OnClickListener {
    private UnitValue b;
    private UnitValue c;
    private UnitValue d;
    private UnitValue e;
    private UnitValue f;
    private UnitValue g;
    private UnitValue h;
    private Spinner i;
    private TextView j;

    private void a(int n2) {
        switch (n2) {
            default: {
                return;
            }
            case 1: {
                this.d.validateUnitValueDouble(3.0E8);
                return;
            }
            case 2: {
                this.d.validateUnitValueDouble(2.25E8);
                return;
            }
            case 3: {
                this.d.validateUnitValueDouble(343.0);
                return;
            }
            case 4: {
                this.d.validateUnitValueDouble(1600.0);
            }
        }
    }

    static void a(ConvFreq convFreq) {
        convFreq.d();
    }

    static void a(ConvFreq convFreq, int n2) {
        convFreq.a(n2);
    }

    private void b() {
        this.b.validateUnitValueDouble(1.0 / this.c.getUnitValue());
        this.e.validateUnitValueDouble(this.d.getUnitValue() / this.b.getUnitValue());
        this.e();
    }

    private void c() {
        this.b.validateUnitValueDouble(this.d.getUnitValue() / this.e.getUnitValue());
        this.c.validateUnitValueDouble(1.0 / this.b.getUnitValue());
        this.e();
    }

    private void d() {
        this.c.validateUnitValueDouble(1.0 / this.b.getUnitValue());
        this.e.validateUnitValueDouble(this.d.getUnitValue() / this.b.getUnitValue());
        this.e();
    }

    private void e() {
        this.f.validateUnitValueDouble(this.e.getUnitValue() / 2.0);
        this.g.validateUnitValueDouble(this.e.getUnitValue() / 4.0);
        this.h.validateUnitValueDouble(this.e.getUnitValue() / 8.0);
        this.j.setText((CharSequence)(String.valueOf(this.f.getTextViewString()) + " - " + this.g.getTextViewString() + " - " + this.h.getTextViewString() + '\n'));
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("convFreq_freq", 1000.0f));
        this.d.validateUnitValueDouble((double)sharedPreferences.getFloat("convFreq_Wvel", 3.0E8f));
        this.i.setSelection(sharedPreferences.getInt("convFreq_spinWvel", 1));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("convFreq_freq", (float)this.b.getUnitValue());
        editor.putFloat("convFreq_Wvel", (float)this.d.getUnitValue());
        editor.putInt("convFreq_spinWvel", this.i.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.freq_btnFreq, n2)) == R.id.freq_btnFreq) {
            this.b.validateUnitValueDouble(d2);
            this.d();
            return;
        }
        if (n2 == R.id.freq_btnPer) {
            this.c.validateUnitValueDouble(d2);
            this.b();
            return;
        }
        if (n2 == R.id.freq_btnWvel) {
            this.d.validateUnitValueDouble(d2);
            this.i.setSelection(0);
            this.d();
            return;
        }
        if (n2 != R.id.freq_btnWlen) return;
        this.e.validateUnitValueDouble(d2);
        this.c();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.freq_btnFreq) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.freq_btnPer) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.freq_btnWvel) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.freq_btnWlen) {
            this.e.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_freq);
        this.setTitle(R.string.list_conv_freq);
        this.b = new UnitValue(this.getString(R.string.frequ), "Hz", "\n", false, (Context)this, (TextView)this.findViewById(R.id.freq_btnFreq), this);
        this.c = new UnitValue(this.getString(R.string.period), "s", "\n", false, (Context)this, (TextView)this.findViewById(R.id.freq_btnPer), this);
        this.d = new UnitValue(this.getString(R.string.freq_wave_vel), "m/s", "\n", false, (Context)this, (TextView)this.findViewById(R.id.freq_btnWvel), this);
        this.e = new UnitValue(this.getString(R.string.wavelength), "m", "\n", true, (Context)this, (TextView)this.findViewById(R.id.freq_btnWlen), this);
        this.f = new UnitValue("\u03bb/2", "m", " = ", true, (Context)this, null, null);
        this.g = new UnitValue("\u03bb/4", "m", " = ", true, (Context)this, null, null);
        this.h = new UnitValue("\u03bb/8", "m", " = ", true, (Context)this, null, null);
        this.j = (TextView)this.findViewById(R.id.freq_lambda_fractions );
        this.i = (Spinner)this.findViewById(R.id.freq_spin_Wvel);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.custom), this.getString(R.string.freq_Wvel_1), this.getString(R.string.freq_Wvel_2), this.getString(R.string.freq_Wvel_3), this.getString(R.string.freq_Wvel_4)});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.i.setAdapter((SpinnerAdapter)arrayAdapter);
        this.i.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new ConvFreq0Listener(this));
        this.readSharedPreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
