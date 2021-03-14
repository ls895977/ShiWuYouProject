package com.example.shiwuyouproject.net.http
import com.example.shiwuyouproject.base.BaseBean
import com.example.shiwuyouproject.net.bean.User
import com.example.shiwuyouproject.ui.bean.SmsSendBean
import com.example.shiwuyouproject.ui.bean.UserAuthBean
import com.example.shiwuyouproject.ui.my.bean.PersonalInformationBean
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
interface Api {

    companion object {
        const val BASE_URL = "http://www.icootoo.com/fastks/public/api/"
        const val Web_URL="http://www.icootoo.com/fastks/public/index/user/contract.html?token="//协议地址
        const val IMAGEHEAD="http://res.swyvip.com/"
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
    /**
     * 获取
     * 个人信息
     */
    @POST("user/get")
    suspend fun userUpdate():BaseBean<PersonalInformationBean>

    /**
     * 保存
     * 保存个人信息
     */
    @FormUrlEncoded
    @POST("user/update")
    suspend fun addUserUpdate(@Field("idfront")idfront:String,
                              @Field("idback")idback:String):BaseBean<String>

    /**
     * 获取个人认证信息
     */
    @GET("user/auth")
    suspend fun userAuth():BaseBean<UserAuthBean>
}