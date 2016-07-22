package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

import java.util.ArrayList;

public class CalcPower extends CalcActivity implements View.OnClickListener {
    private UnitValue b;
    private UnitValue c;
    private UnitValue d;
    private UnitValue e;
    private UnitValue f;
    private UnitValue g;
    private UnitValue h;
    private Spinner i;
    private double j = 1.0;

    static void a(CalcPower calcPower) {
        calcPower.b();
    }
    static void a(CalcPower calcPower, int n2) {
        calcPower.a(n2);
    }
    static UnitValue b(CalcPower calcPower) {
        return calcPower.b;
    }
    static void b(CalcPower calcPower, int n2) {
        calcPower.b(n2);
    }
    static void c(CalcPower calcPower) {
        calcPower.e();
    }
    static void c(CalcPower calcPower, int n2) {
        calcPower.c(n2);
    }
    static UnitValue d(CalcPower calcPower) {
        return calcPower.c;
    }
    static void e(CalcPower calcPower) {
        calcPower.f();
    }
    static UnitValue f(CalcPower calcPower) {
        return calcPower.d;
    }

    private void a(int n2) {
        switch (n2) {
            case 0: {
                this.d.setTextViewVisibility(false);
                this.e.setTextViewVisibility(false);
                this.f.setTextViewVisibility(false);
                this.h.setTextViewVisibility(false);
                break;
            }
            case 1: 
            case 2: {
                this.d.setTextViewVisibility(true);
                this.e.setTextViewVisibility(true);
                this.f.setTextViewVisibility(true);
                this.h.setTextViewVisibility(true);
            }
        }
        if (n2 == 2) {
            this.j = Math.sqrt(3.0);
            return;
        }
        this.j = 1.0;
    }

    private void b() {
        double d2 = this.e.getUnitValue() * 3.141592653589793 / 180.0;
        if (this.i.getSelectedItemPosition() == 0) {
            d2 = 0.0;
        }
        this.f.validateUnitValueDouble(this.b.getUnitValue() * this.c.getUnitValue() * this.j);
        this.g.validateUnitValueDouble(this.f.getUnitValue() * Math.cos(d2));
        UnitValue unitValue2 = this.h;
        double d3 = this.f.getUnitValue();
        unitValue2.validateUnitValueDouble(Math.sin(d2) * d3);
    }

    private void b(int n2) {
        double d2 = this.e.getUnitValue() * 3.141592653589793 / 180.0;
        if (this.i.getSelectedItemPosition() == 0) {
            d2 = 0.0;
        }
        if (n2 == R.id.pw_btn_P) {
            this.f.validateUnitValueDouble(this.g.getUnitValue() / Math.cos(d2));
            return;
        } else {
            if (n2 != R.id.pw_btn_Q) return;
            {
                this.f.validateUnitValueDouble(this.h.getUnitValue() / Math.sin(d2));
                return;
            }
        }
    }

    private void c() {
        double d2 = this.e.getUnitValue() * 3.141592653589793 / 180.0;
        this.d.validateUnitValueDouble(Math.cos(d2));
    }

    private void c(int n2) {
        if (n2 == R.id.pw_btn_P) {
            this.d.validateUnitValueDouble(this.g.getUnitValue() / this.f.getUnitValue());
            this.d();
            return;
        } else {
            if (n2 != R.id.pw_btn_Q) return;
            {
                this.e.validateUnitValueDouble(Math.asin(this.h.getUnitValue() / this.f.getUnitValue()) * 180.0 / 3.141592653589793);
                this.c();
                return;
            }
        }
    }

    private void d() {
        this.e.validateUnitValueDouble(Math.acos(this.d.getUnitValue()) * 180.0 / 3.141592653589793);
    }

    private void d(int n2) {
        ArrayList arrcharSequence = new ArrayList();
        arrcharSequence.add((String)this.b.getUnitName());
        arrcharSequence.add((String)this.c.getUnitName());
        if ((n2 == R.id.pw_btn_P || n2 == R.id.pw_btn_Q) && this.i.getSelectedItemPosition() != 0) {
            arrcharSequence.add(String.valueOf(this.d.getUnitName()) + " / " + this.e.getUnitName());
        }
        CharSequence[] arrcharSequence1 = (CharSequence[])arrcharSequence.toArray(new CharSequence[0]);
        new AlertDialog.Builder((Context)this).setTitle(R.string.select_calc).setCancelable(false)
                .setItems(arrcharSequence1, (DialogInterface.OnClickListener)new CalcPowerDIListener(this, arrcharSequence1, n2)).show();
    }

