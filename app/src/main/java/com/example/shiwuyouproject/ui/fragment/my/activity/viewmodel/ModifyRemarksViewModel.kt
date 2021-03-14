package com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient

class ModifyRemarksViewModel:BaseViewModel() {
    private val getRealNameAuthenticatioApi by lazy { RetrofitClient.getApiService() }
    val getPostStatus = MutableLiveData<Boolean>()
    /**
     * 保存昵称
     */
    fun preservationNickName(nickname:String){
      val hashMap:MutableMap<String, String> =HashMap()
        hashMap["nickname"] = nickname
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.addUserUpdate(hashMap)
                getPostStatus.value=codeStatus.success()
            },
            error = {
                getPostStatus.value=false
            },
            cancel = {
                getPostStatus.value=false
            },
            showErrorToast = false
        )
    }
    /**
     * 保存真实姓名
     */
    fun preservationRealnRame(realname:String){
        val hashMap:MutableMap<String, String> =HashMap()
        hashMap["realname"] = realname
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.addUserUpdate(hashMap)
                getPostStatus.value=codeStatus.success()
            },
            error = {
                getPostStatus.value=false
            },
            cancel = {
                getPostStatus.value=false
            },
            showErrorToast = false
        )
    }

}