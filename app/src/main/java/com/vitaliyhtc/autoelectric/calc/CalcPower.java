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

    private void a(int n2) {
        switch (n2) {
            case 0: {
                this.d.a(false);
                this.e.a(false);
                this.f.a(false);
                this.h.a(false);
                break;
            }
            case 1: 
            case 2: {
                this.d.a(true);
                this.e.a(true);
                this.f.a(true);
                this.h.a(true);
            }
        }
        if (n2 == 2) {
            this.j = Math.sqrt(3.0);
            return;
        }
        this.j = 1.0;
    }

    static void a(CalcPower calcPower) {
        calcPower.b();
    }

    static void a(CalcPower calcPower, int n2) {
        calcPower.a(n2);
    }

    static UnitValue b(CalcPower calcPower) {
        return calcPower.b;
    }

    private void b() {
        double d2 = this.e.h() * 3.141592653589793 / 180.0;
        if (this.i.getSelectedItemPosition() == 0) {
            d2 = 0.0;
        }
        this.f.a(this.b.h() * this.c.h() * this.j);
        this.g.a(this.f.h() * Math.cos(d2));
        UnitValue unitValue2 = this.h;
        double d3 = this.f.h();
        unitValue2.a(Math.sin(d2) * d3);
    }

    private void b(int n2) {
        double d2 = this.e.h() * 3.141592653589793 / 180.0;
        if (this.i.getSelectedItemPosition() == 0) {
            d2 = 0.0;
        }
        if (n2 == R.id.pw_btn_P) {
            this.f.a(this.g.h() / Math.cos(d2));
            return;
        } else {
            if (n2 != R.id.pw_btn_Q) return;
            {
                this.f.a(this.h.h() / Math.sin(d2));
                return;
            }
        }
    }

    static void b(CalcPower calcPower, int n2) {
        calcPower.b(n2);
    }

    private void c() {
        double d2 = this.e.h() * 3.141592653589793 / 180.0;
        this.d.a(Math.cos(d2));
    }

    private void c(int n2) {
        if (n2 == R.id.pw_btn_P) {
            this.d.a(this.g.h() / this.f.h());
            this.d();
            return;
        } else {
            if (n2 != R.id.pw_btn_Q) return;
            {
                this.e.a(Math.asin(this.h.h() / this.f.h()) * 180.0 / 3.141592653589793);
                this.c();
                return;
            }
        }
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

    private void d() {
        this.e.a(Math.acos(this.d.h()) * 180.0 / 3.141592653589793);
    }

    private void d(int n2) {
        ArrayList arrcharSequence = new ArrayList();
        arrcharSequence.add((String)this.b.j());
        arrcharSequence.add((String)this.c.j());
        if ((n2 == R.id.pw_btn_P || n2 == R.id.pw_btn_Q) && this.i.getSelectedItemPosition() != 0) {
            arrcharSequence.add(String.valueOf(this.d.j()) + " / " + this.e.j());
        }
        CharSequence[] arrcharSequence1 = (CharSequence[])arrcharSequence.toArray(new CharSequence[0]);
        new AlertDialog.Builder((Context)this).setTitle(R.string.select_calc).setCancelable(false)
                .setItems(arrcharSequence1, (DialogInterface.OnClickListener)new CalcPowerDIListener(this, arrcharSequence1, n2)).show();
    }

    private void e() {
        this.b.a(this.f.h() / this.c.h() / this.j);
    }

    static void e(CalcPower calcPower) {
        calcPower.f();
    }

    static UnitValue f(CalcPower calcPower) {
        return calcPower.d;
    }

    private void f() {
        this.c.a(this.f.h() / this.b.h() / this.j);
    }

    private void g() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("pwcalc_V", 230.0f));
        this.c.a((double)sharedPreferences.getFloat("pwcalc_I", 4.35f));
        this.e.a((double)sharedPreferences.getFloat("pwcalc_phi", 45.0f));
        this.i.setSelection(sharedPreferences.getInt("pwcalc_spinCurrType", 1));
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("pwcalc_V", (float)this.b.h());
        editor.putFloat("pwcalc_I", (float)this.c.h());
        editor.putFloat("pwcalc_phi", (float)this.e.h());
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
            this.b.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_amp) {
            this.c.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_PF) {
            this.d.a(d2);
            this.d();
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_phase) {
            this.e.a(d2);
            this.c();
            this.b();
            return;
        }
        if (n2 == R.id.pw_btn_S) {
            this.f.a(d2);
            this.d(n2);
            return;
        }
        if (n2 == R.id.pw_btn_P) {
            if (d2 > this.f.h() && this.f.a()) {
                String toastString = String.format(this.getString(R.string.x_mustbe_less_y), this.g.j(), this.f.j());
                Toast.makeText((Context)this.getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
                return;
            }
            this.g.a(d2);
            this.d(n2);
            return;
        }
        if (n2 != R.id.pw_btn_Q) return;
        if (d2 > this.f.h()) {
            String toastString = String.format(this.getString(R.string.x_mustbe_less_y), this.h.j(), this.f.j());
            Toast.makeText((Context)this.getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
            return;
        }
        this.h.a(d2);
        this.d(n2);
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.pw_btn_volt) {
            this.b.a(intent, string);
        } else if (n2 == R.id.pw_btn_amp) {
            this.c.a(intent, string);
        } else if (n2 == R.id.pw_btn_PF) {
            this.d.a(intent, string);
        } else if (n2 == R.id.pw_btn_phase) {
            this.e.a(intent, string);
        } else if (n2 == R.id.pw_btn_S) {
            this.f.a(intent, string);
        } else if (n2 == R.id.pw_btn_P) {
            this.g.a(intent, string);
        } else if (n2 == R.id.pw_btn_Q) {
            this.h.a(intent, string);
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
        this.d.a(1.0f, false);
        this.e.a(90.0f, false);
        this.i = (Spinner)this.findViewById(R.id.pw_current_type);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.voltage_DC), this.getString(R.string.voltage_AC, new Object[]{1}), this.getString(R.string.voltage_AC, new Object[]{3})});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.i.setAdapter((SpinnerAdapter)arrayAdapter);
        this.i.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcPowerCurrentTypeListener(this));
        this.g();
        this.c();
        this.b();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}
