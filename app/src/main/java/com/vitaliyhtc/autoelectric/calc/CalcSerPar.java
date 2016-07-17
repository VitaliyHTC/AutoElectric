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
import com.vitaliyhtc.autoelectric.lib.EIAValuesLines;
import com.vitaliyhtc.autoelectric.lib.EIAValuesTable;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class CalcSerPar extends CalcActivity implements View.OnClickListener {
    private UnitValue b;
    private UnitValue c;
    private UnitValue d;
    private UnitValue e;
    private UnitValue f;
    private UnitValue g;
    private TextView h;
    private TextView i;
    private TextView j;
    private Spinner k;
    private int l = 0;
    private EIAValuesTable m;

    static EIAValuesTable a(CalcSerPar calcSerPar) {
        return calcSerPar.m;
    }

    private String a(double d2) {
        String string;
        String string2;
        if (d2 >= 1000000.0) {
            d2 /= 1000000.0;
            string2 = " M\u2126";
        } else if (d2 >= 1000.0) {
            d2 /= 1000.0;
            string2 = " K\u2126";
        } else {
            string2 = " \u2126";
        }
        String string3 = string = Double.toString((double)Math.round(d2 * 100.0) / 100.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string3 = string.substring(0, string.length() - 2);
        }
        return String.valueOf(string3) + string2;
    }

    static void a(CalcSerPar calcSerPar, int n2) {
        calcSerPar.l = n2;
    }

    private String b(double d2) {
        String string;
        String string2 = string = Double.toString((double)Math.round(d2 * 100.0) / 100.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string2 = string.substring(0, string.length() - 2);
        }
        return String.valueOf(this.getString(R.string.error)) + "=" + string2 + "%";
    }

    private void b() {
        this.b.a(this.c.h() + this.d.h());
        this.m.b(this.b.h());
        this.g.a(this.m.t);
        this.e.a(this.m.y);
        this.f.a(this.m.z);
        this.e();
    }

    static void b(CalcSerPar calcSerPar) {
        calcSerPar.d();
    }

    private void c() {
        this.b.a(this.e.h() * this.f.h() / (this.e.h() + this.f.h()));
        this.m.b(this.b.h());
        this.g.a(this.m.t);
        this.c.a(this.m.v);
        this.d.a(this.m.w);
        this.e();
    }

    private void d() {
        this.m.b(this.b.h());
        this.g.a(this.m.t);
        this.c.a(this.m.v);
        this.d.a(this.m.w);
        this.e.a(this.m.y);
        this.f.a(this.m.z);
        this.e();
    }

    private void e() {
        this.j.setText((CharSequence)(String.valueOf(this.g.l()) + "  (" + this.b(this.m.u) + ")"));
        this.h.setText((CharSequence)(String.valueOf(this.getString(R.string.total)) + ": \u200e" + this.a(this.c.h() + this.d.h()) + "\n (" + this.b(this.m.x) + ")"));
        this.i.setText((CharSequence)(String.valueOf(this.getString(R.string.total)) + ": \u200e" + this.a(this.e.h() * this.f.h() / (this.e.h() + this.f.h())) + "\n (" + this.b(this.m.A) + ")"));
    }

    private void f() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("ser_par_R", 10000.0f));
        this.l = sharedPreferences.getInt("ser_par_SpinSerie", 2);
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("ser_par_R", (float)this.b.h());
        editor.putInt("ser_par_SpinSerie", this.l);
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.sepa_Rdes, n2)) == R.id.sepa_Rdes) {
            this.b.a(d2);
            this.d();
            return;
        }
        if (n2 == R.id.sepa_Rs1) {
            this.c.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.sepa_Rs2) {
            this.d.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.sepa_Rp1) {
            this.e.a(d2);
            this.c();
            return;
        }
        if (n2 == R.id.sepa_Rp2) {
            this.f.a(d2);
            this.c();
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.sepa_Rdes) {
            this.b.a(intent, string);
        } else if (n2 == R.id.sepa_Rs1) {
            this.c.a(intent, string);
        } else if (n2 == R.id.sepa_Rs2) {
            this.d.a(intent, string);
        } else if (n2 == R.id.sepa_Rp1) {
            this.e.a(intent, string);
        } else if (n2 == R.id.sepa_Rp2) {
            this.f.a(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_ser_par);
        this.setTitle(R.string.list_calc_sepa);
        this.b = new UnitValue("R", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.sepa_Rdes), this);
        this.c = new UnitValue("R serie 1", "\u2126", "", false, (Context)this, (TextView)this.findViewById(R.id.sepa_Rs1), this, false);
        this.d = new UnitValue("R serie 2", "\u2126", "", false, (Context)this, (TextView)this.findViewById(R.id.sepa_Rs2), this, false);
        this.e = new UnitValue("R paral 1", "\u2126", "", false, (Context)this, (TextView)this.findViewById(R.id.sepa_Rp1), this, false);
        this.f = new UnitValue("R paral 2", "\u2126", "", false, (Context)this, (TextView)this.findViewById(R.id.sepa_Rp2), this, false);
        this.g = new UnitValue("R", "\u2126", " = ", true, (Context)this, null, null);
        this.j = (TextView)this.findViewById(R.id.sepa_txtNearValue);
        this.h = (TextView)this.findViewById(R.id.sepa_txtTotS);
        this.i = (TextView)this.findViewById(R.id.sepa_txtTotP);
        this.k = (Spinner)this.findViewById(R.id.spinSerie);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, EIAValuesTable.exxpArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.k.setAdapter((SpinnerAdapter)arrayAdapter);
        this.m = new EIAValuesTable(EIAValuesLines.c);
        this.f();
        this.k.setSelection(this.l);
        this.m.a(this.l);
        this.d();
        this.k.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcSerParEIAspinnerListener(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}
