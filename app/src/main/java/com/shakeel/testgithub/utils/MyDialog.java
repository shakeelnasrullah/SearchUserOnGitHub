package com.shakeel.testgithub.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shakeel.testgithub.R;

public class MyDialog {

    public static void showDialog(final Context mContext, String title, String msg, DialogInterface.OnDismissListener dismissListener) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        dialog.setTitle("Error");
        TextView titelText = (TextView) dialog.findViewById(R.id.title_txt);
        TextView msgText = (TextView) dialog.findViewById(R.id.msg_txt);
        Button okBtn = (Button) dialog.findViewById(R.id.ok_btn);
        titelText.setText(title);
        msgText.setText(msg);
        dialog.setCancelable(false);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if(dismissListener!= null){
            dialog.setOnDismissListener(dismissListener);
        }
        dialog.show();

    }

    public static void showLogInDialog(Context mContext) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.login_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        TextView titelText = (TextView) dialog.findViewById(R.id.title_txt);
        EditText userNameEt =  dialog.findViewById(R.id.userNameET);
        EditText password = dialog.findViewById(R.id.passwordEt);
        Button okBtn =  dialog.findViewById(R.id.ok_btn);
        dialog.setCancelable(false);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
