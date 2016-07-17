package com.vitaliyhtc.autoelectric.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;
import com.vitaliyhtc.autoelectric.activity.CalcActivity;
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class ConvEngine extends CalcActivity implements View.OnClickListener {
    private UnitValue b; //lb-ft
    private UnitValue c; //Nm
    private UnitValue d; //hp
    private UnitValue e; //W

    private void b() {
        this.c.a(this.b.h() * 1.35581794833);
    }

    private void c() {
        this.b.a(this.c.h() / 1.35581794833);
    }

    private void d() {
        this.e.a((this.d.h() * 1000 ) / 1.3410220888);
    }

    private void e() {
        this.d.a((this.e.h() * 1.3410220888) / 1000);
    }

    private void f() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("engine_torque_lbft", 650.0f));
        this.d.a((double)sharedPreferences.getFloat("engine_power_hp", 650.0f));
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("engine_torque_lbft", (float)this.b.h());
        editor.putFloat("engine_power_hp", (float)this.d.h());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.torque_lbft, n2)) == R.id.torque_lbft) {
            this.b.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.torque_nm) {
            this.c.a(d2);
            this.c();
            return;
        }
        if (n2 == R.id.power_hp) {
            this.d.a(d2);
            this.d();
            return;
        }
        if (n2 == R.id.power_kw) {
            this.e.a(d2);
            this.e();
            return;
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.torque_lbft) {
            this.b.a(intent, string);
        } else if (n2 == R.id.torque_nm) {
            this.c.a(intent, string);
        } else if (n2 == R.id.power_hp) {
            this.d.a(intent, string);
        } else if (n2 == R.id.power_kw) {
            this.e.a(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_engine_torque_power);
        this.setTitle(R.string.list_conv_engine);
        this.b = new UnitValue("", "lb-ft", "", false, (Context)this, (TextView)this.findViewById(R.id.torque_lbft), this);
        this.c = new UnitValue("", "Nm", "", false, (Context)this, (TextView)this.findViewById(R.id.torque_nm), this);
        this.d = new UnitValue("", "hp", "", false, (Context)this, (TextView)this.findViewById(R.id.power_hp), this);
        this.e = new UnitValue("", "W", "", false, (Context)this, (TextView)this.findViewById(R.id.power_kw), this);
        this.f();
        this.b();
        this.d();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}