    private void e() {
        this.b.validateUnitValueDouble(this.f.getUnitValue() / this.c.getUnitValue() / this.j);
    }

    private void f() {
        this.c.validateUnitValueDouble(this.f.getUnitValue() / this.b.getUnitValue() / this.j);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("pwcalc_V", 230.0f));
        this.c.validateUnitValueDouble((double)sharedPreferences.getFloat("pwcalc_I", 4.35f));
        this.e.validateUnitValueDouble((double)sharedPreferences.getFloat("pwcalc_phi", 45.0f));
        this.i.setSelection(sharedPreferences.getInt("pwcalc_spinCurrType", 1));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("pwcalc_V", (float)this.b.getUnitValue());
        editor.putFloat("pwcalc_I", (float)this.c.getUnitValue());
        editor.putFloat("pwcalc_phi", (float)this.e.getUnitValue());
        editor.putInt("pwcalc_spinCurrType", this.i.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent object) {
        super.onActivityResult(n2, n3, (Intent)object);
        if (n3 != -1) {
            return;
        }
        double d2 = object.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.pw_btn_volt, n2)) == R.id.pw_btn_volt) {
            this.b.validateUnitValueDouble(d2);
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_amp) {
            this.c.validateUnitValueDouble(d2);
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_PF) {
            this.d.validateUnitValueDouble(d2);
            this.d();
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_phase) {
            this.e.validateUnitValueDouble(d2);
            this.c();
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_S) {
            this.f.validateUnitValueDouble(d2);
            this.d(n2);
            return;
        }
        if (n2 == R.id.pw_btn_P) {
            if (d2 > this.f.getUnitValue() && this.f.isTextViewVisible()) {
                String toastString = String.format(this.getString(R.string.x_mustbe_less_y), this.g.getUnitName(), this.f.getUnitName());
                Toast.makeText((Context)this.getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
                return;
            }
            this.g.validateUnitValueDouble(d2);
            this.d(n2);
            return;
        }
        if (n2 != R.id.pw_btn_Q) return;
        if (d2 > this.f.getUnitValue()) {
            String toastString = String.format(this.getString(R.string.x_mustbe_less_y), this.h.getUnitName(), this.f.getUnitName());
            Toast.makeText((Context)this.getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
            return;
        }
        this.h.validateUnitValueDouble(d2);
        this.d(n2);
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.pw_btn_volt) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_amp) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_PF) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_phase) {
            this.e.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_S) {
            this.f.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_P) {
            this.g.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.pw_btn_Q) {
            this.h.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_power);
        this.setTitle(R.string.list_calc_power);
        this.b = new UnitValue(this.getString(R.string.voltage), "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.pw_btn_volt), this);
        this.c = new UnitValue(this.getString(R.string.current), "A", "\n", false, (Context)this, (TextView)this.findViewById(R.id.pw_btn_amp), this);
        this.d = new UnitValue(this.getString(R.string.powerfactor), "", "\n", true, (Context)this, (TextView)this.findViewById(R.id.pw_btn_PF), this);
        this.e = new UnitValue(this.getString(R.string.phase), "\u00b0", "\n", true, (Context)this, (TextView)this.findViewById(R.id.pw_btn_phase), this);
        this.f = new UnitValue(this.getString(R.string.powerS), "VA", "\n", true, (Context)this, (TextView)this.findViewById(R.id.pw_btn_S), this);
        this.g = new UnitValue(this.getString(R.string.powerP), "W", "\n", true, (Context)this, (TextView)this.findViewById(R.id.pw_btn_P), this);
        this.h = new UnitValue(this.getString(R.string.powerQ), "VAR", "\n", true, (Context)this, (TextView)this.findViewById(R.id.pw_btn_Q), this);
        this.d.setTopLimit(1.0f, false);
        this.e.setTopLimit(90.0f, false);
        this.i = (Spinner)this.findViewById(R.id.pw_current_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.voltage_DC), this.getString(R.string.voltage_AC, new Object[]{1}), this.getString(R.string.voltage_AC, new Object[]{3})});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.i.setAdapter((SpinnerAdapter)arrayAdapter);
        this.i.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcPowerCurrentTypeListener(this));
        this.readSharedPreferences();
        this.c();
        this.b();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
