package com.example.shiwuyouproject.ui.fragment.my.activity
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivitySuccessfulcaseBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.SuccessfulCaseViewModel
/**
 * 成功案例
 */
class SuccessfulCaseActivity:BaseVmActivity<ActivitySuccessfulcaseBinding, SuccessfulCaseViewModel>() {
    override fun viewModelClass(): Class<SuccessfulCaseViewModel> = SuccessfulCaseViewModel::class.java

    override fun getViewBinding(): ActivitySuccessfulcaseBinding {
      return  ActivitySuccessfulcaseBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
    }
}