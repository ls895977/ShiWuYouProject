package com.example.shiwuyouproject.ui.fragment
import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentMyBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.SettingActivity
import com.example.shiwuyouproject.ui.fragment.my.activity.TeacherInformationActivity
import com.example.shiwuyouproject.ui.modelview.MyViewModel
import com.example.shiwuyouproject.utils.ActStartUtils
class MyFragment:BaseVmFragment<FragmentMyBinding, MyViewModel>(){
    override fun viewModelClass(): Class<MyViewModel> = MyViewModel::class.java

    override fun getViewBinding(): FragmentMyBinding {
        return FragmentMyBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun setListener() {
        binding?.btSetting?.setOnClickListener { //设置
            ActStartUtils.startAct(context, SettingActivity::class.java)
        }
        binding?.btTeacherInformation?.setOnClickListener {//个人资料
            ActStartUtils.startAct(context, TeacherInformationActivity::class.java)
        }
    }
}

