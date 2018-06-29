package com.shakeel.testgithub.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Map;

public class Utility{

    public static final String OBJECT_EXTRAS = "com.test.object";

    public static void launchActivity(Context mContext, Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(mContext, mClass);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isEmpty(CharSequence target) {
        return (!TextUtils.isEmpty(target));
    }

    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Map<String, String> getUserHeader() {

        Map<String, String> headerMap = new ArrayMap<>();
        headerMap.put("Accept", "application/vnd.github.v3+json");
        //headerMap.put("Authorization", "Basic fdab43196440162b6e2dd97a00f7c5ddeebc016f");
        return headerMap;
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
