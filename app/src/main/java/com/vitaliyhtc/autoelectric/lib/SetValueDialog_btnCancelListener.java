package com.vitaliyhtc.autoelectric.lib;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

class SetValueDialog_btnCancelListener implements View.OnClickListener {
    final SetValueDialog setValueDialog;

    SetValueDialog_btnCancelListener(SetValueDialog setValueDialog) {
        this.setValueDialog = setValueDialog;
    }

    public void onClick(View view) {
        this.setValueDialog.setResult(0);
        ((InputMethodManager)this.setValueDialog.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SetValueDialog.b(this.setValueDialog).getWindowToken(), 0);
        this.setValueDialog.finish();
    }
}
