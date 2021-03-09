package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRegisterBinding
import com.example.shiwuyouproject.databinding.ActivityRewardsandhonorsBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.RewardsAndHonorsViewModel

/**
 * 奖励荣誉
 */
class RewardsAndHonorsActivity :BaseVmActivity<ActivityRewardsandhonorsBinding, RewardsAndHonorsViewModel>(){
    override fun viewModelClass(): Class<RewardsAndHonorsViewModel> = RewardsAndHonorsViewModel::class.java

    override fun getViewBinding(): ActivityRewardsandhonorsBinding {
     return ActivityRewardsandhonorsBinding.inflate(layoutInflater)
    }


}