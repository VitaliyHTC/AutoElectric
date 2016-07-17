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

public class ConvEnergy extends CalcActivity implements View.OnClickListener {
    private UnitValue b; //Wh
    private UnitValue c; //BTU
    private UnitValue d; //J
    private UnitValue e; //cal

    private void b() {
        this.c.a(this.b.h() * 3.41214163312794);
        this.d.a(this.b.h() * 3600.0);
        this.e.a(this.b.h() * 859.84522785899);
    }

    private void c() {
        this.b.a(this.c.h() / 3.41214163312794);
        this.d.a(this.c.h() * 1055.05585262);
        this.e.a(this.c.h() * 252.19021687207);
    }

    private void d() {
        this.b.a(this.d.h() / 3600.0);
        this.c.a(this.d.h() / 1055.05585262);
        this.e.a(this.d.h() / 4.1868);
    }

    private void e() {
        this.b.a(this.e.h() / 859.84522785899);
        this.c.a(this.e.h() / 252.19021687207);
        this.d.a(this.e.h() * 4.1868);
    }

    private void f() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.b.a((double)sharedPreferences.getFloat("energy_Wh", 25.0f));
    }

    public void a() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("energy_Wh", (float)this.b.h());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.energy_Wh, n2)) == R.id.energy_Wh) {
            this.b.a(d2);
            this.b();
            return;
        }
        if (n2 == R.id.energy_BTU) {
            this.c.a(d2);
            this.c();
            return;
        }
        if (n2 == R.id.energy_J) {
            this.d.a(d2);
            this.d();
            return;
        }
        if (n2 == R.id.energy_cal) {
            this.e.a(d2);
            this.e();
            return;
        }
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.energy_Wh) {
            this.b.a(intent, string);
        } else if (n2 == R.id.energy_BTU) {
            this.c.a(intent, string);
        } else if (n2 == R.id.energy_J) {
            this.d.a(intent, string);
        } else if (n2 == R.id.energy_cal) {
            this.e.a(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_energy);
        this.setTitle(R.string.list_conv_energy);
        this.b = new UnitValue("", "Wh", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_Wh), this);
        this.c = new UnitValue("", "BTU", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_BTU), this);
        this.d = new UnitValue("", "J", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_J), this);
        this.e = new UnitValue("", "cal", "", false, (Context)this, (TextView)this.findViewById(R.id.energy_cal), this);
        this.f();
        this.b();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.a();
    }
}
