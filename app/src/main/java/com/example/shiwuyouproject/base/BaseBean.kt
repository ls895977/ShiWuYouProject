package com.example.shiwuyouproject.base

import com.sewageproject.net.http.ApiException

/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
class BaseBean<T>(private val code: Int, private val result: T, private val message: String?,private val success:Boolean) {
     fun   success():Boolean{
         return success
     }
    fun code(): Int {
        if (code == 200) {
            return code
        } else {
            throw ApiException(code, message ?: "")
        }
    }

    fun result(): T {
        if (code == 200) {
            return result
        } else {
            throw ApiException(code, message ?: "")
        }
    }

}