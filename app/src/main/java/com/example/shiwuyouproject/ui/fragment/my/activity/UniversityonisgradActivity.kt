package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityUniversityonisgradBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.UniversityonisgradViewModel

/**
 * 毕业院校
 */
class UniversityonisgradActivity :BaseVmActivity<ActivityUniversityonisgradBinding, UniversityonisgradViewModel>(){
    override fun viewModelClass(): Class<UniversityonisgradViewModel> = UniversityonisgradViewModel::class.java

    override fun getViewBinding(): ActivityUniversityonisgradBinding {
        return ActivityUniversityonisgradBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
    }
}