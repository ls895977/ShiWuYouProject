package com.example.shiwuyouproject.ui.my
import android.content.Intent
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRealnameauthenticationBinding
import com.example.shiwuyouproject.net.http.Api
import com.example.shiwuyouproject.ui.my.viewmodel.RealNameAuthenticationViewModel
import com.example.shiwuyouproject.utils.Auth
import com.example.shiwuyouproject.utils.GlideEngine
import com.example.shiwuyouproject.utils.GlideUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import java.text.SimpleDateFormat
import java.util.*
/**
 * 实名认证
 */
class RealNameAuthenticationActivity: BaseVmActivity<ActivityRealnameauthenticationBinding, RealNameAuthenticationViewModel>() {
    override fun viewModelClass(): Class<RealNameAuthenticationViewModel> = RealNameAuthenticationViewModel::class.java

    override fun getViewBinding(): ActivityRealnameauthenticationBinding {
     return ActivityRealnameauthenticationBinding.inflate(layoutInflater)
    }
    private var carPthZhen=""
    private var carPthFan=""
    private var cardStatus=false//false正面true反面
    override fun initView() {
        mBinding.clTitleBar.tvTitle.text="实名认证"

    }

    override fun initData() {
    mViewModel.getRealNameAuthenticationData()

    }

    override fun setListener() {
        mBinding.clTitleBar.tvBack.setOnClickListener { finish() }
     mBinding.clUploadTheFrontOfIDCard.setOnClickListener { //上传身份证正面
         cardStatus=false
         openCard()
     }
        mBinding.clUploadBackOfIDCard.setOnClickListener { //上传身份证反面
            cardStatus=true
            openCard()
        }
        mBinding.btShiBie.setOnClickListener {
            if(carPthZhen.isBlank()){
                ToastUtil.show("请上传身份证正面图片！")
                return@setOnClickListener
            }
            if(carPthFan.isBlank()){
                ToastUtil.show("请上传身份证反面图片！")
                return@setOnClickListener
            }
            YUtils.showLoading(this, "上传中...")
          mViewModel.preservationCard(carPthZhen,carPthFan)
        }
    }
//   private fun requestPermission() {
//        // checkSelfPermission 判断是否已经申请了此权限
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PERMISSION_GRANTED) {
//            //如果应用之前请求过此权限但用户拒绝了请求，shouldShowRequestPermissionRationale将返回 true。
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                            this,
//                            Manifest.permission.CAMERA
//                    )) {
//
//            } else {
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
//            }
//        }
//    }
//    override fun onRequestPermissionsResult(
//            requestCode: Int,
//            permissions: Array<String>,
//            grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1) {
//            for (i in permissions.indices) {
//                if (grantResults[i] == PERMISSION_GRANTED) {
//                    openCard()
//                } else {
//                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//    }

    private fun openCard(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
//            .isEnableCrop(true)//是否开启裁剪
//                .cropImageWideHigh(8,6)// 裁剪宽高比，设置如果大于图片本身宽高则无效
                .maxSelectNum(1)//最大选择数量,默认9张
                .imageEngine(GlideEngine.createGlideEngine())
                .isCompress(true)//是否压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)

    }
    private val TAG: String = RealNameAuthenticationActivity::class.java.getSimpleName()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                // 图片选择结果回调
                val selectList = PictureSelector.obtainMultipleResult(data)
                    Log.e(TAG, "压缩:" + selectList[0].compressPath)
                upFile(selectList[0].compressPath)
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
    private var upLoadManager: UploadManager ?=null
    private var upToken: String? = null
   private  fun upFile(pathFile:String){
        upLoadManager=UploadManager(config)
        val auth: Auth = Auth.create(accessKey, secretKey)
        upToken = auth.uploadToken(bucket)
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val key1 = "icon_" + sdf.format(Date())
        upLoadManager?.put(pathFile, key1, upToken,
                { key, info, res -> //res包含hash、key等信息，具体字段取决于上传策略的设置
                    if (info.isOK) {
                        if(!cardStatus){//正面
                        carPthZhen=key
                            GlideUtils.fangImgPortrait(this,mBinding.ivUploadIDCard, Api.IMAGEHEAD+carPthZhen)
                        }else{//反面
                            carPthFan=key
                            GlideUtils.fangImgPortrait(this,mBinding.ivUploadBackOfIDCard, Api.IMAGEHEAD+carPthFan)
                        }
                        setBootStatus()
                    } else {
                        Toast.makeText(this@RealNameAuthenticationActivity, "图片上传失败，请检查网络连接", Toast.LENGTH_SHORT)
                                .show()
                        Looper.loop()
                    }
                }, null
        )
    }
    /**
     * 检测按钮颜色状态
     */
    private fun setBootStatus(){
        if(carPthZhen.isBlank()){
            mBinding.btShiBie.isSelected=false
            return
        }
        if(carPthFan.isBlank()){
            mBinding.btShiBie.isSelected=false
            return
        }
        mBinding.btShiBie.isSelected=true
    }

    override fun observe() {
        mViewModel.getGenRenStatus.observe(this,{
            carPthZhen=it.idfront
            carPthFan=it.idback
            GlideUtils.fangImgPortrait(this,mBinding.ivUploadIDCard, Api.IMAGEHEAD+carPthZhen)
            GlideUtils.fangImgPortrait(this,mBinding.ivUploadBackOfIDCard, Api.IMAGEHEAD+carPthFan)
            setBootStatus()
        })
        mViewModel.getPostStatus.observe(this,{
            YUtils.hideLoading()
            if(it){
                ToastUtil.show("身份图片，上传成功！")
                setResult(100)
                finish()
            }else{
                ToastUtil.show("身份图片，上传失败！")
            }

        })

    }
}