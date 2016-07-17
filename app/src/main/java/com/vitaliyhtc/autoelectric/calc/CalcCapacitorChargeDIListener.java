package com.vitaliyhtc.autoelectric.calc;

import android.content.DialogInterface;

class CalcCapacitorChargeDIListener implements DialogInterface.OnClickListener {
    final CalcCapacitorCharge calcCapacitorCharge;
    private final CharSequence[] charSequences;

    CalcCapacitorChargeDIListener(CalcCapacitorCharge calcCapacitorCharge, CharSequence[] arrcharSequence) {
        this.calcCapacitorCharge = calcCapacitorCharge;
        this.charSequences = arrcharSequence;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        if (this.charSequences[n2].equals(CalcCapacitorCharge.a(this.calcCapacitorCharge).j())) {
            CalcCapacitorCharge.a(this.calcCapacitorCharge).a(CalcCapacitorCharge.b(this.calcCapacitorCharge).h() / CalcCapacitorCharge.c(this.calcCapacitorCharge).h());
        } else if (this.charSequences[n2].equals(CalcCapacitorCharge.c(this.calcCapacitorCharge).j())) {
            CalcCapacitorCharge.c(this.calcCapacitorCharge).a(CalcCapacitorCharge.b(this.calcCapacitorCharge).h() / CalcCapacitorCharge.a(this.calcCapacitorCharge).h());
        }
        CalcCapacitorCharge.d(this.calcCapacitorCharge);
    }
}
