package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityTitleBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.TitleViewModel
/**
 * 职称
 */
class TitleActivity :BaseVmActivity<ActivityTitleBinding, TitleViewModel>(){
    override fun viewModelClass(): Class<TitleViewModel> = TitleViewModel::class.java

    override fun getViewBinding(): ActivityTitleBinding {
return ActivityTitleBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
    }

}