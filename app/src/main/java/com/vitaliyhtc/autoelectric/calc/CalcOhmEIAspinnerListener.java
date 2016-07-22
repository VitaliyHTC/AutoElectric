package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcOhmEIAspinnerListener implements AdapterView.OnItemSelectedListener {
    final CalcOhm calcOhm;

    CalcOhmEIAspinnerListener(CalcOhm calcOhm) {
        this.calcOhm = calcOhm;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcOhm.b(this.calcOhm, n2);
        CalcOhm.c(this.calcOhm).selectEIAValuesLine(n2);
        CalcOhm.d(this.calcOhm);
    }

    public void onNothingSelected(AdapterView adapterView) {}
}
