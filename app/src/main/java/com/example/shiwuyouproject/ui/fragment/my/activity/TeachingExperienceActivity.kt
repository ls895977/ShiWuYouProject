package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityTeachingexperienceBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.TeachingExperienceViewModel

/**
 * 教学经历
 */
class TeachingExperienceActivity:BaseVmActivity<ActivityTeachingexperienceBinding, TeachingExperienceViewModel>(){
    override fun viewModelClass(): Class<TeachingExperienceViewModel> =TeachingExperienceViewModel::class.java

    override fun getViewBinding(): ActivityTeachingexperienceBinding {
    return ActivityTeachingexperienceBinding.inflate(layoutInflater)
    }
}