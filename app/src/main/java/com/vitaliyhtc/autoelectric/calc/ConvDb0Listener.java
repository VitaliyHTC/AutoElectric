package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class ConvDb0Listener implements AdapterView.OnItemSelectedListener {
    final ConvDb convDb;

    ConvDb0Listener(ConvDb convDb) {
        this.convDb = convDb;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        this.convDb.a();
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
