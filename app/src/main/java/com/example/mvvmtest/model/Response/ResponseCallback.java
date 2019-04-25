package com.example.mvvmtest.model.Response;

import android.util.Log;

import com.example.mvvmtest.interfaces.HttpErrorHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallback<T> implements Callback<T> {

    private static final int RESPONSE_CODE_FAIL_CALL = 10000;
    private HttpErrorHandler httpErrorHandler;

    public ResponseCallback(HttpErrorHandler httpErrorHandler) {
        this.httpErrorHandler = httpErrorHandler;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFail(response.code());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("GBC", String.format("Request %s failed: ", call.request().url()), t);
        onFail(RESPONSE_CODE_FAIL_CALL);
    }

    protected abstract void onSuccess(T entity);

    protected void onFail(int responseCode) {
        if (httpErrorHandler != null) {
            httpErrorHandler.handleHttpError(responseCode);
        }
    }
}
