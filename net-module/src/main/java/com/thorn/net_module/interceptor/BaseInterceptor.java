package com.thorn.net_module.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pengj on 2018-7-25.
 * Github https://github.com/ThornFUN
 * Function:
 * 拦截器抽象基类
 */

public abstract class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    protected LinkedHashMap<String, String> getUrlParamters(Chain chain) {
        final HttpUrl URL = chain.request().url();

        int size = URL.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            params.put(URL.queryParameterName(i), URL.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParamters(Chain chain, String key) {
        final Request REQUEST = chain.request();
        return REQUEST.url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
