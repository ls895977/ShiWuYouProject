package com.example.shiwuyouproject.ui.fragment.my.activity

import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityPersonalinformationBinding
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.PersonalInformationViewModel
import com.example.shiwuyouproject.ui.pup.AlbumPopupView
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.lxj.xpopup.XPopup
import java.security.AccessController.getContext

/**
 * 个人信息
 */
class PersonalInformationActivity:BaseVmActivity<ActivityPersonalinformationBinding, PersonalInformationViewModel>(){
    override fun viewModelClass(): Class<PersonalInformationViewModel> =PersonalInformationViewModel::class.java
    override fun getViewBinding(): ActivityPersonalinformationBinding {
        return  ActivityPersonalinformationBinding.inflate(layoutInflater)
    }

    override fun initView() {
    mBinding.MyTitleBar.tvTitle.text="个人信息"

    }

    override fun initData() {


    }

    override fun setListener() {
        mBinding.MyTitleBar.tvBack.setOnClickListener {finish()}
        mBinding.itemHeader.setOnClickListener { //个人头像修改
            XPopup.Builder(this)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                .asCustom(AlbumPopupView(this,object : AlbumPopupView.OnBackItem {
                    override fun onCamera() {
                        PictureSelector.create(this@PersonalInformationActivity)
                            .openCamera(PictureMimeType.ofImage())
                            .forResult(PictureConfig.REQUEST_CAMERA)
                    }
                    override fun onXiangCe() {

                    }
                }) )
                .show()
        }
    }

    override fun observe() {

    }
}