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

    static void a(CalcOhm calcOhm, int n2) {
        calcOhm.a(n2);
    }

    static void a(CalcOhm calcOhm, String string) {
        calcOhm.a(string);
    }

    private void a(String string) {
        if (string.equals("R = P / I\u00b2")) {
            this.e.a(this.h.h() / (this.g.h() * this.g.h()));
            this.c();
            this.g();
            return;
        } else {
            if (string.equals("R = V\u00b2 / P")) {
                this.e.a(this.f.h() * this.f.h() / this.h.h());
                this.b();
                this.g();
                return;
            }
            if (string.equals("I = P / V")) {
                this.g.a(this.h.h() / this.f.h());
                this.d();
                return;
            }
            if (string.equals("I = \u221a(P / R)")) {
                this.g.a(Math.sqrt(this.h.h() / this.e.h()));
                this.c();
                return;
            }
            if (string.equals("V = P / I")) {
                this.f.a(this.h.h() / this.g.h());
                this.d();
                return;
            }
            if (!string.equals("V = \u221a(P x R)")) return;
            {
                this.f.a(Math.sqrt(this.h.h() * this.e.h()));
                this.b();
                return;
            }
        }
    }

    static UnitValue b(CalcOhm calcOhm) {
        return calcOhm.i;
    }

    private void b() {
        this.g.a(this.f.h() / this.e.h());
    }

    static void b(CalcOhm calcOhm, int n2) {
        calcOhm.m = n2;
    }

    static EIAValuesTable c(CalcOhm calcOhm) {
        return calcOhm.n;
    }

    private void c() {
        this.f.a(this.e.h() * this.g.h());
    }

    private void d() {
        this.e.a(this.f.h() / this.g.h());
        this.g();
    }

    static void d(CalcOhm calcOhm) {
        calcOhm.g();
    }

    private void e() {
        this.h.a(this.f.h() * this.g.h());
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
        this.n.b(this.e.h());
        this.i.a(this.n.t);
        this.j.setText((CharSequence)(String.valueOf(this.i.l()) + "  (" + this.a(this.n.u) + ")"));
    }

    private void h() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.f.a((double)sharedPreferences.getFloat("ohm_V", 13.2f));
        this.g.a((double)sharedPreferences.getFloat("ohm_I", 5.5f));
        this.m = sharedPreferences.getInt("ohm_SpinSerie", 2);
        this.o = sharedPreferences.getInt("ohm_Last", c);
        this.p = sharedPreferences.getInt("ohm_Last_but_one", d);
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("ohm_V", (float)this.f.h());
        editor.putFloat("ohm_I", (float)this.g.h());
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
            this.e.a(d2);
            this.a(b);
            this.g();
            return;
        }
        if (n2 == R.id.ohm_V) {
            this.f.a(d2);
            this.a(c);
            return;
        }
        if (n2 == R.id.ohm_I) {
            this.g.a(d2);
            this.a(d);
            return;
        }
        if (n2 != R.id.ohm_P) return;
        this.h.a(d2);
        this.f();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.ohm_R) {
            this.e.a(intent, string);
        } else if (n2 == R.id.ohm_V) {
            this.f.a(intent, string);
        } else if (n2 == R.id.ohm_I) {
            this.g.a(intent, string);
        } else if (n2 == R.id.ohm_P) {
            this.h.a(intent, string);
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
        this.h();
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
        this.a();
    }
}
