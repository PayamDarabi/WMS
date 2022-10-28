package com.wms.wms.data.api

import com.wms.wms.data.UserManager
import com.wms.wms.data.helper.PreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    fun getInstance(): IApi {
        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        var mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor { chain ->
                var request = chain.request().newBuilder()

                UserManager.get()?.let {
                    request = request.addHeader("Cookie", it.cookie)
                }
                val response = chain.proceed(request.build())

                if (response.code == 401 && !UserManager.get()?.accessToken.isNullOrEmpty()) {
                    UserManager.logout()
                }

                response
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(PreferenceHelper.getString("BaseUrl"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()


        return retrofit.create(IApi::class.java)
    }





}