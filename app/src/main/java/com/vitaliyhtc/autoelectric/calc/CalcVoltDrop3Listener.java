package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcVoltDrop3Listener implements AdapterView.OnItemSelectedListener {
    final CalcVoltDrop calcVoltDrop;

    CalcVoltDrop3Listener(CalcVoltDrop calcVoltDrop) {
        this.calcVoltDrop = calcVoltDrop;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcVoltDrop.c(this.calcVoltDrop, n2);
        CalcVoltDrop.a(this.calcVoltDrop);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
