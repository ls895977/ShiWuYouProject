package com.example.shiwuyouproject.ui.fragment.my.activity

import android.content.Intent
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityModifyremarksBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.ModifyRemarksViewModel
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils

/**
 * 修改各种备注
 */
class ModifyRemarksActivity:BaseVmActivity<ActivityModifyremarksBinding,ModifyRemarksViewModel>() {
    override fun viewModelClass(): Class<ModifyRemarksViewModel> = ModifyRemarksViewModel::class.java

    override fun getViewBinding(): ActivityModifyremarksBinding {
      return ActivityModifyremarksBinding.inflate(layoutInflater)
    }
    private var titleType=""
    override fun initView() {
        titleType=intent.getStringExtra("titleType").toString()
        mBinding.myTitleBar.tvRightTitle.text="完成"
        if(titleType == "nickName"){//昵称
            mBinding.myTitleBar.tvTitle.text="修改昵称"
            if(intent.getStringExtra("stNickName")!=null) {
                mBinding.etName.setText(intent.getStringExtra("stNickName").toString())
            }
        }else  if(titleType == "name"){//真实姓名
            mBinding.myTitleBar.tvTitle.text="修改姓名"
            if(intent.getStringExtra("stRealname")!=null) {
                mBinding.etName.setText(intent.getStringExtra("stRealname").toString())
            }
        }
    }

    override fun setListener() {
        mBinding.myTitleBar.tvBack.setOnClickListener { finish() }
        mBinding.myTitleBar.tvRightTitle.setOnClickListener {
            if(mBinding.etName.text.isEmpty()){
                ToastUtil.show("请输入需要修改内容！")
                return@setOnClickListener
            }
            YUtils.showLoading(this, "修改中...")
            if(titleType == "nickName"){//昵称
                mViewModel.preservationNickName(mBinding.etName.text.toString())
            }else  if(titleType == "name"){//真实姓名
                mViewModel.preservationRealnRame(mBinding.etName.text.toString())
            }
        }
    }

    override fun observe() {
        mViewModel.getPostStatus.observe(this,{
            if(it){
                ToastUtil.show("修改成功！")
                val myIntent=Intent()
                myIntent.putExtra("titleType",mBinding.etName.text.toString())
                setResult(100,myIntent)
                finish()
            }else{
                ToastUtil.show("修改失败！")
            }
            YUtils.hideLoading()
        })


    }
}