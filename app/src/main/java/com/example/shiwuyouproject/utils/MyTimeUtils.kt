package com.example.shiwuyouproject.utils

import java.text.SimpleDateFormat
import java.util.*

object  MyTimeUtils {

    /**
     * 获取当前年月
     */
    fun getYerMoth():String{
        val format = SimpleDateFormat("yyyy-MM")
        return format.format(Date()).toString()
    }
    fun getYerMoth1():String{
        val format = SimpleDateFormat("yyyy年MM月")
        return format.format(Date()).toString()
    }
    //年月日时分秒格式
     fun getTimes(date: Date): String? {
        val format = SimpleDateFormat("yyyy-MM")
        return format.format(date)
    }
    //年月
    fun getTimesYe(date: Date): String? {
        val format = SimpleDateFormat("yyyy年MM月")
        return format.format(date)
    }
}