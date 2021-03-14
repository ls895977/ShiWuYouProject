package com.example.shiwuyouproject.ui.fragment.my.activity
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityPersonalinformationBinding
import com.example.shiwuyouproject.net.http.Api
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.PersonalInformationViewModel
import com.example.shiwuyouproject.ui.pup.AlbumPopupView
import com.example.shiwuyouproject.utils.ActStartUtils
import com.example.shiwuyouproject.utils.Auth
import com.example.shiwuyouproject.utils.GlideEngine
import com.example.shiwuyouproject.utils.GlideUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.lxj.xpopup.XPopup
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import java.text.SimpleDateFormat
import java.util.*


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
    mViewModel.getRealNameAuthenticationData()

    }
    var stNickName=""
    var stRealname=""
    override fun setListener() {
        mBinding.MyTitleBar.tvBack.setOnClickListener {finish()}
        mBinding.itemHeader.setOnClickListener { //个人头像修改
            XPopup.Builder(this)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                .asCustom(AlbumPopupView(this, object : AlbumPopupView.OnBackItem {
                    override fun onCamera() {
                        PictureSelector.create(this@PersonalInformationActivity)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.REQUEST_CAMERA)
                    }

                    override fun onXiangCe() {
                        PictureSelector.create(this@PersonalInformationActivity)
                                .openGallery(PictureMimeType.ofImage())
//            .isEnableCrop(true)//是否开启裁剪
//                .cropImageWideHigh(8,6)// 裁剪宽高比，设置如果大于图片本身宽高则无效
                                .maxSelectNum(1)//最大选择数量,默认9张
                                .imageEngine(GlideEngine.createGlideEngine())
                                .isCompress(true)//是否压缩
                                .forResult(PictureConfig.REQUEST_CAMERA)
                    }
                }))
                .show()
        }
        mBinding.itemNickName.setOnClickListener {
            val mBundle=Bundle()
            mBundle.putString("titleType","nickName")
            if(stNickName.isNotEmpty()){
                mBundle.putString("stNickName",stNickName)
            }
            ActStartUtils.startForAct(this, ModifyRemarksActivity::class.java,mBundle,100)
        }
        mBinding.itemName.setOnClickListener {
            val mBundle=Bundle()
            mBundle.putString("titleType","name")
            if(stRealname.isNotEmpty()){
                mBundle.putString("stRealname",stNickName)
            }
            ActStartUtils.startForAct(this, ModifyRemarksActivity::class.java,mBundle,100)
        }
    }
    private var myKey=""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK&&requestCode==PictureConfig.REQUEST_CAMERA) {
            YUtils.showLoading(this, "上传中...")
            // 图片选择结果回调
            val selectList = PictureSelector.obtainMultipleResult(data)
            upFile(selectList[0].compressPath)
        }else{
            if(resultCode==100&&requestCode==100){
                mBinding.tvnickname.text = data?.getStringExtra("titleType")
            }else    if(resultCode==100&&requestCode==101){
                mBinding.tvrealname.text = data?.getStringExtra("titleType")
            }
        }
    }
    var accessKey = "5Mq4K0HoqmBl_Clmc4x358zCHC2Zbwg1pltAFBOG"
    var secretKey = "nQbAllsv15ly9yMf05_jil7exdCHsXS1k4V7WR5d"
    var bucket = "uswy"
    var config: Configuration = Configuration.Builder()
            .zone(FixedZone.zone2)
            .connectTimeout(10)           // 链接超时。默认10秒
            .useHttps(true)               // 是否使用https上传域名
            .responseTimeout(60)          // 服务器响应超时。默认60秒
            .recorder(null)           // recorder分片上传时，已上传片记录器。默认null
            .build()
    private var upLoadManager: UploadManager?=null
    private var upToken: String? = null
    private  fun upFile(pathFile:String){
        upLoadManager= UploadManager(config)
        val auth: Auth = Auth.create(accessKey, secretKey)
        upToken = auth.uploadToken(bucket)
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val key1 = "icon_" + sdf.format(Date())
        upLoadManager?.put(pathFile, key1, upToken,
                { key, info, res -> //res包含hash、key等信息，具体字段取决于上传策略的设置
                    if (info.isOK) {
                        myKey=key
                    mViewModel.preservationCard(key)
                    } else {
                        Toast.makeText(this@PersonalInformationActivity, "图片上传失败，请检查网络连接", Toast.LENGTH_SHORT)
                                .show()
                        Looper.loop()
                    }
                }, null
        )
    }
    override fun observe() {
        mViewModel.getPostStatus.observe(this,{
            YUtils.hideLoading()
            if(it) {
                ToastUtil.show("上传成功！")
                GlideUtils.fangImgPortrait(this, mBinding.ivUploadIDCard, Api.IMAGEHEAD+myKey )
            }else{
                ToastUtil.show("上传失败！")
            }
        })
        mViewModel.getGenRenStatus.observe(this,{
            myKey=it.avatar
            GlideUtils.fangImgPortrait(this, mBinding.ivUploadIDCard, Api.IMAGEHEAD+myKey )
            mBinding.tvnickname.text=it.nickname
            mBinding.tvrealname.text=it.realname
            stNickName=it.nickname
            stRealname=it.realname

        })
    }
}