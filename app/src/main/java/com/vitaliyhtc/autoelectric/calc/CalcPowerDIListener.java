package com.vitaliyhtc.autoelectric.calc;

import android.content.DialogInterface;

class CalcPowerDIListener implements DialogInterface.OnClickListener {
    final CalcPower calcPower;
    private final CharSequence[] charSequences;
    private final int anInt;

    CalcPowerDIListener(CalcPower calcPower, CharSequence[] arrcharSequence, int n2) {
        this.calcPower = calcPower;
        this.charSequences = arrcharSequence;
        this.anInt = n2;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        if (this.charSequences[n2].equals(CalcPower.b(this.calcPower).j())) {
            CalcPower.b(this.calcPower, this.anInt);
            CalcPower.c(this.calcPower);
        } else if (this.charSequences[n2].equals(CalcPower.d(this.calcPower).j())) {
            CalcPower.b(this.calcPower, this.anInt);
            CalcPower.e(this.calcPower);
        } else if (this.charSequences[n2].toString().startsWith(CalcPower.f(this.calcPower).j())) {
            CalcPower.c(this.calcPower, this.anInt);
        }
        CalcPower.a(this.calcPower);
    }
}
