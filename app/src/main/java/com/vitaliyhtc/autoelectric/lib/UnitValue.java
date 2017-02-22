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

public class UnitValue {
    private String unitName;
    private String unitSymbol;
    private String formatString;
    private double unitValue;
    private boolean compSign;
    private boolean unitHasName;
    private float topLimit;
    private float bottomLimit;
    private boolean mustBeLessOrEqual;
    private boolean mustBeGreaterOrEqual;
    private boolean mustBeLessThan;
    private boolean mustBeGreaterThen;
    private TextView textView;
    private char prefixSymbol;
    private String lengthSymbolString;
    private String unitValueString;
    private Context context;
    private int roundDigits;

    public UnitValue(String string, double d2, String string2, Context context) {
        this.unitValueConstructor(string, string2, "", true, context, null, null);
        this.unitValue = d2;
    }

    public UnitValue(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener) {
        this.unitValueConstructor(string, string2, string3, bl2, context, textView, onClickListener);
    }

    public UnitValue(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener, Boolean bl3) {
        this.unitValueConstructor(string, string2, string3, bl2, context, textView, onClickListener);
        this.unitHasName = bl3;
    }

    static TextView getTextView(UnitValue unitValue2) {
        return unitValue2.textView;
    }
    static int getRoundDigits(UnitValue unitValue2) {
        return unitValue2.roundDigits;
    }

    private void unitValueConstructor(String string, String string2, String string3, Boolean bl2, Context context, TextView textView, View.OnClickListener onClickListener) {
        this.unitName = string;
        this.unitSymbol = string2;
        this.formatString = string3;
        this.unitValue = 0.0;
        this.compSign = false;
        this.unitHasName = true;
        this.topLimit = 0.0f;
        this.mustBeLessOrEqual = true;
        this.mustBeLessThan = false;
        if (bl2.booleanValue()) {
            this.bottomLimit = 0.0f;
            this.mustBeGreaterOrEqual = false;
            this.mustBeGreaterThen = true;
        } else {
            this.bottomLimit = 0.0f;
            this.mustBeGreaterOrEqual = true;
            this.mustBeGreaterThen = true;
        }
        this.textView = textView;
        if (this.textView != null) {
            this.textView.setOnClickListener(onClickListener);
        }
        this.context = context;
        this.roundDigits = 3;
    }

    private String formatResultString(String string) {
        String string2 = "";
        if (this.unitHasName) {
            string2 = this.unitName;
        }
        if (string.equals("")) {
            return String.valueOf(string2) + this.formatString + "\u200e" + this.unitValueString;
        }
        if (this.prefixSymbol == '\u0000') {
            return String.valueOf(string2) + this.formatString + "\u200e" + this.unitValueString + ' ' + string;
        }
        return String.valueOf(string2) + this.formatString + "\u200e" + this.unitValueString + ' ' + this.prefixSymbol + string;
    }

