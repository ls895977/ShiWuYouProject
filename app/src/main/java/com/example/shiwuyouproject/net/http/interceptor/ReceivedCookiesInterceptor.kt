package com.example.shiwuyouproject.net.http.interceptor
import com.sewageproject.utils.MyConfig
import com.yechaoa.yutilskt.SpUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by yechaoa on 2020/2/4.
 * Describe : 从响应头里拿到cookie并存起来，后面的每次请求再添加到请求头里
 */
class ReceivedCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            SpUtil.setStringSet(MyConfig.COOKIE, cookies)
        }
        return originalResponse
    }
}