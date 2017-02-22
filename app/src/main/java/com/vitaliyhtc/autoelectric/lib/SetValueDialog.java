package com.vitaliyhtc.autoelectric.lib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.vitaliyhtc.autoelectric.R;

import java.util.List;

public class SetValueDialog extends AppCompatActivity {
    private EditText editTextValue;
    private TextView textView;
    private Spinner spinUnit;
    private SetValueDialogUnit setValueDialogUnit;
    private UnitValue value;
    private int spinUnitItemSelectedInt;
    private int g;
    private SharedPreferences sharedPreferences;
    private String packageName;

    static SharedPreferences getSharedPreferences(SetValueDialog setValueDialog) {
        return setValueDialog.sharedPreferences;
    }

    static void setSpinUnitItemSelectedInt(SetValueDialog setValueDialog, int n2) {
        setValueDialog.spinUnitItemSelectedInt = n2;
    }

    static EditText getEditText(SetValueDialog setValueDialog) {
        return setValueDialog.editTextValue;
    }

    public void actionSetResult() {
        try {
            this.value.validateUnitValueDouble(Double.parseDouble(this.editTextValue.getText().toString()));
        }
        catch (NumberFormatException var1_1) {
            this.value.validateUnitValueDouble(0.0);
        }
        if (this.spinUnit.getVisibility() == View.VISIBLE) {
            if (!this.sharedPreferences.getBoolean("Unit_tempC", true) && this.value.getUnitSymbol().equals("\u00b0C")) {
                this.value.validateUnitValueDouble((this.value.getUnitValue() - 32.0) * 5.0 / 9.0);
            } else {
                this.value.validateUnitValueDouble(this.value.getUnitValue() * this.setValueDialogUnit.getUnitMultiplier(this.spinUnitItemSelectedInt));
            }
        }
        this.value.validateUnitValueDouble(this.value.getUnitValue() * (double)this.g);
        Intent intent = new Intent();
        intent.putExtra(String.valueOf(this.packageName) + ".comp_name", this.value.getUnitName());
        intent.putExtra(String.valueOf(this.packageName) + ".comp_value", this.value.getUnitValue());
        intent.putExtra(String.valueOf(this.packageName) + ".comp_unit", this.value.getUnitSymbol());
        this.setResult(-1, intent);
        ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.editTextValue.getWindowToken(), 0);
        this.finish();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.set_value_dialog);
        this.spinUnitItemSelectedInt = 0;
        this.editTextValue = (EditText)this.findViewById(R.id.editResValue);
        this.textView = (TextView)this.findViewById(R.id.txtDescr);
        this.spinUnit = (Spinner)this.findViewById(R.id.spinUnit);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        this.packageName = this.getPackageName();
        bundle = this.getIntent().getExtras();
        String string = bundle.getString(String.valueOf(this.packageName) + ".comp_name");
        double d2 = bundle.getDouble(String.valueOf(this.packageName) + ".comp_value", 0.0);
        String string2 = bundle.getString(String.valueOf(this.packageName) + ".comp_unit");
        boolean bl2 = bundle.getBoolean(String.valueOf(this.packageName) + ".comp_sign");
        if (string != null) {
            this.textView.setText((CharSequence)String.format(this.getString(R.string.insert_res), string));
        }
        this.value = new UnitValue(string, d2, string2, (Context)this);
        this.value.setCompSign(bl2);
        this.value.setMustBeGreaterThan(false);
        this.value.setMustBeLessThan(false);
        this.value.setRoundDigits(5);
        if (this.value.getCompSign()) {
            this.editTextValue.setInputType(12290);
            this.g = 1;
        } else if (this.value.getUnitValue() < 0.0) {
            this.value.validateUnitValueDouble(Math.abs(this.value.getUnitValue()));
            this.g = -1;
        } else {
            this.g = 1;
        }
        if (this.value.getUnitValue() == 0.0) {
            this.editTextValue.setText((CharSequence)"");
        } else {
            this.editTextValue.setText((CharSequence)this.value.getUnitValueString());
        }
        if (this.sharedPreferences.getBoolean("Text_Highlighted", true)) {
            this.editTextValue.selectAll();
        }
        ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(2, 0);
        this.setValueDialogUnit = new SetValueDialogUnit(this, string2);
        if (this.setValueDialogUnit.getUnitsArraySymbolsSize() > 0) {
            this.spinUnitItemSelectedInt = !this.sharedPreferences.getBoolean("Unit_SI", true) && this.value.getUnitSymbol().equals("m")
                    ? this.setValueDialogUnit.getUnitSymbolIndex(this.value.getLengthSymbolString()) : this.setValueDialogUnit.getCompUnitIndex(this.value.getPrefixSymbol());
            ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (List)this.setValueDialogUnit.getUnitsArraySymbols());
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinUnit.setAdapter((SpinnerAdapter)arrayAdapter);
            this.spinUnit.setSelection(this.spinUnitItemSelectedInt);
            if (this.setValueDialogUnit.getUnitsArraySymbolsSize() == 1) {
                this.spinUnit.setEnabled(false);
            }
        } else {
            this.spinUnit.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(-2, -2);
            relativeLayout.addRule(5, R.id.txtDescr);
            relativeLayout.addRule(7, R.id.txtDescr);
            relativeLayout.addRule(3, R.id.txtDescr);
            this.editTextValue.setLayoutParams((ViewGroup.LayoutParams)relativeLayout);
        }
        this.editTextValue.setOnEditorActionListener((TextView.OnEditorActionListener)new SetValueDialog_editTextValueListener(this));
        ((Button)this.findViewById(R.id.btnOK)).setOnClickListener((View.OnClickListener)new SetValueDialog_btnOKListener(this));
        ((Button)this.findViewById(R.id.btnCancel)).setOnClickListener((View.OnClickListener)new SetValueDialog_btnCancelListener(this));
        this.spinUnit.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new SetValueDialog_spinUnitItemSelectedListener(this));
    }

}
