package com.vitaliyhtc.autoelectric.calc;

import android.view.View;

class CalcOhmSetThisListener implements View.OnClickListener {
    final CalcOhm calcOhm;

    CalcOhmSetThisListener(CalcOhm calcOhm) {
        this.calcOhm = calcOhm;
    }

    public void onClick(View view) {
        CalcOhm.a(this.calcOhm).a(CalcOhm.b(this.calcOhm).h());
        CalcOhm.a(this.calcOhm, CalcOhm.b);
    }
}
