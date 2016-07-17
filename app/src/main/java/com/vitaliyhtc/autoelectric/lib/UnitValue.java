package com.vitaliyhtc.autoelectric.lib;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaliyhtc.autoelectric.R;

/**
 * Created by VitaliyHTC on 04.07.2016.
 */

public class UnitValue {
    private String a;
    private String b;
    private String c;
    private double d;
    private boolean e;
    private boolean f;
    private float g;
    private float h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private TextView m;
    private char n;
    private String o;
    private String p;
    private Context q;
    private int r;

    public UnitValue(String string, double d2, String string2, Context context) {
        this.a(string, string2, "", true, context, null, null);
        this.d = d2;
    }

    public UnitValue(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener) {
        this.a(string, string2, string3, bl2, context, textView, onClickListener);
    }

    public UnitValue(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener, Boolean bl3) {
        this.a(string, string2, string3, bl2, context, textView, onClickListener);
        this.f = bl3;
    }

    static TextView a(UnitValue unitValue2) {
        return unitValue2.m;
    }

    private void a(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener) {
        this.a = string;
        this.b = string2;
        this.c = string3;
        this.d = 0.0;
        this.e = false;
        this.f = true;
        this.g = 0.0f;
        this.i = true;
        this.k = false;
        if (bl2.booleanValue()) {
            this.h = 0.0f;
            this.j = false;
            this.l = true;
        } else {
            this.h = 0.0f;
            this.j = true;
            this.l = true;
        }
        this.m = textView;
        if (this.m != null) {
            this.m.setOnClickListener(onClickListener);
        }
        this.q = context;
        this.r = 3;
    }

    static int b(UnitValue unitValue2) {
        return unitValue2.r;
    }

    private String c(String string) {
        String string2 = "";
        if (this.f) {
            string2 = this.a;
        }
        if (string.equals("")) {
            return String.valueOf(string2) + this.c + "\u200e" + this.p;
        }
        if (this.n == '\u0000') {
            return String.valueOf(string2) + this.c + "\u200e" + this.p + ' ' + string;
        }
        return String.valueOf(string2) + this.c + "\u200e" + this.p + ' ' + this.n + string;
    }

