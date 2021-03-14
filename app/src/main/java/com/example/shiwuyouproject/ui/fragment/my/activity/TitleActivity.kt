package com.example.shiwuyouproject.ui.fragment.my.activity

import android.content.Intent
import android.graphics.Color
import android.os.Looper
import android.widget.Toast
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityTitleBinding
import com.example.shiwuyouproject.net.http.Api
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.TitleViewModel
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
import kotlin.collections.ArrayList

/**
 * 职称
 */
class TitleActivity :BaseVmActivity<ActivityTitleBinding, TitleViewModel>(){
    override fun viewModelClass(): Class<TitleViewModel> = TitleViewModel::class.java

    override fun getViewBinding(): ActivityTitleBinding {
return ActivityTitleBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
        mBinding.rlZhiCheng.setOnClickListener {
            pvZcnameStatus?.show()
        }
        mBinding.zhichengImg.setOnClickListener { //职称照片
            openCard()
        }
        mBinding.btSava.setOnClickListener {
            YUtils.showLoading(this, "提交中...")
            mViewModel.preservationCard(zcname.toString(),myKey)

        }
    }
    override fun initData() {
        initZcnameStatusPicker()
        mViewModel.getRealNameAuthenticationData()

    }
    private var zcname=1
    override fun observe() {
        mViewModel.getGenRenStatus.observe(this,{
            zcname= it.zcname
            when(it.zcname){
                1->{
                    mBinding.tvZcname.text="无职称"
                }
                2->{
                    mBinding.tvZcname.text="三级教师"
                }
                3->{
                    mBinding.tvZcname.text="二级教师"
                }
                4->{
                    mBinding.tvZcname.text="一级教师"
                }
                5->{
                    mBinding.tvZcname.text="高级教师"
                }
            }
            myKey=it.zcimg
            if(it.zcimg.isNotBlank()) {
                GlideUtils.fangImgPortrait(this, mBinding.zcimg, Api.IMAGEHEAD + it.zcimg)
            }
        })
        mViewModel.getPostStatus.observe(this,{
            YUtils.hideLoading()
            if(it){
                ToastUtil.show("提交成功！")
                finish()
            }else{
                ToastUtil.show("提交失败！")
            }
        })
    }

    /**
     * 教师状态
     */
    private var pvZcnameStatus: OptionsPickerView<*>? = null
    private fun initZcnameStatusPicker() {
        val workData:MutableList<String> =ArrayList()
        workData.add("无职称")
        workData.add("三级教师")
        workData.add("二级教师")
        workData.add("一级教师")
        workData.add("高级教师")
        pvZcnameStatus = OptionsPickerBuilder(this) { options1, _, _, v -> //返回的分别是三个级别的选中位置
            mBinding.tvZcname.text = workData[options1]
            zcname=zcname+1
        }
            .setTitleText("职称选择")
            .setContentTextSize(20) //设置滚轮文字大小
            .setDividerColor(Color.LTGRAY) //设置分割线的颜色
            .setSelectOptions(0, 1) //默认选中项
            .isRestoreItem(true) //切换时是否还原，设置默认选中第一项。
            .setOutSideColor(0x00000000) //设置外部遮罩颜色
            .build<Any>()
        (pvZcnameStatus as OptionsPickerView<Any>?)?.setPicker(workData as List<Any>?) //二级选择器
    }
    private fun openCard(){
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .maxSelectNum(1)//最大选择数量,默认9张
            .imageEngine(GlideEngine.createGlideEngine())
            .isCompress(true)//是否压缩
            .forResult(PictureConfig.CHOOSE_REQUEST)

    }
    private var myKey=""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK&&requestCode== PictureConfig.CHOOSE_REQUEST) {
            // 图片选择结果回调
            val selectList = PictureSelector.obtainMultipleResult(data)
            upFile(selectList[0].compressPath)
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
                    GlideUtils.fangImgPortrait(this, mBinding.zcimg, Api.IMAGEHEAD + myKey)
                } else {
                    Toast.makeText(this@TitleActivity, "图片上传失败，请检查网络连接", Toast.LENGTH_SHORT)
                        .show()
                    Looper.loop()
                }
            }, null
        )
    }
}