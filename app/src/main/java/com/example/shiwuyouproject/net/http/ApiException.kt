package com.sewageproject.net.http

/**
 * 自定义异常
 */
class ApiException(var code: Int, override var message: String) : RuntimeException()