package com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient
import com.example.shiwuyouproject.ui.my.bean.PersonalInformationBean

class TitleViewModel:BaseViewModel() {

    private val getRealNameAuthenticatioApi by lazy { RetrofitClient.getApiService() }
    val getGenRenStatus = MutableLiveData<PersonalInformationBean>()
    fun getRealNameAuthenticationData(){
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.userUpdate()
                getGenRenStatus.value=codeStatus.result()
            },
            error = {
            },
            cancel = {

            },
            showErrorToast = false
        )
    }

    val getPostStatus = MutableLiveData<Boolean>()
    /**
     * 保存毕业院校信息
     *
     */
    fun preservationCard(zcname:String
                         ,zcimg:String){
        val hashMap:MutableMap<String, String> = HashMap()
        hashMap["zcname"] = zcname
        hashMap["zcimg"] = zcimg
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