package com.vitaliyhtc.autoelectric.lib;

import android.view.View;
import android.widget.AdapterView;

class SetValueDialog_spinUnitItemSelectedListener implements AdapterView.OnItemSelectedListener {
    final SetValueDialog setValueDialog;

    SetValueDialog_spinUnitItemSelectedListener(SetValueDialog setValueDialog) {
        this.setValueDialog = setValueDialog;
    }

    public void onItemSelected(AdapterView adapterView, View view, int n2, long l2) {
        SetValueDialog.setSpinUnitItemSelectedInt(this.setValueDialog, n2);
    }

    public void onNothingSelected(AdapterView adapterView) {}
}
