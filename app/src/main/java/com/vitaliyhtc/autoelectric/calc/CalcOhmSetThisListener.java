package com.vitaliyhtc.autoelectric.calc;

import android.view.View;

class CalcOhmSetThisListener implements View.OnClickListener {
    final CalcOhm calcOhm;

    CalcOhmSetThisListener(CalcOhm calcOhm) {
        this.calcOhm = calcOhm;
    }

    public void onClick(View view) {
        CalcOhm.a(this.calcOhm).validateUnitValueDouble(CalcOhm.b(this.calcOhm).getUnitValue());
        CalcOhm.a(this.calcOhm, CalcOhm.b);
    }
}
