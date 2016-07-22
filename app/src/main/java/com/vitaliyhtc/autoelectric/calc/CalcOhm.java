package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.EIAValuesTable;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;
import com.vitaliyhtc.autoelectric.lib.EIAValuesLines;

import java.util.ArrayList;

public class CalcOhm extends CalcActivity implements View.OnClickListener {
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    private UnitValue e;
    private UnitValue f;
    private UnitValue g;
    private UnitValue h;
    private UnitValue i;
    private TextView j;
    private Button k;
    private Spinner l;
    private int m = 0;
    private EIAValuesTable n;
    private int o = 0;
    private int p = 0;

    static UnitValue a(CalcOhm calcOhm) {
        return calcOhm.e;
    }
    static void a(CalcOhm calcOhm, int n2) {
        calcOhm.a(n2);
    }
    static void a(CalcOhm calcOhm, String string) {
        calcOhm.a(string);
    }
    static UnitValue b(CalcOhm calcOhm) {
        return calcOhm.i;
    }
    static void b(CalcOhm calcOhm, int n2) {
        calcOhm.m = n2;
    }
    static EIAValuesTable c(CalcOhm calcOhm) {
        return calcOhm.n;
    }
    static void d(CalcOhm calcOhm) {
        calcOhm.g();
    }

    private String a(double d2) {
        String string;
        String string2 = string = Double.toString((double)Math.round(d2 * 100.0) / 100.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string2 = string.substring(0, string.length() - 2);
        }
        return String.valueOf(this.getResources().getString(R.string.error)) + "=\u200e" + string2 + "%";
    }

    private void a(int var1) {
        if(this.o != var1) {
            this.p = this.o;
            this.o = var1;
        }
        switch(this.p + this.o) {
            case 3:
                this.b();
                break;
            case 4:
                this.c();
                break;
            case 5:
                this.d();
        }
        this.e();
    }

    private void a(String string) {
        if (string.equals("R = P / I\u00b2")) {
            this.e.validateUnitValueDouble(this.h.getUnitValue() / (this.g.getUnitValue() * this.g.getUnitValue()));
            this.c();
            this.g();
            return;
        } else {
            if (string.equals("R = V\u00b2 / P")) {
                this.e.validateUnitValueDouble(this.f.getUnitValue() * this.f.getUnitValue() / this.h.getUnitValue());
                this.b();
                this.g();
                return;
            }
            if (string.equals("I = P / V")) {
                this.g.validateUnitValueDouble(this.h.getUnitValue() / this.f.getUnitValue());
                this.d();
                return;
            }
            if (string.equals("I = \u221a(P / R)")) {
                this.g.validateUnitValueDouble(Math.sqrt(this.h.getUnitValue() / this.e.getUnitValue()));
                this.c();
                return;
            }
            if (string.equals("V = P / I")) {
                this.f.validateUnitValueDouble(this.h.getUnitValue() / this.g.getUnitValue());
                this.d();
                return;
            }
            if (!string.equals("V = \u221a(P x R)")) return;
            {
                this.f.validateUnitValueDouble(Math.sqrt(this.h.getUnitValue() * this.e.getUnitValue()));
                this.b();
                return;
            }
        }
    }

    private void b() {
        this.g.validateUnitValueDouble(this.f.getUnitValue() / this.e.getUnitValue());
    }

    private void c() {
        this.f.validateUnitValueDouble(this.e.getUnitValue() * this.g.getUnitValue());
    }

    private void d() {
        this.e.validateUnitValueDouble(this.f.getUnitValue() / this.g.getUnitValue());
        this.g();
    }

    private void e() {
        this.h.validateUnitValueDouble(this.f.getUnitValue() * this.g.getUnitValue());
    }

    private void f() {
        Object localObject = new ArrayList();
        ((ArrayList)localObject).add("R = P / I²");
        ((ArrayList)localObject).add("R = V² / P");
        ((ArrayList)localObject).add("I = P / V");
        ((ArrayList)localObject).add("I = √(P / R)");
        ((ArrayList)localObject).add("V = P / I");
        ((ArrayList)localObject).add("V = √(P x R)");
        localObject = (CharSequence[])((ArrayList)localObject).toArray(new CharSequence[0]);
        new AlertDialog.Builder(this).setTitle(R.string.select_calc).setCancelable(false)
                .setItems((CharSequence[])localObject, new CalcOhmDIListener(this, (CharSequence[])localObject)).show();
    }

    private void g() {
        this.n.calcError(this.e.getUnitValue());
        this.i.validateUnitValueDouble(this.n.t);
        this.j.setText((CharSequence)(String.valueOf(this.i.getTextViewString()) + "  (" + this.a(this.n.error1Is) + ")"));
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.f.validateUnitValueDouble((double)sharedPreferences.getFloat("ohm_V", 13.2f));
        this.g.validateUnitValueDouble((double)sharedPreferences.getFloat("ohm_I", 5.5f));
        this.m = sharedPreferences.getInt("ohm_SpinSerie", 2);
        this.o = sharedPreferences.getInt("ohm_Last", c);
        this.p = sharedPreferences.getInt("ohm_Last_but_one", d);
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("ohm_V", (float)this.f.getUnitValue());
        editor.putFloat("ohm_I", (float)this.g.getUnitValue());
        editor.putInt("ohm_SpinSerie", this.m);
        editor.putInt("ohm_Last", this.o);
        editor.putInt("ohm_Last_but_one", this.p);
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.ohm_R, n2)) == R.id.ohm_R) {
            this.e.validateUnitValueDouble(d2);
            this.a(b);
            this.g();
            return;
        }
        if (n2 == R.id.ohm_V) {
            this.f.validateUnitValueDouble(d2);
            this.a(c);
            return;
        }
        if (n2 == R.id.ohm_I) {
            this.g.validateUnitValueDouble(d2);
            this.a(d);
            return;
        }
        if (n2 != R.id.ohm_P) return;
        this.h.validateUnitValueDouble(d2);
        this.f();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.ohm_R) {
            this.e.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.ohm_V) {
            this.f.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.ohm_I) {
            this.g.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.ohm_P) {
            this.h.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_ohm);
        this.setTitle(R.string.list_calc_ohm);
        this.e = new UnitValue("R", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.ohm_R), this);
        this.f = new UnitValue("V", "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.ohm_V), this);
        this.g = new UnitValue("I", "A", "\n", false, (Context)this, (TextView)this.findViewById(R.id.ohm_I), this);
        this.h = new UnitValue(this.getString(R.string.power), "W", "\n", true, (Context)this, (TextView)this.findViewById(R.id.ohm_P), this);
        this.i = new UnitValue("R", "\u2126", " = ", true, (Context)this, (TextView)this.findViewById(R.id.ohm_txtNearValue), null);
        this.j = (TextView)this.findViewById(R.id.ohm_txtNearValue);
        this.l = (Spinner)this.findViewById(R.id.spinSerie);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, EIAValuesTable.exxArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.l.setAdapter((SpinnerAdapter)arrayAdapter);
        this.n = new EIAValuesTable(EIAValuesLines.c);
        this.readSharedPreferences();
        this.l.setSelection(this.m);
        this.d();
        this.e();
        this.g();
        this.k = (Button)this.findViewById(R.id.ohm_btnSetThis);
        this.k.setOnClickListener((View.OnClickListener)new CalcOhmSetThisListener(this));
        this.l.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcOhmEIAspinnerListener(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
