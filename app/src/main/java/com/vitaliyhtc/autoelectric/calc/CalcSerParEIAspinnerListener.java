package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcSerParEIAspinnerListener implements AdapterView.OnItemSelectedListener {
    final CalcSerPar calcSerPar;

    CalcSerParEIAspinnerListener(CalcSerPar calcSerPar) {
        this.calcSerPar = calcSerPar;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcSerPar.a(this.calcSerPar, n2);
        CalcSerPar.a(this.calcSerPar).selectEIAValuesLine(n2);
        CalcSerPar.b(this.calcSerPar);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
