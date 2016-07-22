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
    static void a(CalcSerPar calcSerPar, int n2) {
        calcSerPar.l = n2;
    }
    static void b(CalcSerPar calcSerPar) {
        calcSerPar.d();
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

    private String b(double d2) {
        String string;
        String string2 = string = Double.toString((double)Math.round(d2 * 100.0) / 100.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string2 = string.substring(0, string.length() - 2);
        }
        return String.valueOf(this.getString(R.string.error)) + "=" + string2 + "%";
    }

    private void b() {
        this.b.validateUnitValueDouble(this.c.getUnitValue() + this.d.getUnitValue());
        this.m.calcError(this.b.getUnitValue());
        this.g.validateUnitValueDouble(this.m.t);
        this.e.validateUnitValueDouble(this.m.y);
        this.f.validateUnitValueDouble(this.m.z);
        this.e();
    }

    private void c() {
        this.b.validateUnitValueDouble(this.e.getUnitValue() * this.f.getUnitValue() / (this.e.getUnitValue() + this.f.getUnitValue()));
        this.m.calcError(this.b.getUnitValue());
        this.g.validateUnitValueDouble(this.m.t);
        this.c.validateUnitValueDouble(this.m.v);
        this.d.validateUnitValueDouble(this.m.w);
        this.e();
    }

    private void d() {
        this.m.calcError(this.b.getUnitValue());
        this.g.validateUnitValueDouble(this.m.t);
        this.c.validateUnitValueDouble(this.m.v);
        this.d.validateUnitValueDouble(this.m.w);
        this.e.validateUnitValueDouble(this.m.y);
        this.f.validateUnitValueDouble(this.m.z);
        this.e();
    }

    private void e() {
        this.j.setText((CharSequence)(String.valueOf(this.g.getTextViewString()) + "  (" + this.b(this.m.error1Is) + ")"));
        this.h.setText((CharSequence)(String.valueOf(this.getString(R.string.total)) + ": \u200e" + this.a(this.c.getUnitValue() + this.d.getUnitValue()) + "\n (" + this.b(this.m.error2Is) + ")"));
        this.i.setText((CharSequence)(String.valueOf(this.getString(R.string.total)) + ": \u200e" + this.a(this.e.getUnitValue() * this.f.getUnitValue() / (this.e.getUnitValue() + this.f.getUnitValue())) + "\n (" + this.b(this.m.error3Is) + ")"));
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("ser_par_R", 10000.0f));
        this.l = sharedPreferences.getInt("ser_par_SpinSerie", 2);
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("ser_par_R", (float)this.b.getUnitValue());
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
            this.b.validateUnitValueDouble(d2);
            this.d();
            return;
        }
        if (n2 == R.id.sepa_Rs1) {
            this.c.validateUnitValueDouble(d2);
            this.b();
            return;
        }
        if (n2 == R.id.sepa_Rs2) {
            this.d.validateUnitValueDouble(d2);
            this.b();
            return;
        }
        if (n2 == R.id.sepa_Rp1) {
            this.e.validateUnitValueDouble(d2);
            this.c();
            return;
        }
        if (n2 == R.id.sepa_Rp2) {
            this.f.validateUnitValueDouble(d2);
            this.c();
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.sepa_Rdes) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.sepa_Rs1) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.sepa_Rs2) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.sepa_Rp1) {
            this.e.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.sepa_Rp2) {
            this.f.setValueDialogIntent(intent, string);
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
        this.readSharedPreferences();
        this.k.setSelection(this.l);
        this.m.selectEIAValuesLine(this.l);
        this.d();
        this.k.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new CalcSerParEIAspinnerListener(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
