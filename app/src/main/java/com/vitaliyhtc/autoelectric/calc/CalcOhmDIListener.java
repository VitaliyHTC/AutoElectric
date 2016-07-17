package com.vitaliyhtc.autoelectric.calc;

import android.content.DialogInterface;

class CalcOhmDIListener implements DialogInterface.OnClickListener {
    final CalcOhm calcOhm;
    private final CharSequence[] charSequences;

    CalcOhmDIListener(CalcOhm calcOhm, CharSequence[] arrcharSequence) {
        this.calcOhm = calcOhm;
        this.charSequences = arrcharSequence;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        CalcOhm.a(this.calcOhm, (String)this.charSequences[n2]);
    }
}
