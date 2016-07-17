package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcPowerCurrentTypeListener implements AdapterView.OnItemSelectedListener {
    final CalcPower calcPower;

    CalcPowerCurrentTypeListener(CalcPower calcPower) {
        this.calcPower = calcPower;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcPower.a(this.calcPower, n2);
        CalcPower.a(this.calcPower);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
