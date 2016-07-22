package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

import java.util.ArrayList;

public class CalcCapacitorCharge extends CalcActivity implements View.OnClickListener {
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

    static UnitValue a(CalcCapacitorCharge calcCapacitorCharge) {
        return calcCapacitorCharge.b;
    }
    static UnitValue b(CalcCapacitorCharge calcCapacitorCharge) {
        return calcCapacitorCharge.g;
    }
    static UnitValue c(CalcCapacitorCharge calcCapacitorCharge) {
        return calcCapacitorCharge.d;
    }
    static void d(CalcCapacitorCharge calcCapacitorCharge) {
        calcCapacitorCharge.c();
    }

    private void a(int n2) {
        if (n2 == R.id.charge_btnRC) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.b.getUnitName());
            arrayList.add(this.d.getUnitName());
            CharSequence[] charSequences = (CharSequence[])arrayList.toArray(new CharSequence[0]);
            new AlertDialog.Builder((Context)this).setTitle(R.string.select_calc).setCancelable(false)
                    .setItems(charSequences, (DialogInterface.OnClickListener)new CalcCapacitorChargeDIListener(this, charSequences)).show();
            return;
        }
        if (n2 == R.id.charge_btnT) {
            this.k.validateUnitValueDouble(this.h.getUnitValue() / this.g.getUnitValue());
            this.b();
            return;
        }
        this.c();
    }

    private void b() {
        this.h.validateUnitValueDouble(this.k.getUnitValue() * this.g.getUnitValue());
        this.i.validateUnitValueDouble(this.e.getUnitValue() * Math.exp((- this.h.getUnitValue()) / this.g.getUnitValue()));
        this.j.validateUnitValueDouble(this.f.getUnitValue() * (1.0 - Math.exp((- this.h.getUnitValue()) / this.g.getUnitValue())));
    }

    private void c() {
        this.g.validateUnitValueDouble(this.b.getUnitValue() * this.d.getUnitValue());
        this.e.validateUnitValueDouble(this.c.getUnitValue() / this.b.getUnitValue());
        this.f.validateUnitValueDouble(this.d.getUnitValue() * this.c.getUnitValue());
        this.b();
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.validateUnitValueDouble((double)sharedPreferences.getFloat("Ccapcharge_R", 62.0f));
        this.d.validateUnitValueDouble((double)sharedPreferences.getFloat("Ccapcharge_C", 4.7E-5f));
        this.c.validateUnitValueDouble((double)sharedPreferences.getFloat("Ccapcharge_V", 14.4f));
        this.k.validateUnitValueDouble((double)sharedPreferences.getFloat("Ccapcharge_tRC", 2.0f));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("Ccapcharge_R", (float)this.b.getUnitValue());
        editor.putFloat("Ccapcharge_C", (float)this.d.getUnitValue());
        editor.putFloat("Ccapcharge_V", (float)this.c.getUnitValue());
        editor.putFloat("Ccapcharge_tRC", (float)this.k.getUnitValue());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.charge_btnR, n2)) == R.id.charge_btnR) {
            this.b.validateUnitValueDouble(d2);
        } else if (n2 == R.id.charge_btnV) {
            this.c.validateUnitValueDouble(d2);
        } else if (n2 == R.id.charge_btnC) {
            this.d.validateUnitValueDouble(d2);
        } else if (n2 == R.id.charge_btnRC) {
            this.g.validateUnitValueDouble(d2);
        } else if (n2 == R.id.charge_btnT) {
            this.h.validateUnitValueDouble(d2);
        } else if (n2 == R.id.charge_btntRC) {
            this.k.validateUnitValueDouble(d2);
        }
        this.a(n2);
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.charge_btnR) {
            this.b.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.charge_btnV) {
            this.c.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.charge_btnC) {
            this.d.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.charge_btnRC) {
            this.g.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.charge_btnT) {
            this.h.setValueDialogIntent(intent, string);
        } else if (n2 == R.id.charge_btntRC) {
            this.k.setValueDialogIntent(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.calc_capacitor_charge);
        this.setTitle(R.string.list_calc_cap_chg);
        this.b = new UnitValue("R", "\u2126", "\n", false, (Context)this, (TextView)this.findViewById(R.id.charge_btnR), this);
        this.c = new UnitValue("V", "V", "\n", false, (Context)this, (TextView)this.findViewById(R.id.charge_btnV), this);
        this.d = new UnitValue("C", "F", "\n", false, (Context)this, (TextView)this.findViewById(R.id.charge_btnC), this);
        this.g = new UnitValue(this.getString(R.string.charge_RC), "s", "\n", false, (Context)this, (TextView)this.findViewById(R.id.charge_btnRC), this);
        this.h = new UnitValue("t", "s", "", true, (Context)this, (TextView)this.findViewById(R.id.charge_btnT), this, false);
        this.k = new UnitValue("t (RC)", "RC", "", true, (Context)this, (TextView)this.findViewById(R.id.charge_btntRC), this, false);
        this.e = new UnitValue("I max (t=0)", "A", " = ", true, (Context)this, (TextView)this.findViewById(R.id.charge_Imax), null);
        this.f = new UnitValue("Q max (t\u2192\u221e)", "C", " = ", true, (Context)this, (TextView)this.findViewById(R.id.charge_Qmax), null);
        this.i = new UnitValue("I", "A", " = ", true, (Context)this, (TextView)this.findViewById(R.id.charge_Iist), null);
        this.j = new UnitValue("Q", "C", " = ", true, (Context)this, (TextView)this.findViewById(R.id.charge_Qist), null);
        this.readSharedPreferences();
        this.c();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
