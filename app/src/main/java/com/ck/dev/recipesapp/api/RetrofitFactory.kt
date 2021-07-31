package com.ck.dev.recipesapp.api

import com.ck.dev.recipesapp.utils.Constants.API_KEY
import com.ck.dev.recipesapp.utils.Constants.AUTHORIZATION
import com.ck.dev.recipesapp.utils.Constants.CONNECT_TIMEOUT
import com.ck.dev.recipesapp.utils.Constants.CONTENT_TYPE
import com.ck.dev.recipesapp.utils.Constants.CONTENT_TYPE_VALUE
import com.ck.dev.recipesapp.utils.Constants.HOST
import com.ck.dev.recipesapp.utils.Constants.HOST_VALUE
import com.ck.dev.recipesapp.utils.Constants.READ_TIMEOUT
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitFactory @Inject constructor() {

    fun getRecepieService(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RecipeService::class.java)


    private fun provideOkHttpClient(): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(provideLoggingInterceptor())
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        return b.build()
    }


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun getHeaderInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .header(AUTHORIZATION, API_KEY)
            .header(HOST, HOST_VALUE)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }
}