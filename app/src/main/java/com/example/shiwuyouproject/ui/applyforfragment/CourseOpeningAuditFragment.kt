package com.example.shiwuyouproject.ui.applyforfragment
import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentCourseopeningauditBinding
import com.example.shiwuyouproject.ui.applyforfragment.viewmdoel.CourseOpeningAuditViewModel

class CourseOpeningAuditFragment:BaseVmFragment<FragmentCourseopeningauditBinding,CourseOpeningAuditViewModel>() {
    override fun viewModelClass(): Class<CourseOpeningAuditViewModel> = CourseOpeningAuditViewModel::class.java
    override fun getViewBinding(): FragmentCourseopeningauditBinding {
        return FragmentCourseopeningauditBinding.inflate(layoutInflater)
    }
    override fun initView() {
    }
}