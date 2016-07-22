package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcVoltageDividerEIAspinnerListener implements AdapterView.OnItemSelectedListener {
    final CalcVoltageDivider calcVoltageDivider;

    CalcVoltageDividerEIAspinnerListener(CalcVoltageDivider calcVoltageDivider) {
        this.calcVoltageDivider = calcVoltageDivider;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcVoltageDivider.a(this.calcVoltageDivider).setSelection(n2);
        CalcVoltageDivider.b(this.calcVoltageDivider).selectEIAValuesLine(n2);
        this.calcVoltageDivider.a(CalcVoltageDivider.c(this.calcVoltageDivider).getUnitValue() /
                CalcVoltageDivider.d(this.calcVoltageDivider).getUnitValue() - 1.0);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
