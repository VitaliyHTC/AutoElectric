package com.vitaliyhtc.autoelectric.calc;

import android.widget.CompoundButton;

class CalcVoltageDividerLoadListener implements CompoundButton.OnCheckedChangeListener {
    final CalcVoltageDivider calcVoltageDivider;

    CalcVoltageDividerLoadListener(CalcVoltageDivider calcVoltageDivider) {
        this.calcVoltageDivider = calcVoltageDivider;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean bl2) {
        this.calcVoltageDivider.a();
    }
}
