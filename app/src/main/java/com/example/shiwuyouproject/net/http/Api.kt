package com.example.shiwuyouproject.net.http
import com.example.shiwuyouproject.base.BaseBean
import com.example.shiwuyouproject.net.bean.User
import com.example.shiwuyouproject.ui.bean.SmsSendBean
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
interface Api {

    companion object {
        const val BASE_URL = "http://www.icootoo.com/fastks/public/api/"
        const val Web_URL="http://www.icootoo.com/fastks/public/index/user/contract.html?token="//协议地址
    }
    //登录
    @POST("sms/send")
    suspend fun login(@Body requestBody: RequestBody): BaseBean<User>
    /**
     * 获取验证码
     * mobile手机号
     * event： register,resetpwd
     */
    @POST("sms/send")
    suspend fun smsSend(@Query("mobile")mobile:String,
                        @Query("event")event:String):
            BaseBean<SmsSendBean>
}