package com.vitaliyhtc.autoelectric.lib;

import android.annotation.TargetApi;
import android.os.Build;

public class UnitValueTextViewHandler implements Runnable {
    final UnitValue unitValue;

    UnitValueTextViewHandler(UnitValue unitValue2) {
        this.unitValue = unitValue2;
    }

    @TargetApi(value=16)
    @Override
    public void run() {
        if (Build.VERSION.SDK_INT < 16 || UnitValue.getTextView(this.unitValue).getMaxLines() == -1) return;
        int n2 = UnitValue.getRoundDigits(this.unitValue);
        do {
            if (UnitValue.getTextView(this.unitValue).getLineCount() <= UnitValue.getTextView(this.unitValue).getMaxLines() || UnitValue.getRoundDigits(this.unitValue) < 0) {
                this.unitValue.setRoundDigits(n2);
                return;
            }
            this.unitValue.setRoundDigits(UnitValue.getRoundDigits(this.unitValue) - 1);
            UnitValue.getTextView(this.unitValue).setText(this.unitValue.getTextViewString());
        } while (true);
    }
}
