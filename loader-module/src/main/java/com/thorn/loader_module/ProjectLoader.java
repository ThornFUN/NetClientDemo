package com.thorn.loader_module;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.thorn.core_module.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by pengj on 2018-7-19.
 * Github https://github.com/ThornFUN
 * Function:
 * 自定义一个加载对话框
 * Others:
 * 两个加载方法都需要传入 Context ，因为如果传入 ApplicationContext 的话，在 WebView 或者其他 View 上面显示的话，很容易报错！！
 * 因此最好传入当前 Activity 或者 Fragment 的 Context
 */

public class ProjectLoader {

    private static final int LOADER_SIZE_SCALE = 8;//缩放八倍
    private static final int LOADER_OFFSET_SCALE = 10;//高度上的偏移量为 10

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallScaleMultipleIndicator.name();

    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth(context);
        int deviceHeight = DimenUtil.getScreenHeight(context);

        final Window dialogWindow = dialog.getWindow();

        if (dialog != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth / LOADER_SIZE_SCALE;
            layoutParams.height = deviceWidth / LOADER_SIZE_SCALE;

            layoutParams.height = layoutParams.height + deviceHeight / LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    // 加载默认对话框的方法
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    // 加载对应枚举类型对话框的方法
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();// 不仅让对话框不可见，还要执行一些回调
                    dialog.dismiss();//仅仅让对话框不可见，不会有其他操作
                }
            }
        }
    }
}
