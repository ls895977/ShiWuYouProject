package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityTeacherinformationBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.TeacherInformationViewModel
import com.example.shiwuyouproject.utils.ActStartUtils

/**
 * 个人资料
 */
class TeacherInformationActivity:BaseVmActivity<ActivityTeacherinformationBinding, TeacherInformationViewModel>(){
    override fun viewModelClass(): Class<TeacherInformationViewModel> = TeacherInformationViewModel::class.java

    override fun getViewBinding(): ActivityTeacherinformationBinding {
        return ActivityTeacherinformationBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
        mBinding.btUniversityonisgrad.setOnClickListener { //毕业院校
            ActStartUtils.startAct(this, UniversityonisgradActivity::class.java)
        }
        mBinding.tvTitle.setOnClickListener { //职称
            ActStartUtils.startAct(this, TitleActivity::class.java)
        }
        mBinding.btRewardsAndHonors.setOnClickListener { //奖励荣誉
            ActStartUtils.startAct(this, RewardsAndHonorsActivity::class.java)
        }
        mBinding.btTeachingExperience.setOnClickListener { //教学经历
            ActStartUtils.startAct(this, TeachingExperienceActivity::class.java)

        }

        mBinding.btSuccessfulCase.setOnClickListener { //成功案例
            ActStartUtils.startAct(this, SuccessfulCaseActivity::class.java)

        }

    }
}