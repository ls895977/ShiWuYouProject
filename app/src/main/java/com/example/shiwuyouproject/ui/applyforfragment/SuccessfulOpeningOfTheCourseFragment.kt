package com.example.shiwuyouproject.ui.applyforfragment
import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentSuccessfulopeningofthecourseBinding
import com.example.shiwuyouproject.ui.MainActivity
import com.example.shiwuyouproject.ui.applyforfragment.viewmdoel.SuccessfulOpeningOfTheCourseViewModel
import com.example.shiwuyouproject.utils.ActStartUtils
/**
 * 开课成功
 */
class SuccessfulOpeningOfTheCourseFragment:BaseVmFragment<FragmentSuccessfulopeningofthecourseBinding, SuccessfulOpeningOfTheCourseViewModel>(){
    override fun viewModelClass(): Class<SuccessfulOpeningOfTheCourseViewModel> = SuccessfulOpeningOfTheCourseViewModel::class.java
    override fun getViewBinding(): FragmentSuccessfulopeningofthecourseBinding {
        return FragmentSuccessfulopeningofthecourseBinding.inflate(layoutInflater)
    }

    override fun initView() {


    }

    override fun setListener() {
        mBinding.btSuccessfulOpeningOfTheCourse.setOnClickListener {
            ActStartUtils.startAct(context, MainActivity::class.java)
            activity?.finish()
        }
    }
}