    private String n() {
        String string;
        double d2;
        this.n = '\u0000';
        String string2 = this.b;
        if (this.d == 0.0) {
            this.p = "0";
            string = string2;
            return this.c(string);
        }
        double d3 = d2 = this.d;
        string = string2;
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this.q).getBoolean("Unit_tempC", true)) {
            d3 = d2;
            string = string2;
            if (this.b.equals("\u00b0C")) {
                d3 = d2 * 9.0 / 5.0 + 32.0;
                string = "\u00b0F";
            }
        }
        this.b(d3);
        return this.c(string);
    }

    private String o() {
        double d2 = this.d;
        if (d2 == 0.0) {
            this.n = '\u0000';
            this.p = "0";
        } else if (d2 < 1.0E-4) {
            this.n = 109;
            d2 *= 1000000.0;
        } else if (d2 < 1.0) {
            this.n = 99;
            d2 *= 10000.0;
        } else {
            this.n = '\u0000';
        }
        this.b(d2);
        return this.c(this.b);
    }

    public Intent a(Intent intent, String string) {
        intent.putExtra(String.valueOf(string) + ".comp_name", this.a);
        intent.putExtra(String.valueOf(string) + ".comp_value", this.d);
        intent.putExtra(String.valueOf(string) + ".comp_unit", this.b);
        intent.putExtra(String.valueOf(string) + ".comp_sign", this.d());
        return intent;
    }

    public String a(Float object) {
        String string;
        string = Double.toString((double)Math.round(Double.valueOf(object.floatValue()) * 1000.0) / 1000.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string = string.substring(0, string.length() - 2);
        }
        return string + " " + this.b;
    }

    public void a(float f2) {
        this.g = f2;
        this.k = true;
        this.i = true;
    }

    public void a(float f2, boolean bl2) {
        this.g = f2;
        this.k = true;
        this.i = bl2;
    }

    public void a(int n2) {
        if (this.m == null) {
            return;
        }
        this.m.setVisibility(n2);
    }

    public void a(String string) {
        this.b = string;
        if (this.m != null) {
            this.m.setText((CharSequence)this.l());
        }
    }

    public void a(boolean bl2) {
        if (this.m == null) {
            return;
        }
        if (bl2) {
            this.m.setVisibility(View.VISIBLE);
        }else {
            this.m.setVisibility(View.INVISIBLE);
        }
    }

    public boolean a() {
        if (this.m == null || this.m.getVisibility() != View.VISIBLE) {
            return false;
        }
        return true;
    }

    public boolean a(double d2) {
        if (this.l) {
            if ((float)d2 <= this.h && this.j) {
                String string = String.format(this.q.getString(R.string.x_mustbe_y), this.j(), "> " + this.a(Float.valueOf(this.h)));
                Toast.makeText((Context)this.q, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((float)d2 < this.h && !this.j) {
                String string = String.format(this.q.getString(R.string.x_mustbe_y), this.j(), "\u2265 " + this.a(Float.valueOf(this.h)));
                Toast.makeText((Context)this.q, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (this.k) {
            if ((float)d2 >= this.g && this.i) {
                String string = String.format(this.q.getString(R.string.x_mustbe_y), this.j(), "< " + this.a(Float.valueOf(this.g)));
                Toast.makeText((Context)this.q, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((float)d2 > this.g && !this.i) {
                String string = String.format(this.q.getString(R.string.x_mustbe_y), this.j(), "\u2264 " + this.a(Float.valueOf(this.g)));
                Toast.makeText((Context)this.q, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        this.d = d2;
        if (this.m != null) {
            this.m.setText((CharSequence)this.l());
            this.m.post((Runnable)new UnitValueTextViewHandler(this));
        }
        return true;
    }

    public float b() {
        return this.g;
    }

    public void b(double d2) {
        double d3 = 1.0;
        int n2 = 0;
        do {
            if (n2 >= this.r) {
                this.p = Double.toString((double)Math.round(d2 * d3) / d3);
                this.p = this.p.replace(',', '.');
                if (this.p.endsWith(".0")) {
                    this.p = this.p.substring(0, this.p.length() - 2);
                }
                return;
            }
            d3 *= 10.0;
            ++n2;
        } while (true);
    }

    public void b(float f2) {
        this.h = f2;
        this.l = true;
        this.j = true;
    }

    public void b(float f2, boolean bl2) {
        this.h = f2;
        this.l = true;
        this.j = bl2;
    }

    public void b(int n2) {
        this.r = n2;
    }

    public void b(String string) {
        this.a = string;
        if (this.m != null) {
            this.m.setText((CharSequence)this.l());
        }
    }

    public void b(boolean bl2) {
        if (this.m == null) {
            return;
        }
        this.m.setEnabled(bl2);
    }

    public float c() {
        return this.h;
    }

    public void c(boolean bl2) {
        this.k = bl2;
    }

    public void d(boolean bl2) {
        this.l = bl2;
    }

    public boolean d() {
        return this.e;
    }

    public String e() {
        this.l();
        return this.p;
    }

    public void e(boolean bl2) {
        this.e = bl2;
    }

    public char f() {
        this.l();
        return this.n;
    }

    public String g() {
        this.l();
        return this.o;
    }

    public double h() {
        return this.d;
    }

    public String i() {
        return this.b;
    }

    public String j() {
        return this.a;
    }

    public void k() {
        if (this.m == null || this.a.indexOf(47) == -1) {
            return;
        }
        String[] arrstring = this.a.split("/");
        this.a = String.valueOf(arrstring[1]) + '/' + arrstring[0];
        this.m.setText((CharSequence)this.l());
    }

    public String l() {
        Boolean var4 = false;
        String object = "";
        if (this.d == Double.POSITIVE_INFINITY) {
            this.p = Double.toString(this.d);
            object = "";
            if (this.f) {
                object = this.a;
            }
            return String.valueOf(object) + this.c + "\u221e";
        }
        if (this.b.equals("")) {
            return this.n();
        }
        if (this.b.equals("bit")) {
            return this.n();
        }
        if (this.b.equals("%")) {
            return this.n();
        }
        if (this.b.equals("RC")) {
            return this.n();
        }
        if (this.b.contains("\u00b0C")) {
            return this.n();
        }
        if (this.b.equals("\u00b0")) {
            return this.n();
        }
        if (this.b.startsWith("dB")) {
            return this.n();
        }
        if (this.b.contains("\u00b2") && !this.b.contains("/")) {
            return this.o();
        }
        if (this.b.equals("s")) {
            var4 = true;
        }
        int n2 = 1;
        double d2 = this.d;
        if (this.d < 0.0) {
            d2 = Math.abs(this.d);
            n2 = -1;
        }
        String string = this.b;
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this.q).getBoolean("Unit_SI", true) && this.b.equals("m")) {
            this.n = '\u0000';
            if ((d2 /= 0.0254) == 0.0) {
                this.p = "0";
                object = string;
            } else if (d2 < 12.0) {
                object = "in";
            } else if (d2 < 63360.0) {
                object = "ft";
                d2 /= 12.0;
            } else {
                object = "mi";
                d2 /= 63360.0;
            }
            this.o = (String)object;
        } else if (d2 == 0.0) {
            this.n = '\u0000';
            this.p = "0";
            object = string;
        } else if (d2 < 1.0E-9) {
            this.n = 'p';
            d2 *= 1.0E12;
            object = string;
        } else if (d2 < 1.0E-6) {
            this.n = 'n';
            d2 *= 1.0E9;
            object = string;
        } else if (d2 < 0.001) {
            this.n = '\u03bc';
            d2 *= 1000000.0;
            object = string;
        } else if (d2 < 1.0) {
            this.n = 'm';
            d2 *= 1000.0;
            object = string;
        } else if (d2 < 1000.0 || var4) {
            this.n = '\u0000';
            object = string;
        } else if (d2 < 1000000.0) {
            this.n = 'k';
            d2 /= 1000.0;
            object = string;
        } else if (d2 < 1.0E9) {
            this.n = 'M';
            d2 /= 1000000.0;
            object = string;
        } else {
            this.n = 'G';
            d2 /= 1.0E9;
            object = string;
        }
        this.b((double)n2 * d2);
        return this.c((String)object);
    }

    public Spanned m() {
        String string = "";
        if (this.f) {
            string = this.a;
        }
        String string2 = "";
        if (!this.b.equals("")) {
            string2 = String.valueOf(' ') + this.b;
        }
        this.b(this.d);
        return Html.fromHtml((String)(String.valueOf(string) + this.c + "10<sup><small>" + this.p + "</small></sup>" + string2));
    }
}
