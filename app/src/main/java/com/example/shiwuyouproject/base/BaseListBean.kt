package com.example.shiwuyouproject.base
/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
class BaseListBean<T>(
    private val current: Int,
    private val records: T,
    private val orders: String?,
    private val pages:Int,
    private val size:Int,
    private val total:Int)