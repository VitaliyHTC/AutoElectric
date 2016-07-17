package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class CalcVoltDrop2Listener implements AdapterView.OnItemSelectedListener {
    final CalcVoltDrop calcVoltDrop;

    CalcVoltDrop2Listener(CalcVoltDrop calcVoltDrop) {
        this.calcVoltDrop = calcVoltDrop;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        CalcVoltDrop.b(this.calcVoltDrop);
        CalcVoltDrop.a(this.calcVoltDrop);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
