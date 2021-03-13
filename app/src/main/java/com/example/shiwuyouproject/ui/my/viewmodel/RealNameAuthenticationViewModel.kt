package com.example.shiwuyouproject.ui.my.viewmodel

import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRealnameauthenticationBinding
import com.example.shiwuyouproject.net.http.RetrofitClient

class RealNameAuthenticationViewModel:BaseViewModel(){
    private val getRealNameAuthenticatioApi by lazy { RetrofitClient.getApiService() }

    fun getRealNameAuthenticationData(){
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.userUpdate()
            },
            error = {
            },
            cancel = {

            },
            showErrorToast = false
        )
    }

    /**
     * 保存身份正反面
     */
    fun preservationCard(idFront:String,idBack:String){
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.addUserUpdate(idFront,idBack)
            },
            error = {
            },
            cancel = {

            },
            showErrorToast = false
        )

    }
}