package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class ConvFreq0Listener implements AdapterView.OnItemSelectedListener {
    final ConvFreq convFreq;

    ConvFreq0Listener(ConvFreq convFreq) {
        this.convFreq = convFreq;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        ConvFreq.a(this.convFreq, n2);
        ConvFreq.a(this.convFreq);
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
