package com.example.shiwuyouproject.ui.fragment

import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentCurriculumBinding
import com.example.shiwuyouproject.ui.modelview.CurriculumViewModel

class CurriculumFragment:BaseVmFragment<FragmentCurriculumBinding, CurriculumViewModel>() {
    override fun viewModelClass(): Class<CurriculumViewModel> = CurriculumViewModel::class.java


    override fun getViewBinding(): FragmentCurriculumBinding {
     return FragmentCurriculumBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

}