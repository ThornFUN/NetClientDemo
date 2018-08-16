package com.thorn.net_module.interceptor.cookie;

import android.annotation.SuppressLint;

import com.thorn.core_module.util.ProjectSharedPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pengj on 2018-8-1.
 * Github https://github.com/ThornFUN
 * Function:
 */

public class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder BUILDER = chain.request().newBuilder();
        Observable
                .just(ProjectSharedPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String coookie) throws Exception {
                        //  给原生请求附带上 WebView 拦截下来的 cookie
                        BUILDER.addHeader("cookie", coookie);
                    }
                });
        return chain.proceed(BUILDER.build());
    }
}
