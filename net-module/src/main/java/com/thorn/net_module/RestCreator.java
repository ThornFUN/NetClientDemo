package com.thorn.net_module;


import com.thorn.core_module.configs.Apple;
import com.thorn.core_module.configs.ConfigType;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by pengj on 2018-7-16.
 * Github https://github.com/ThornFUN
 * Function:
 * 创建 Retrofit 实例，并通过 OkHttp 的方式来处理各种请求
 */

public class RestCreator {

    // 将 Params 申明为全局变量，并以单例模式返回
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Apple.getConfigByKey(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
//        private static final ArrayList<Interceptor> INTERCEPTORS = Apple.getConfigByKey(ConfigType.INTERCEPTORS);
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

//        private static OkHttpClient.Builder addInterceptor() {
//            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
//                for (Interceptor interceptor : INTERCEPTORS) {
//                    BUILDER.addInterceptor(interceptor);
//                }
//            }
//            return BUILDER;
//        }
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT
                .create(RestService.class);
    }
}
