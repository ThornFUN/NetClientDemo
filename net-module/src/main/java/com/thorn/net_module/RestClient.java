package com.thorn.net_module;

import android.content.Context;

import com.thorn.loader_module.LoaderStyle;
import com.thorn.loader_module.ProjectLoader;
import com.thorn.net_module.callback.IError;
import com.thorn.net_module.callback.IFailure;
import com.thorn.net_module.callback.IRequest;
import com.thorn.net_module.callback.ISuccess;
import com.thorn.net_module.callback.RequestCallBack;
import com.thorn.net_module.download.DownloadHandler;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by pengj on 2018-7-16.
 * Github https://github.com/ThornFUN
 * Function:
 * Http 请求的框架入口，对接各种请求方法
 */

public class RestClient {

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url, Map<String, Object> params, IRequest request, ISuccess success
            , IFailure failure, IError error, RequestBody body, LoaderStyle loaderStyle, Context context, File file
            , String downloaddir, String extentsion, String name) {
        this.URL = url;
        this.PARAMS.putAll(PARAMS);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloaddir;
        this.EXTENSION = extentsion;
        this.NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBack(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    // HttpMethod 是一个枚举类型的类，应该是 http 返回的一种标准模式
    private void request(HttpMethod httpMethod) {
        final RestService restService = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            ProjectLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (httpMethod) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
            case PUT_RAW:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            case DOWNLOAD:
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, SUCCESS, FAILURE, ERROR, DOWNLOAD_DIR, EXTENSION, NAME).handleDownload();
        request(HttpMethod.DOWNLOAD);
    }
}
