package com.vitaliyhtc.autoelectric.lib;

import android.view.KeyEvent;
import android.widget.TextView;

class SetValueDialog_editResValueListener implements TextView.OnEditorActionListener {
    final SetValueDialog setValueDialog;

    SetValueDialog_editResValueListener(SetValueDialog setValueDialog) {
        this.setValueDialog = setValueDialog;
    }

    public boolean onEditorAction(TextView textView, int n2, KeyEvent keyEvent) {
        if (n2 == 6) {
            this.setValueDialog.a();
            return true;
        }
        return false;
    }
}
