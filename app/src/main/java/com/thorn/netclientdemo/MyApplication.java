package com.thorn.netclientdemo;

import android.app.Application;

import com.thorn.core_module.configs.Apple;
import com.thorn.netclientdemo.constant.UrlConstants;

/**
 * Created by pengj on 2018-8-16.
 * Github https://github.com/ThornFUN
 * Function:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Apple.init(this.getApplicationContext())
                .withApiHost(UrlConstants.getBaseUrl())
                .configure();
    }
}