    private String formString() {
        String string;
        double d2;
        this.prefixSymbol = '\u0000';
        String string2 = this.unitSymbol;
        if (this.unitValue == 0.0) {
            this.unitValueString = "0";
            string = string2;
            return this.formatResultString(string);
        }
        double d3 = d2 = this.unitValue;
        string = string2;
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this.context).getBoolean("Unit_tempC", true)) {
            d3 = d2;
            string = string2;
            if (this.unitSymbol.equals("\u00b0C")) {
                d3 = d2 * 9.0 / 5.0 + 32.0;
                string = "\u00b0F";
            }
        }
        this.roundDouble(d3);
        return this.formatResultString(string);
    }

    private String squaredSymbol() {
        double d2 = this.unitValue;
        if (d2 == 0.0) {
            this.prefixSymbol = '\u0000';
            this.unitValueString = "0";
        } else if (d2 < 1.0E-4) {
            this.prefixSymbol = 109;
            d2 *= 1000000.0;
        } else if (d2 < 1.0) {
            this.prefixSymbol = 99;
            d2 *= 10000.0;
        } else {
            this.prefixSymbol = '\u0000';
        }
        this.roundDouble(d2);
        return this.formatResultString(this.unitSymbol);
    }

    public Intent setValueDialogIntent(Intent intent, String string) {
        intent.putExtra(String.valueOf(string) + ".comp_name", this.unitName);
        intent.putExtra(String.valueOf(string) + ".comp_value", this.unitValue);
        intent.putExtra(String.valueOf(string) + ".comp_unit", this.unitSymbol);
        intent.putExtra(String.valueOf(string) + ".comp_sign", this.getCompSign());
        return intent;
    }

    public String roundUnitValue(Float object) {
        String string;
        string = Double.toString((double)Math.round(Double.valueOf(object.floatValue()) * 1000.0) / 1000.0).replace(',', '.');
        if (string.endsWith(".0")) {
            string = string.substring(0, string.length() - 2);
        }
        return string + " " + this.unitSymbol;
    }

    public void setTopLimit(float f2) {
        this.topLimit = f2;
        this.mustBeLessThan = true;
        this.mustBeLessOrEqual = true;
    }

    public void setTopLimit(float f2, boolean bl2) {
        this.topLimit = f2;
        this.mustBeLessThan = true;
        this.mustBeLessOrEqual = bl2;
    }

    public void setTextViewVisibility(int n2) {
        if (this.textView == null) {
            return;
        }
        this.textView.setVisibility(n2);
    }

    public void setUnitSymbol(String string) {
        this.unitSymbol = string;
        if (this.textView != null) {
            this.textView.setText((CharSequence)this.getTextViewString());
        }
    }

    public void setTextViewVisibility(boolean bl2) {
        if (this.textView == null) {
            return;
        }
        if (bl2) {
            this.textView.setVisibility(View.VISIBLE);
        }else {
            this.textView.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isTextViewVisible() {
        if (this.textView == null || this.textView.getVisibility() != View.VISIBLE) {
            return false;
        }
        return true;
    }

    public boolean validateUnitValueDouble(double d2) {
        if (this.mustBeGreaterThen) {
            if ((float)d2 <= this.bottomLimit && this.mustBeGreaterOrEqual) {
                String string = String.format(this.context.getString(R.string.x_mustbe_y), this.getUnitName(), "> " + this.roundUnitValue(Float.valueOf(this.bottomLimit)));
                Toast.makeText((Context)this.context, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((float)d2 < this.bottomLimit && !this.mustBeGreaterOrEqual) {
                String string = String.format(this.context.getString(R.string.x_mustbe_y), this.getUnitName(), "\u2265 " + this.roundUnitValue(Float.valueOf(this.bottomLimit)));
                Toast.makeText((Context)this.context, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (this.mustBeLessThan) {
            if ((float)d2 >= this.topLimit && this.mustBeLessOrEqual) {
                String string = String.format(this.context.getString(R.string.x_mustbe_y), this.getUnitName(), "< " + this.roundUnitValue(Float.valueOf(this.topLimit)));
                Toast.makeText((Context)this.context, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((float)d2 > this.topLimit && !this.mustBeLessOrEqual) {
                String string = String.format(this.context.getString(R.string.x_mustbe_y), this.getUnitName(), "\u2264 " + this.roundUnitValue(Float.valueOf(this.topLimit)));
                Toast.makeText((Context)this.context, (CharSequence)string, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        this.unitValue = d2;
        if (this.textView != null) {
            this.textView.setText((CharSequence)this.getTextViewString());
            this.textView.post((Runnable)new UnitValueTextViewHandler(this));
        }
        return true;
    }

    public float getTopLimit() {
        return this.topLimit;
    }

    public void roundDouble(double d2) {
        double d3 = 1.0;
        int n2 = 0;
        do {
            if (n2 >= this.roundDigits) {
                this.unitValueString = Double.toString((double)Math.round(d2 * d3) / d3);
                this.unitValueString = this.unitValueString.replace(',', '.');
                if (this.unitValueString.endsWith(".0")) {
                    this.unitValueString = this.unitValueString.substring(0, this.unitValueString.length() - 2);
                }
                return;
            }
            d3 *= 10.0;
            ++n2;
        } while (true);
    }

    public void setBottomLimit(float f2) {
        this.bottomLimit = f2;
        this.mustBeGreaterThen = true;
        this.mustBeGreaterOrEqual = true;
    }

    public void setBottomLimit(float f2, boolean bl2) {
        this.bottomLimit = f2;
        this.mustBeGreaterThen = true;
        this.mustBeGreaterOrEqual = bl2;
    }

    public void setRoundDigits(int n2) {
        this.roundDigits = n2;
    }

    public void setTextViewString(String string) {
        this.unitName = string;
        if (this.textView != null) {
            this.textView.setText((CharSequence)this.getTextViewString());
        }
    }

    public void enableTextView(boolean bl2) {
        if (this.textView == null) {
            return;
        }
        this.textView.setEnabled(bl2);
    }

    public float getBottomLimit() {
        return this.bottomLimit;
    }

    public void setMustBeLessThan(boolean bl2) {
        this.mustBeLessThan = bl2;
    }

    public void setMustBeGreaterThan(boolean bl2) {
        this.mustBeGreaterThen = bl2;
    }

    public boolean getCompSign() {
        return this.compSign;
    }

    public String getUnitValueString() {
        this.getTextViewString();
        return this.unitValueString;
    }

    public void setCompSign(boolean bl2) {
        this.compSign = bl2;
    }

    public char getPrefixSymbol() {
        this.getTextViewString();
        return this.prefixSymbol;
    }

    public String getLengthSymbolString() {
        this.getTextViewString();
        return this.lengthSymbolString;
    }

    public double getUnitValue() {
        return this.unitValue;
    }

    public String getUnitSymbol() {
        return this.unitSymbol;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public void slashSymbol() {
        if (this.textView == null || this.unitName.indexOf(47) == -1) {
            return;
        }
        String[] arrstring = this.unitName.split("/");
        this.unitName = String.valueOf(arrstring[1]) + '/' + arrstring[0];
        this.textView.setText((CharSequence)this.getTextViewString());
    }

    public String getTextViewString() {
        Boolean var4 = false;
        String object = "";
        if (this.unitValue == Double.POSITIVE_INFINITY) {
            this.unitValueString = Double.toString(this.unitValue);
            object = "";
            if (this.unitHasName) {
                object = this.unitName;
            }
            return String.valueOf(object) + this.formatString + "\u221e";
        }
        if (this.unitSymbol.equals("")) {
            return this.formString();
        }
        if (this.unitSymbol.equals("bit")) {
            return this.formString();
        }
        if (this.unitSymbol.equals("%")) {
            return this.formString();
        }
        if (this.unitSymbol.equals("RC")) {
            return this.formString();
        }
        if (this.unitSymbol.contains("\u00b0C")) {
            return this.formString();
        }
        if (this.unitSymbol.equals("\u00b0")) {
            return this.formString();
        }
        if (this.unitSymbol.startsWith("dB")) {
            return this.formString();
        }
        if (this.unitSymbol.contains("\u00b2") && !this.unitSymbol.contains("/")) {
            return this.squaredSymbol();
        }
        if (this.unitSymbol.equals("s")) {
            var4 = true;
        }
        int n2 = 1;
        double d2 = this.unitValue;
        if (this.unitValue < 0.0) {
            d2 = Math.abs(this.unitValue);
            n2 = -1;
        }
        String string = this.unitSymbol;
        if (!PreferenceManager.getDefaultSharedPreferences((Context)this.context).getBoolean("Unit_SI", true) && this.unitSymbol.equals("m")) {
            this.prefixSymbol = '\u0000';
            if ((d2 /= 0.0254) == 0.0) {
                this.unitValueString = "0";
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
            this.lengthSymbolString = (String)object;
        } else if (d2 == 0.0) {
            this.prefixSymbol = '\u0000';
            this.unitValueString = "0";
            object = string;
        } else if (d2 < 1.0E-9) {
            this.prefixSymbol = 'p';
            d2 *= 1.0E12;
            object = string;
        } else if (d2 < 1.0E-6) {
            this.prefixSymbol = 'n';
            d2 *= 1.0E9;
            object = string;
        } else if (d2 < 0.001) {
            this.prefixSymbol = '\u03bc';
            d2 *= 1000000.0;
            object = string;
        } else if (d2 < 1.0) {
            this.prefixSymbol = 'm';
            d2 *= 1000.0;
            object = string;
        } else if (d2 < 1000.0 || var4) {
            this.prefixSymbol = '\u0000';
            object = string;
        } else if (d2 < 1000000.0) {
            this.prefixSymbol = 'k';
            d2 /= 1000.0;
            object = string;
        } else if (d2 < 1.0E9) {
            this.prefixSymbol = 'M';
            d2 /= 1000000.0;
            object = string;
        } else {
            this.prefixSymbol = 'G';
            d2 /= 1.0E9;
            object = string;
        }
        this.roundDouble((double)n2 * d2);
        return this.formatResultString((String)object);
    }

    public Spanned getHtmlString() {
        String string = "";
        if (this.unitHasName) {
            string = this.unitName;
        }
        String string2 = "";
        if (!this.unitSymbol.equals("")) {
            string2 = String.valueOf(' ') + this.unitSymbol;
        }
        this.roundDouble(this.unitValue);
        return Html.fromHtml((String)(String.valueOf(string) + this.formatString + "10<sup><small>" + this.unitValueString + "</small></sup>" + string2));
    }
}
