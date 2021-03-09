package com.example.shiwuyouproject.net.http.interceptor
import com.sewageproject.utils.MyConfig
import com.yechaoa.yutilskt.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by yechaoa on 2020/2/4.
 * Describe : 从响应头里拿到cookie并存起来，后面的每次请求再添加到请求头里
 */
class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val stringSet = SpUtil.getStringSet(MyConfig.COOKIE)
        for (cookie in stringSet) {
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}