package com.thorn.net_module;

import android.content.Context;
import android.util.Log;


import com.thorn.loader_module.LoaderStyle;
import com.thorn.net_module.callback.IError;
import com.thorn.net_module.callback.IFailure;
import com.thorn.net_module.callback.IRequest;
import com.thorn.net_module.callback.ISuccess;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by pengj on 2018-7-16.
 * Github https://github.com/ThornFUN
 * Function:
 * build 一个 RestClient 对象，并接收其创建所需要的参数们
 */

public class RestClientBuilder {

    private String mUrl = null;
    private WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownLoadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        // 因为 Retrofit 不允许传入一个空值，因此需要初始化
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        loader(context, LoaderStyle.BallScaleMultipleIndicator);
        return this;
    }

    public final RestClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filepath) {
        mFile = new File(filepath);
        return this;
    }


    public final RestClientBuilder downloadDir(String downloadDir) {
        mDownLoadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        mName = name;
        return this;
    }

    public final RestClient build() {
        Log.i("jin",mUrl);
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody, mLoaderStyle, mContext, mFile, mDownLoadDir, mExtension, mName);
    }

}
