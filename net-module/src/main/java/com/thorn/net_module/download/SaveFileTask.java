package com.thorn.net_module.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;


import com.thorn.core_module.configs.Apple;
import com.thorn.core_module.util.FileUtil;
import com.thorn.net_module.callback.IRequest;
import com.thorn.net_module.callback.ISuccess;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by pengj on 2018-7-19.
 * Github https://github.com/ThornFUN
 * Function:
 * 开启线程池，保存文件到本地
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final ISuccess SUCCESS;
    private final IRequest REQUEST;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.SUCCESS = success;
        this.REQUEST = request;
    }

    private static void autoInstallAPK(File file) {
        if (FileUtil.getExtension(file.getPath()).equals(".apk")) {
            final Intent installIntent = new Intent();
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installIntent.setAction(Intent.ACTION_VIEW);
            installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Apple.getApplicationContext().startActivity(installIntent);
        }
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody responseBody = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream inputStream = responseBody.byteStream();

        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null || name.equals("")) {
            return FileUtil.writeToDisk(inputStream, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(inputStream, downloadDir, name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
