package com.example.shiwuyouproject.ui

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRegisterBinding
import com.example.shiwuyouproject.ui.modelview.RegisterViewModel

/**
 * ls
 * 注册
 */
class RegisterActivity:BaseVmActivity<ActivityRegisterBinding, RegisterViewModel>() {
    override fun viewModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun getViewBinding(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }
}