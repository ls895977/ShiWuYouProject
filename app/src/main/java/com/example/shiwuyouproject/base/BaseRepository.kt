package com.example.shiwuyouproject.base

import com.example.shiwuyouproject.net.http.Api
import com.example.shiwuyouproject.net.http.RetrofitClient

/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
open class BaseRepository {

    protected fun apiService(): Api {
        return RetrofitClient.getApiService()
    }

}