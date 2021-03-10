package com.example.shiwuyouproject.ui

import com.blankj.utilcode.util.RegexUtils
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRegisterBinding
import com.example.shiwuyouproject.ui.modelview.RegisterViewModel
import com.example.shiwuyouproject.utils.ActStartUtils
import com.example.shiwuyouproject.utils.CountDownTimerUtils
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils

/**
 * ls
 * 注册
 */
class RegisterActivity:BaseVmActivity<ActivityRegisterBinding, RegisterViewModel>() {
    override fun viewModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun getViewBinding(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }
    private var mCountDownTimerUtils:CountDownTimerUtils?=null
    override fun initView() {
        mCountDownTimerUtils = CountDownTimerUtils(mBinding.tvCode, 60000, 1000)
    }

    override fun initData() {

    }
    override fun setListener() {
      mBinding.tvCode.setOnClickListener {
          val username = mBinding.etUsername.text.toString().trim()
          if (username.isBlank()) {
              ToastUtil.show("手机号码不能为空！")
              return@setOnClickListener
          }
           if( !RegexUtils.isMobileSimple(username)){
               ToastUtil.show("请输入正确手机号！")
               return@setOnClickListener
           }
          YUtils.showLoading(this, "获取中...")
          mViewModel.smsSend(username)

      }
    mBinding.btRegister.setOnClickListener {
        ActStartUtils.startAct(this,ApplyForPartTimeCoursesActivity::class.java)
    }
    }
    override fun observe() {
        mViewModel.myCodeState.observe(this,{
            YUtils.hideLoading()
            ToastUtil.show("发送成功！")
            mCountDownTimerUtils?.start()
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        mCountDownTimerUtils?.onFinish()
    }
}