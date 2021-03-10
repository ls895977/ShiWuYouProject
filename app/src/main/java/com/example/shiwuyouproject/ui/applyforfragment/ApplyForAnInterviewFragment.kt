package com.example.shiwuyouproject.ui.applyforfragment

import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentApplyforaninterviewBinding
import com.example.shiwuyouproject.ui.ApplyForPartTimeCoursesActivity
import com.example.shiwuyouproject.ui.applyforfragment.viewmdoel.ApplyForAnInterviewViewModel

/**
 * 申请面试
 */
class ApplyForAnInterviewFragment:BaseVmFragment<FragmentApplyforaninterviewBinding, ApplyForAnInterviewViewModel>(){
    override fun viewModelClass(): Class<ApplyForAnInterviewViewModel> = ApplyForAnInterviewViewModel::class.java

    override fun getViewBinding(): FragmentApplyforaninterviewBinding {
        return FragmentApplyforaninterviewBinding.inflate(layoutInflater)
    }
    var myApp: ApplyForPartTimeCoursesActivity?=null
    override fun initView() {
        myApp= activity as ApplyForPartTimeCoursesActivity?

    }

    override fun setListener() {
        mBinding.btApplyForAnInTerView.setOnClickListener {
            myApp?.setPageStatus(1)
        }

    }

}