package com.example.shiwuyouproject.ui

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityLoginBinding
import com.example.shiwuyouproject.ui.modelview.LoginModelView
import com.example.shiwuyouproject.utils.ActStartUtils

/**
 * ls
 * 登录
 */
class LoginActivity :BaseVmActivity<ActivityLoginBinding, LoginModelView>(){
    /**
     * 获取ViewModel的class
     */
    override fun viewModelClass(): Class<LoginModelView> = LoginModelView::class.java

    override fun getViewBinding(): ActivityLoginBinding {return ActivityLoginBinding.inflate(layoutInflater) }


    override fun setListener() {
        mBinding.btLogin.setOnClickListener {
            ActStartUtils.startAct(this, MainActivity::class.java)
            finish()
        }
        mBinding.btRegister.setOnClickListener {
            ActStartUtils.startAct(this,RegisterActivity::class.java)
        }
    }
}