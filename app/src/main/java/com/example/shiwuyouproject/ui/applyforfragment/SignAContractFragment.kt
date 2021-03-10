package com.example.shiwuyouproject.ui.applyforfragment

import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentSignacontractBinding
import com.example.shiwuyouproject.ui.ApplyForPartTimeCoursesActivity
import com.example.shiwuyouproject.ui.applyforfragment.viewmdoel.SignAContractViewModel

/**
 * 签订合同
 */
class SignAContractFragment:BaseVmFragment<FragmentSignacontractBinding, SignAContractViewModel>() {
    override fun viewModelClass(): Class<SignAContractViewModel> = SignAContractViewModel::class.java
    override fun getViewBinding(): FragmentSignacontractBinding {
     return FragmentSignacontractBinding.inflate(layoutInflater)
    }
    var myApp: ApplyForPartTimeCoursesActivity?=null
    override fun initView() {
        myApp= activity as ApplyForPartTimeCoursesActivity?
    }

    override fun setListener() {
        mBinding.btSignAContract.setOnClickListener {
        }
    }

}