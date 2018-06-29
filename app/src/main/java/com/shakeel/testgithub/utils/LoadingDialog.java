package com.shakeel.testgithub.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog extends ProgressDialog {
    private Activity mContext;

    public LoadingDialog(Context context) {
        super(context);
        mContext = (Activity) context;
        init();
    }

    private void init() {
        setMessage("Loading...");
        setCancelable(false);
    }

    private void setMessage(String message) {
        super.setMessage(message);
    }
}

