package com.example.shiwuyouproject.ui.fragment.my.activity
import android.content.Intent
import android.graphics.Color
import android.os.Looper
import android.view.View
import android.widget.*
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.example.shiwuyouproject.R
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityUniversityonisgradBinding
import com.example.shiwuyouproject.net.http.Api
import com.example.shiwuyouproject.ui.fragment.my.activity.viewmodel.UniversityonisgradViewModel
import com.example.shiwuyouproject.ui.my.RealNameAuthenticationActivity
import com.example.shiwuyouproject.utils.Auth
import com.example.shiwuyouproject.utils.GlideEngine
import com.example.shiwuyouproject.utils.GlideUtils
import com.example.shiwuyouproject.utils.MyTimeUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import kotlinx.android.synthetic.main.activity_universityonisgrad.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 毕业院校
 */
class UniversityonisgradActivity :BaseVmActivity<ActivityUniversityonisgradBinding, UniversityonisgradViewModel>(){
    override fun viewModelClass(): Class<UniversityonisgradViewModel> = UniversityonisgradViewModel::class.java

    override fun getViewBinding(): ActivityUniversityonisgradBinding {
        return ActivityUniversityonisgradBinding.inflate(layoutInflater)
    }

    override fun setListener() {
        mBinding.tvBack.setOnClickListener { finish() }
        mBinding.clUploadTheFrontOfIDCard.setOnClickListener { //毕业证书
            cardStatus=false
            openCard()
        }
        mBinding.clUploadTheFrontOfIDCard1.setOnClickListener { //学位证书
            cardStatus=true
            openCard()
        }
        mBinding.startTime.setOnClickListener {
            timeStatus=true
            pvCustomLunar?.show()
        }
        mBinding.eduendTime.setOnClickListener {
            timeStatus=false
            pvCustomLunar?.show()
        }
        mBinding.rldegree.setOnClickListener { //学历
            pvdegreeStatus?.show()
        }
        mBinding.btSava.setOnClickListener { //保存
            YUtils.showLoading(this, "保存中...")
            mViewModel.preservationCard(
                mBinding.etEduschool.text.toString(),
                myStStartTime,
                myStEndTime,
                degree.toString(),
                mBinding.etmajor.text.toString(),
                mBinding.eduno.text.toString(),
                carPthZhen,
                mBinding.xwno.text.toString(),
                carPthFan
            )

        }
    }

    override fun initView() {


    }

    override fun initData() {
        initCustomTimePicker()
        initdegreeStatusPicker()
        mViewModel.getRealNameAuthenticationData()

    }

    override fun observe() {
        mViewModel.getGenRenStatus.observe(this, {
            mBinding.etEduschool.setText(it.eduschool)//学校名称
            mBinding.edustartTime.text = it.edustart_time.toString()//开始时间
            mBinding.eduendTime.text = it.eduend_time.toString()//结束时间
            when (it.degree) {//学历
                1 -> {
                    mBinding.tvdegree.text = "专科"
                }
                2 -> {
                    mBinding.tvdegree.text = "本科"
                }
                3 -> {
                    mBinding.tvdegree.text = "硕士"
                }
                4 -> {
                    mBinding.tvdegree.text = "博士"
                }
            }
            mBinding.etmajor.setText(it.major)//专业
            mBinding.eduno.setText(it.eduno)//学历证书编号
            carPthZhen = it.eduimg
            GlideUtils.fangImgPortrait(this, mBinding.ivEduimg, Api.IMAGEHEAD + it.eduimg)//学历证书
            carPthFan = it.xwimg
            mBinding.xwno.setText(it.xwno)//学位证书编号
            GlideUtils.fangImgPortrait(this, mBinding.xwimg, Api.IMAGEHEAD + it.xwimg)//学位证书
        })
        mViewModel.getPostStatus.observe(this,{
            YUtils.hideLoading()
            if(it){
                finish()
                ToastUtil.show("保存成功！")
            }else{
                ToastUtil.show("保存失败！")
            }

        })
    }

