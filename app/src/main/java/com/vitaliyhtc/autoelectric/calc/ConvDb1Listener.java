package com.vitaliyhtc.autoelectric.calc;

import android.view.View;
import android.widget.AdapterView;

class ConvDb1Listener implements AdapterView.OnItemSelectedListener {
    final ConvDb convDb;

    ConvDb1Listener(ConvDb convDb) {
        this.convDb = convDb;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        this.convDb.b();
    }

    public void onNothingSelected(AdapterView adapterView) {
    }
}
