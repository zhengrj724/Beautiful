package com.beautiful.beautiful.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by krkj on 2016/10/24.
 * 土司窗工具
 */

public class ToastUtil {
    private static Toast mToast;

    public static void Toast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context,text,duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void Toast(Context context, int resId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context,resId,duration);
        } else {
            mToast.setText(resId);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void LongToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context,resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void LongToast(Context context, CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(context,text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void ShortToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context,resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void ShortToast(Context context, CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
