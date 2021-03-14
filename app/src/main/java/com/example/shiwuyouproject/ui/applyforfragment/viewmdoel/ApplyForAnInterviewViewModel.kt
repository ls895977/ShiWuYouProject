package com.example.shiwuyouproject.ui.applyforfragment.viewmdoel
import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient
import com.example.shiwuyouproject.ui.bean.UserAuthBean
import com.example.shiwuyouproject.ui.my.bean.PersonalInformationBean

class ApplyForAnInterviewViewModel:BaseViewModel() {
    private val applyForDataApi by lazy { RetrofitClient.getApiService() }
    val applyForDataState = MutableLiveData<UserAuthBean>()
    /**
     * 状态获取
     */
    fun getStatusAll(){
        launch(
            block = {
                val codeStatus = applyForDataApi.userAuth()
                applyForDataState.value=codeStatus.result()
            },
            error = {
            },
            cancel = {

            },
            showErrorToast = false
        )

    }

    val getGenRenStatus = MutableLiveData<PersonalInformationBean>()
    val getPostStatus = MutableLiveData<Boolean>()
    fun getRealNameAuthenticationData(){
        launch(
            block = {
                val codeStatus = applyForDataApi.userUpdate()
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
                val codeStatus = applyForDataApi.addUserUpdate(idFront,idBack)
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