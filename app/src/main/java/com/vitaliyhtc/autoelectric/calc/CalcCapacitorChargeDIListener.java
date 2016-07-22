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
        if (this.charSequences[n2].equals(CalcCapacitorCharge.a(this.calcCapacitorCharge).getUnitName())) {
            CalcCapacitorCharge.a(this.calcCapacitorCharge).validateUnitValueDouble(CalcCapacitorCharge.b(this.calcCapacitorCharge).getUnitValue() / CalcCapacitorCharge.c(this.calcCapacitorCharge).getUnitValue());
        } else if (this.charSequences[n2].equals(CalcCapacitorCharge.c(this.calcCapacitorCharge).getUnitName())) {
            CalcCapacitorCharge.c(this.calcCapacitorCharge).validateUnitValueDouble(CalcCapacitorCharge.b(this.calcCapacitorCharge).getUnitValue() / CalcCapacitorCharge.a(this.calcCapacitorCharge).getUnitValue());
        }
        CalcCapacitorCharge.d(this.calcCapacitorCharge);
    }
}
