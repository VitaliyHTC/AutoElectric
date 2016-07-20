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

    private void a(int n2) {
        if (n2 == R.id.charge_btnRC) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.b.j());
            arrayList.add(this.d.j());
            CharSequence[] charSequences = (CharSequence[])arrayList.toArray(new CharSequence[0]);
            new AlertDialog.Builder((Context)this).setTitle(R.string.select_calc).setCancelable(false)
                    .setItems(charSequences, (DialogInterface.OnClickListener)new CalcCapacitorChargeDIListener(this, charSequences)).show();
            return;
        }
        if (n2 == R.id.charge_btnT) {
            this.k.a(this.h.h() / this.g.h());
            this.b();
            return;
        }
        this.c();
    }

    static UnitValue b(CalcCapacitorCharge calcCapacitorCharge) {
        return calcCapacitorCharge.g;
    }

    private void b() {
        this.h.a(this.k.h() * this.g.h());
        this.i.a(this.e.h() * Math.exp((- this.h.h()) / this.g.h()));
        this.j.a(this.f.h() * (1.0 - Math.exp((- this.h.h()) / this.g.h())));
    }

    static UnitValue c(CalcCapacitorCharge calcCapacitorCharge) {
        return calcCapacitorCharge.d;
    }

    private void c() {
        this.g.a(this.b.h() * this.d.h());
        this.e.a(this.c.h() / this.b.h());
        this.f.a(this.d.h() * this.c.h());
        this.b();
    }

    private void d() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("Ccapcharge_R", 62.0f));
        this.d.a((double)sharedPreferences.getFloat("Ccapcharge_C", 4.7E-5f));
        this.c.a((double)sharedPreferences.getFloat("Ccapcharge_V", 14.4f));
        this.k.a((double)sharedPreferences.getFloat("Ccapcharge_tRC", 2.0f));
    }

    static void d(CalcCapacitorCharge calcCapacitorCharge) {
        calcCapacitorCharge.c();
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("Ccapcharge_R", (float)this.b.h());
        editor.putFloat("Ccapcharge_C", (float)this.d.h());
        editor.putFloat("Ccapcharge_V", (float)this.c.h());
        editor.putFloat("Ccapcharge_tRC", (float)this.k.h());
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
            this.b.a(d2);
        } else if (n2 == R.id.charge_btnV) {
            this.c.a(d2);
        } else if (n2 == R.id.charge_btnC) {
            this.d.a(d2);
        } else if (n2 == R.id.charge_btnRC) {
            this.g.a(d2);
        } else if (n2 == R.id.charge_btnT) {
            this.h.a(d2);
        } else if (n2 == R.id.charge_btntRC) {
            this.k.a(d2);
        }
        this.a(n2);
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.charge_btnR) {
            this.b.a(intent, string);
        } else if (n2 == R.id.charge_btnV) {
            this.c.a(intent, string);
        } else if (n2 == R.id.charge_btnC) {
            this.d.a(intent, string);
        } else if (n2 == R.id.charge_btnRC) {
            this.g.a(intent, string);
        } else if (n2 == R.id.charge_btnT) {
            this.h.a(intent, string);
        } else if (n2 == R.id.charge_btntRC) {
            this.k.a(intent, string);
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
        this.d();
        this.c();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}
