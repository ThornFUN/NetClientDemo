package com.thorn.net_module.callback;

import android.os.Handler;


import com.thorn.loader_module.LoaderStyle;
import com.thorn.loader_module.ProjectLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pengj on 2018-7-17.
 * Github https://github.com/ThornFUN
 * Function:
 * Request 回调接口，用于处理加载对话框的显示与隐藏
 */

public class RequestCallBack implements Callback<String> {

    private static final Handler MYHANDLER = new Handler();// 用 static 修饰避免内存泄漏
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallBack(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    private static void stopLoading() {
        MYHANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                ProjectLoader.stopLoading();
            }
        }, 0);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure(t);
        }
        stopLoading();
    }
}
