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
        if (Build.VERSION.SDK_INT < 16 || UnitValue.a(this.unitValue).getMaxLines() == -1) return;
        int n2 = UnitValue.b(this.unitValue);
        do {
            if (UnitValue.a(this.unitValue).getLineCount() <= UnitValue.a(this.unitValue).getMaxLines() || UnitValue.b(this.unitValue) < 0) {
                this.unitValue.b(n2);
                return;
            }
            this.unitValue.b(UnitValue.b(this.unitValue) - 1);
            UnitValue.a(this.unitValue).setText(this.unitValue.l());
        } while (true);
    }
}
