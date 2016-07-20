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
import com.vitaliyhtc.autoelectric.lib.SetValueDialog;
import com.vitaliyhtc.autoelectric.lib.UnitValue;

public class ConvDb extends CalcActivity implements View.OnClickListener {
    String[] b = new String[]{"dBm", "dBW", "dB"};
    String[] c = new String[]{"dBV", "dBmV", "dBuV", "dBu", "dB"};
    String[] d = new String[]{"dBuA", "dB"};
    String[] e = new String[]{"dBHz", "dB"};
    String[] f = new String[]{"dBSPL", "dBSIL", "dBSWL", "dB"};
    private UnitValue g;
    private UnitValue h;
    private UnitValue i;
    private UnitValue j;
    private Spinner k;
    private Spinner l;

    void a() {
        String[] var1 = null;
        switch(this.k.getSelectedItemPosition()) {
            case 0:
                var1 = this.b;
                this.a(0.001D, "W", "dBm", this.getString(R.string.power));
                break;
            case 1:
                var1 = this.c;
                this.a(1.0D, "V", "dBV", this.getString(R.string.voltage));
                break;
            case 2:
                var1 = this.d;
                this.a(1.0E-6D, "A", "dBuA", this.getString(R.string.current));
                break;
            case 3:
                var1 = this.e;
                this.a(1.0D, "Hz", "dBHz", this.getString(R.string.frequ));
                break;
            case 4:
                var1 = this.f;
                this.a(2.0E-5D, "Pa", "dBSPL", this.getString(R.string.soundpress));
        }
        ArrayAdapter var2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, var1);
        var2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.l.setAdapter(var2);
        this.e();
    }

    void a(double d2, String string, String string2, String string3) {
        if (d2 != 0.0) {
            this.i.a(d2);
        }
        this.i.a(string);
        this.h.a(string);
        this.h.b(string3);
        this.g.a(string2);
        if (string2 == "dB") {
            this.i.b(true);
            return;
        }
        this.i.b(false);
    }

    void b() {
        label47:
        switch(this.k.getSelectedItemPosition()) {
            case 0:
                switch(this.l.getSelectedItemPosition()) {
                    case 0:
                        this.a(0.001D, "W", "dBm", this.getString(R.string.power));
                        break label47;
                    case 1:
                        this.a(1.0D, "W", "dBW", this.getString(R.string.power));
                        break label47;
                    case 2:
                        this.a(0.0D, "W", "dB", this.getString(R.string.power));
                    default:
                        break label47;
                }
            case 1:
                switch(this.l.getSelectedItemPosition()) {
                    case 0:
                        this.a(1.0D, "V", "dBV", this.getString(R.string.voltage));
                        break label47;
                    case 1:
                        this.a(0.001D, "V", "dBmV", this.getString(R.string.voltage));
                        break label47;
                    case 2:
                        this.a(1.0E-6D, "V", "dBuV", this.getString(R.string.voltage));
                        break label47;
                    case 3:
                        this.a(0.7746D, "V", "dBu", this.getString(R.string.voltage));
                        break label47;
                    case 4:
                        this.a(0.0D, "V", "dB", this.getString(R.string.voltage));
                    default:
                        break label47;
                }
            case 2:
                switch(this.l.getSelectedItemPosition()) {
                    case 0:
                        this.a(1.0E-6D, "A", "dBuA", this.getString(R.string.current));
                        break label47;
                    case 1:
                        this.a(0.0D, "A", "dB", this.getString(R.string.current));
                    default:
                        break label47;
                }
            case 3:
                switch(this.l.getSelectedItemPosition()) {
                    case 0:
                        this.a(1.0D, "Hz", "dBHz", this.getString(R.string.frequ));
                        break label47;
                    case 1:
                        this.a(0.0D, "Hz", "dB", this.getString(R.string.frequ));
                    default:
                        break label47;
                }
            case 4:
                switch(this.l.getSelectedItemPosition()) {
                    case 0:
                        this.a(2.0E-5D, "Pa", "dBSPL", this.getString(R.string.soundpress));
                        break;
                    case 1:
                        this.a(1.0E-12D, "W/m²", "dBSIL", this.getString(R.string.soundpress));
                        break;
                    case 2:
                        this.a(1.0E-12D, "W", "dBSWL", this.getString(R.string.soundpress));
                        break;
                    case 3:
                        this.a(0.0D, "Pa", "dB", this.getString(R.string.soundpress));
                }
        }
        this.e();
    }

    void c() {
        int n2 = this.k.getSelectedItemPosition();
        double d2 = this.h.h() / this.i.h();
        this.j.a(d2);
        n2 = n2 == 0 || n2 == 3 ? 10 : 20;
        UnitValue unitValue2 = this.g;
        double d3 = n2;
        unitValue2.a(Math.log(d2) * d3 * Math.log10(2.718281828459045));
    }

    void d() {
        this.h.a(this.j.h() * this.i.h());
        this.c();
    }

    void e() {
        int n2 = this.k.getSelectedItemPosition();
        n2 = n2 == 0 || n2 == 3 ? 10 : 20;
        double d2 = Math.pow(10.0, this.g.h() / (double)n2);
        this.h.a(this.i.h() * d2);
        this.j.a(d2);
    }

    private void readSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Calc_Setting", 0);
        this.h.a((double)sharedPreferences.getFloat("conv_db_val", 100.0f));
        this.i.a((double)sharedPreferences.getFloat("conv_db_rif", 1.0f));
        this.k.setSelection(sharedPreferences.getInt("conv_db_spinType", 0));
        this.l.setSelection(sharedPreferences.getInt("conv_db_spinUnit", 0));
    }

    public void writeSharedPreferences() {
        SharedPreferences.Editor editor = this.getSharedPreferences("Calc_Setting", 0).edit();
        editor.putFloat("conv_db_val", (float)this.h.h());
        editor.putFloat("conv_db_rif", (float)this.i.h());
        editor.putInt("conv_db_spinType", this.k.getSelectedItemPosition());
        editor.putInt("conv_db_spinUnit", this.l.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    public void onActivityResult(int n2, int n3, Intent intent) {
        super.onActivityResult(n2, n3, intent);
        if (n3 != -1) {
            return;
        }
        double d2 = intent.getDoubleExtra(String.valueOf(this.getPackageName()) + ".comp_value", 0.0);
        if ((n2 = this.a(R.id.db_btn_decibel, n2)) == R.id.db_btn_decibel) {
            this.g.a(d2);
            this.e();
            return;
        }
        if (n2 == R.id.db_btn_value) {
            this.h.a(d2);
            this.c();
            return;
        }
        if (n2 == R.id.db_btn_gain) {
            this.j.a(d2);
            this.d();
            return;
        }
        if (n2 != R.id.db_btn_ref) return;
        this.i.a(d2);
        this.d();
    }

    public void onClick(View view) {
        String string = this.getPackageName();
        Intent intent = new Intent((Context)this, (Class)SetValueDialog.class);
        int n2 = view.getId();
        if (n2 == R.id.db_btn_decibel) {
            this.g.a(intent, string);
        } else if (n2 == R.id.db_btn_value) {
            this.h.a(intent, string);
        } else if (n2 == R.id.db_btn_gain) {
            this.j.a(intent, string);
        } else if (n2 == R.id.db_btn_ref) {
            this.i.a(intent, string);
        }
        this.startActivityForResult(intent, n2);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.conv_db);
        this.setTitle(R.string.list_conv_db);
        this.g = new UnitValue(this.getString(R.string.decibel), "", "\n", true, (Context)this, (TextView)this.findViewById(R.id.db_btn_decibel), this);
        this.g.e(true);
        this.g.d(false);
        this.h = new UnitValue("***", "", "\n", false, (Context)this, (TextView)this.findViewById(R.id.db_btn_value), this);
        this.i = new UnitValue(this.getString(R.string.db_ref), "", "\n", false, (Context)this, (TextView)this.findViewById(R.id.db_btn_ref), this);
        this.j = new UnitValue(this.getString(R.string.gain), "", "\n", false, (Context)this, (TextView)this.findViewById(R.id.db_btn_gain), this);
        this.k = (Spinner)this.findViewById(R.id.db_spinType);
        this.l = (Spinner)this.findViewById(R.id.db_spin_unit);
        ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (Object[])new String[]{this.getString(R.string.power), this.getString(R.string.voltage), this.getString(R.string.current), this.getString(R.string.frequ), this.getString(R.string.soundpress)});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.k.setAdapter((SpinnerAdapter)arrayAdapter);
        this.k.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new ConvDb0Listener(this));
        this.l.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new ConvDb1Listener(this));
        this.readSharedPreferences();
        this.c();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.writeSharedPreferences();
    }
}
