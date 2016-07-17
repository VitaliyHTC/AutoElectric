package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcVoltDrop1Listener implements AdapterView.OnItemSelectedListener {
    final CalcVoltDrop calcVoltDrop;

    CalcVoltDrop1Listener(CalcVoltDrop calcVoltDrop) {
        this.calcVoltDrop = calcVoltDrop;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcVoltDrop.b(this.calcVoltDrop, n2);
        CalcVoltDrop.a(this.calcVoltDrop);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
