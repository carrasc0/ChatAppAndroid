package com.example.mvvmtest.dagger.module;

import com.example.mvvmtest.network.RetrofitCall;
import com.example.mvvmtest.network.RetrofitInterface;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import android.app.Application;
import com.example.mvvmtest.util.Constant;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URISyntaxException;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String apiUrl, chatUrl;

    public NetworkModule(String apiUrl, String chatUrl) {
        this.apiUrl = apiUrl;
        this.chatUrl = chatUrl;
    }

    @Provides
    @Singleton
    Socket provideSocket() {
        try {
            IO.Options mOptions = new IO.Options();
            mOptions.forceNew = true;
            mOptions.query = "userID=" + Constant.SENDER;
            return IO.socket(chatUrl, mOptions);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    RetrofitCall provideRetrofitCall(RetrofitInterface retrofitInterface) {
        return new RetrofitCall(retrofitInterface);
    }

    @Provides
    @Singleton
    RetrofitInterface provideRetrofitInterface(Retrofit retrofit) {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        return retrofitInterface;
    }


}
