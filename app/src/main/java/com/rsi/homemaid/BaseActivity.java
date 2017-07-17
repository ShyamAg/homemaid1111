package com.rsi.homemaid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit.ApiClient;
import retrofit.ApiInterface;

/**
 * Created by deepak.sharma on 7/12/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected ApiInterface apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiClient.getClient().create(ApiInterface.class);

    }
}
