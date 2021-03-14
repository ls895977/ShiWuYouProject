package com.example.shiwuyouproject.ui.my.viewmodel
import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient
import com.example.shiwuyouproject.ui.my.bean.PersonalInformationBean

class RealNameAuthenticationViewModel:BaseViewModel(){
    private val getRealNameAuthenticatioApi by lazy { RetrofitClient.getApiService() }
    val getGenRenStatus = MutableLiveData<PersonalInformationBean>()
    val getPostStatus = MutableLiveData<Boolean>()
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

    /**
     * 保存身份正反面
     */
    fun preservationCard(idFront:String,idBack:String){
        launch(
            block = {
                val codeStatus = getRealNameAuthenticatioApi.addUserUpdate(idFront,idBack)
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