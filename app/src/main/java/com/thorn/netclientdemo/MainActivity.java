package com.thorn.netclientdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thorn.net_module.RestClient;
import com.thorn.net_module.callback.IError;
import com.thorn.net_module.callback.IFailure;
import com.thorn.net_module.callback.ISuccess;
import com.thorn.netclientdemo.constant.UrlConstants;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btn_loadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadData = findViewById(R.id.btn_loadData);

        btn_loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RestClient
                        .builder()
                        .url(UrlConstants.getIndexDataUrl())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.i("jin", "11111111111");
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(Throwable t) {
                                Log.i("jin", "22222222222");
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                Log.i("jin", "333333333" + msg);
                            }
                        })
                        .build()
                        .get();
            }
        });
    }
}
