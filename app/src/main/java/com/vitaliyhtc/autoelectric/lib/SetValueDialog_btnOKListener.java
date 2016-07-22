package com.vitaliyhtc.autoelectric.lib;

import android.view.View;

class SetValueDialog_btnOKListener implements View.OnClickListener {
    final SetValueDialog setValueDialog;

    SetValueDialog_btnOKListener(SetValueDialog setValueDialog) {
        this.setValueDialog = setValueDialog;
    }

    public void onClick(View view) {
        this.setValueDialog.actionSetResult();
    }
}
