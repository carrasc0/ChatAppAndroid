package com.example.mvvmtest.dagger.module

import android.app.Application
import com.example.mvvmtest.network.ChatCall
import com.example.mvvmtest.network.ChatInterface
import com.example.mvvmtest.network.RetrofitCall
import com.example.mvvmtest.network.RetrofitInterface
import com.example.mvvmtest.util.Constant
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import java.net.URISyntaxException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val chatUrl: String, private val apiUrl: String) {

    @Provides
    @Singleton
    internal fun provideSocket(): Socket {
        try {
            val mOptions = IO.Options()
            mOptions.forceNew = true
            mOptions.query = "userID=" + Constant.SENDER
            return IO.socket(chatUrl, mOptions)
            //return IO.socket(chatUrl)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

    }

    @Provides
    @Singleton
    internal fun provideChatInterface(retrofit: Retrofit): ChatInterface {
        return retrofit.create(ChatInterface::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(retrofit: Retrofit): RetrofitInterface {
        return retrofit.create(RetrofitInterface::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofitCall(retrofitInterface: RetrofitInterface): RetrofitCall {
        return RetrofitCall(retrofitInterface)
    }

    @Provides
    @Singleton
    internal fun provideChatCall(chatInterface: ChatInterface): ChatCall {
        return ChatCall(chatInterface)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }


    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        builder.cache(cache)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

}
