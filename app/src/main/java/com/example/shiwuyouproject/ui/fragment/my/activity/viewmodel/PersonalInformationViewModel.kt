package com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel
import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient
import com.example.shiwuyouproject.ui.my.bean.PersonalInformationBean

class PersonalInformationViewModel:BaseViewModel() {
    private val getRealNameAuthenticatioApi by lazy { RetrofitClient.getApiService() }
    val getPostStatus = MutableLiveData<Boolean>()
    /**
     * 保存头像
     */
    fun preservationCard(avatar:String){
        launch(
                block = {
                    val codeStatus = getRealNameAuthenticatioApi.addUserUpdate(avatar)
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
}