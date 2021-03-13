package com.example.shiwuyouproject.net.http

import android.util.Log
import com.example.shiwuyouproject.net.http.interceptor.AddCookiesInterceptor
import com.example.shiwuyouproject.net.http.interceptor.ReceivedCookiesInterceptor
import com.example.shiwuyouproject.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yechaoa on 2021/2/4.
 * Describe :
 */
object RetrofitClient {

    private const val CALL_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 20L
    private const val IO_TIMEOUT = 20L

    private var apiService: Api

    fun getApiService(): Api {
        return apiService
    }

    init {
        val mTokenInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val authorised: Request?
//            if(SPUtils.getInstance().myUserInfo!=null) {
                authorised = originalRequest.newBuilder()
//                    .header("X-Access-Token", SPUtils.getInstance().myUserInfo.token)
                    .header("token","234f184d-42df-456c-8f03-b682f1230084")
                    .build()
                chain.proceed(authorised)
//            }else{
//                chain.proceed(originalRequest);
//            }
        }
        val loggingInterceptor = HttpLoggingInterceptor { Log.e("httpLog", it) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        /**OkHttpClient*/
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mTokenInterceptor)
            .retryOnConnectionFailure(true)
            .build()

        /**Retrofit*/
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /**ApiService*/
        apiService = retrofit.create(Api::class.java)
    }

}