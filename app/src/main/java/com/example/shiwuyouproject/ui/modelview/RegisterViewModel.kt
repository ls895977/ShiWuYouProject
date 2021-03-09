package com.example.shiwuyouproject.ui.modelview
import androidx.lifecycle.MutableLiveData
import com.example.shiwuyouproject.base.BaseViewModel
import com.example.shiwuyouproject.net.http.RetrofitClient
/**
 * ls
 */
class RegisterViewModel:BaseViewModel() {
    private val loginRepository by lazy { RetrofitClient.getApiService() }
     val myCodeState = MutableLiveData<Boolean>()
    /**
     * 获取验证码
     */
     fun smsSend(mobile:String){
        launch(
            block = {
                val codeStatus = loginRepository.smsSend(mobile,"register")
                myCodeState.value=codeStatus.success()
            },
            error = {
                myCodeState.value = false
            },
            cancel = {

            },
            showErrorToast = false
        )
     }
}