    private fun openCard(){
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
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
                upFile(selectList[0].compressPath)
            }
        }
    }
    private var carPthZhen=""
    private var carPthFan=""
    private var cardStatus=false//false正面true反面
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
    private  fun upFile(pathFile: String){
        upLoadManager= UploadManager(config)
        val auth: Auth = Auth.create(accessKey, secretKey)
        upToken = auth.uploadToken(bucket)
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val key1 = "icon_" + sdf.format(Date())
        upLoadManager?.put(
            pathFile, key1, upToken,
            { key, info, res -> //res包含hash、key等信息，具体字段取决于上传策略的设置
                if (info.isOK) {
                    if (!cardStatus) {//正面
                        carPthZhen = key
                        GlideUtils.fangImgPortrait(
                            this,
                            mBinding.ivEduimg,
                            Api.IMAGEHEAD + carPthZhen
                        )
                    } else {//反面
                        carPthFan = key
                        GlideUtils.fangImgPortrait(this, mBinding.xwimg, Api.IMAGEHEAD + carPthFan)
                    }
                } else {
                    Toast.makeText(
                        this@UniversityonisgradActivity,
                        "图片上传失败，请检查网络连接",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Looper.loop()
                }
            }, null
        )
    }
    private var timeStatus=false
    var pvCustomLunar: TimePickerView? = null
    var cuurDataTime:Date?=null
    var stStartTime:Date?=null
    var stendTime:Date?=null
    var myStStartTime=""
    var myStEndTime=""
    private fun initCustomTimePicker() {
        val selectedDate = Calendar.getInstance() //系统当前时间
        val startDate = Calendar.getInstance()
        startDate[2014, 1] = 23
        val endDate = Calendar.getInstance()
        endDate[2069, 2] = 28
        //时间选择器 ，自定义布局
        //时间选择器 ，自定义布局
        pvCustomLunar = TimePickerBuilder(
            this
        ) { date, v -> //选中事件回调
                cuurDataTime=date
        }
            .setDate(selectedDate)
            .setRangDate(startDate, endDate)
            .setLayoutRes(R.layout.pickerview_custom_lunar
            ) { v ->
                val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                val ivCancel = v.findViewById<View>(R.id.iv_cancel) as TextView
                tvSubmit.setOnClickListener {
                    pvCustomLunar?.returnData()
                    pvCustomLunar?.dismiss()
                    if(startDate!=null&&cuurDataTime!=null){

                    }
                    if(timeStatus){
                        stStartTime=cuurDataTime
                        myStStartTime= stStartTime?.let { it1 -> MyTimeUtils.getTimes(it1) }!!
                        mBinding.edustartTime.text= stStartTime?.let { it1 ->
                            MyTimeUtils.getTimesYe(
                                it1
                            )
                        }
                    }else{
                        stendTime=cuurDataTime
                        myStEndTime= stendTime?.let { it1 -> MyTimeUtils.getTimes(it1) }!!
                        mBinding.eduendTime.text= stendTime?.let { it1 ->
                            MyTimeUtils.getTimesYe(
                                it1
                            )
                        }
                    }
                }
                ivCancel.setOnClickListener { pvCustomLunar?.dismiss() }
            }
            .setDividerColor(Color.WHITE)//设置分割线的颜色
            .setType(booleanArrayOf(true, true, false, false, false, false))
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .setDividerColor(Color.RED)
            .build()
    }
    private var degree=1
    /**
     * 学历
     */
    private var pvdegreeStatus: OptionsPickerView<*>? = null
    private fun initdegreeStatusPicker() {
        val workData:MutableList<String> =ArrayList()
        workData.add("专科")
        workData.add("本科")
        workData.add("硕士")
        workData.add("博士")
        pvdegreeStatus = OptionsPickerBuilder(this) { options1, _, _, v -> //返回的分别是三个级别的选中位置
            mBinding.tvdegree.text= workData[options1]
            degree=options1+1
        }
            .setTitleText("工作状态")
            .setContentTextSize(20) //设置滚轮文字大小
            .setDividerColor(Color.LTGRAY) //设置分割线的颜色
            .setSelectOptions(0, 1) //默认选中项
            .isRestoreItem(true) //切换时是否还原，设置默认选中第一项。
            .setOutSideColor(0x00000000) //设置外部遮罩颜色
            .build<Any>()
        (pvdegreeStatus as OptionsPickerView<Any>?)?.setPicker(workData as List<Any>?) //二级选择器
    }
}