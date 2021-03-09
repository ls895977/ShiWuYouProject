package com.example.shiwuyouproject.base

import com.sewageproject.net.http.ApiException

/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
class BaseBean<T>(private val code: Int, private val data: T, private val msg: String?,private val success:Boolean) {
     fun   success():Boolean{
         return success
     }
    fun code(): Int {
        if (code == 1) {
            return code
        } else {
            throw ApiException(code, msg ?: "")
        }
    }

    fun result(): T {
        if (code == 1) {
            return data
        } else {
            throw ApiException(code, msg ?: "")
        }
    }

}