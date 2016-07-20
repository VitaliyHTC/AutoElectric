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

/**
 * Created by VitaliyHTC on 04.07.2016.
 */
public class SetValueDialog extends AppCompatActivity {
    private EditText editResValue;
    private TextView txtDescr;
    private Spinner spinUnit;
    private SetValueDialogUnit setValueDialogUnit;
    private UnitValue value;
    private int f;
    private int g;
    private SharedPreferences sharedPreferences;
    private String packageName;

    static SharedPreferences a(SetValueDialog setValueDialog) {
        return setValueDialog.sharedPreferences;
    }

    static void a(SetValueDialog setValueDialog, int n2) {
        setValueDialog.f = n2;
    }

    static EditText b(SetValueDialog setValueDialog) {
        return setValueDialog.editResValue;
    }

    public void a() {
        try {
            this.value.a(Double.parseDouble(this.editResValue.getText().toString()));
        }
        catch (NumberFormatException var1_1) {
            this.value.a(0.0);
        }
        if (this.spinUnit.getVisibility() == View.VISIBLE) {
            if (!this.sharedPreferences.getBoolean("Unit_tempC", true) && this.value.i().equals("\u00b0C")) {
                this.value.a((this.value.h() - 32.0) * 5.0 / 9.0);
            } else {
                this.value.a(this.value.h() * this.setValueDialogUnit.a(this.f));
            }
        }
        this.value.a(this.value.h() * (double)this.g);
        Intent intent = new Intent();
        intent.putExtra(String.valueOf(this.packageName) + ".comp_name", this.value.j());
        intent.putExtra(String.valueOf(this.packageName) + ".comp_value", this.value.h());
        intent.putExtra(String.valueOf(this.packageName) + ".comp_unit", this.value.i());
        this.setResult(-1, intent);
        ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.editResValue.getWindowToken(), 0);
        this.finish();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.set_value_dialog);
        this.f = 0;
        this.editResValue = (EditText)this.findViewById(R.id.editResValue);
        this.txtDescr = (TextView)this.findViewById(R.id.txtDescr);
        this.spinUnit = (Spinner)this.findViewById(R.id.spinUnit);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        this.packageName = this.getPackageName();
        bundle = this.getIntent().getExtras();
        String string = bundle.getString(String.valueOf(this.packageName) + ".comp_name");
        double d2 = bundle.getDouble(String.valueOf(this.packageName) + ".comp_value", 0.0);
        String string2 = bundle.getString(String.valueOf(this.packageName) + ".comp_unit");
        boolean bl2 = bundle.getBoolean(String.valueOf(this.packageName) + ".comp_sign");
        if (string != null) {
            this.txtDescr.setText((CharSequence)String.format(this.getString(R.string.insert_res), string));
        }
        this.value = new UnitValue(string, d2, string2, (Context)this);
        this.value.e(bl2);
        this.value.d(false);
        this.value.c(false);
        this.value.b(5);
        if (this.value.d()) {
            this.editResValue.setInputType(12290);
            this.g = 1;
        } else if (this.value.h() < 0.0) {
            this.value.a(Math.abs(this.value.h()));
            this.g = -1;
        } else {
            this.g = 1;
        }
        if (this.value.h() == 0.0) {
            this.editResValue.setText((CharSequence)"");
        } else {
            this.editResValue.setText((CharSequence)this.value.e());
        }
        if (this.sharedPreferences.getBoolean("Text_Highlighted", true)) {
            this.editResValue.selectAll();
        }
        ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(2, 0);
        this.setValueDialogUnit = new SetValueDialogUnit(this, string2);
        if (this.setValueDialogUnit.b() > 0) {
            this.f = !this.sharedPreferences.getBoolean("Unit_SI", true) && this.value.i().equals("m") ? this.setValueDialogUnit.a(this.value.g()) : this.setValueDialogUnit.a(this.value.f());
            ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, android.R.layout.simple_spinner_item, (List)this.setValueDialogUnit.a());
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinUnit.setAdapter((SpinnerAdapter)arrayAdapter);
            this.spinUnit.setSelection(this.f);
            if (this.setValueDialogUnit.b() == 1) {
                this.spinUnit.setEnabled(false);
            }
        } else {
            this.spinUnit.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(-2, -2);
            relativeLayout.addRule(5, R.id.txtDescr);
            relativeLayout.addRule(7, R.id.txtDescr);
            relativeLayout.addRule(3, R.id.txtDescr);
            this.editResValue.setLayoutParams((ViewGroup.LayoutParams)relativeLayout);
        }
        this.editResValue.setOnEditorActionListener((TextView.OnEditorActionListener)new SetValueDialog_editResValueListener(this));
        ((Button)this.findViewById(R.id.btnOK)).setOnClickListener((View.OnClickListener)new SetValueDialog_btnOKListener(this));
        ((Button)this.findViewById(R.id.btnCancel)).setOnClickListener((View.OnClickListener)new SetValueDialog_btnCancelListener(this));
        this.spinUnit.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)new SetValueDialog_spinUnitItemSelectedListener(this));
    }

}
