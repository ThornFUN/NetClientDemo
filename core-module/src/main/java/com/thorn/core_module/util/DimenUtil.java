package com.thorn.core_module.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;


/**
 * Created by pengj on 2018-7-19.
 * Github https://github.com/ThornFUN
 * Function:
 * 获取屏幕宽高的工具类
 */

public class DimenUtil {

    public static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
