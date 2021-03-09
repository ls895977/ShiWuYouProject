package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivitySettingBinding
import com.example.shiwuyouproject.ui.fragment.my.viewmodel.SettingViewModel

class SettingActivity:BaseVmActivity<ActivitySettingBinding, SettingViewModel>() {
    override fun viewModelClass(): Class<SettingViewModel> = SettingViewModel::class.java

    override fun getViewBinding(): ActivitySettingBinding {
return ActivitySettingBinding.inflate(layoutInflater)
    }


}