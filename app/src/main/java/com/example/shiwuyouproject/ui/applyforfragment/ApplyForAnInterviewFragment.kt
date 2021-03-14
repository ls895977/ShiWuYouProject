package com.example.shiwuyouproject.ui.applyforfragment
import android.content.Intent
import android.graphics.Color
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.example.shiwuyouproject.base.BaseVmFragment
import com.example.shiwuyouproject.databinding.FragmentApplyforaninterviewBinding
import com.example.shiwuyouproject.ui.ApplyForPartTimeCoursesActivity
import com.example.shiwuyouproject.ui.applyforfragment.viewmdoel.ApplyForAnInterviewViewModel
import com.example.shiwuyouproject.ui.fragment.my.activity.TeacherInformationActivity
import com.example.shiwuyouproject.ui.my.RealNameAuthenticationActivity
import com.example.shiwuyouproject.utils.ActStartUtils


/**
 * 申请面试
 */
class ApplyForAnInterviewFragment:BaseVmFragment<FragmentApplyforaninterviewBinding, ApplyForAnInterviewViewModel>(){
    override fun viewModelClass(): Class<ApplyForAnInterviewViewModel> = ApplyForAnInterviewViewModel::class.java

    override fun getViewBinding(): FragmentApplyforaninterviewBinding {
        return FragmentApplyforaninterviewBinding.inflate(layoutInflater)
    }
    var myApp: ApplyForPartTimeCoursesActivity?=null
    override fun initView() {
        myApp= activity as ApplyForPartTimeCoursesActivity?

    }


    override fun initData() {
        mViewModel.getStatusAll()
         mViewModel.getRealNameAuthenticationData()
        initWorkPicker()
        initWorkStatusPicker()
    }
    override fun setListener() {
        mBinding.btApplyForAnInTerView.setOnClickListener {
            myApp?.setPageStatus(1)
        }
        mBinding.clRealNameAuthentication.setOnClickListener { //实名认证
            ActStartUtils.startForAct(activity, RealNameAuthenticationActivity::class.java,100)
        }
        mBinding.clNatureOfWork.setOnClickListener { //工作性质
            pvWork?.show()
        }
        mBinding.clWorkingCondition.setOnClickListener { //工作状态
            pvWorkStatus?.show()

        }
        mBinding.clItem2.setOnClickListener { //个人资料
            ActStartUtils.startAct(context, TeacherInformationActivity::class.java)
        }

    }

    /**
     * 工作性质初始化
     */
    private var pvWork: OptionsPickerView<*>? = null
    private fun initWorkPicker() { //条件选择器初始化
        val workData:MutableList<String> =ArrayList()
        workData.add("兼职")
        workData.add("全职")
        workData.add("实习")
        pvWork = OptionsPickerBuilder(activity) { options1, _, _, v -> //返回的分别是三个级别的选中位置
            mBinding.tvNatureOfWorkStatus.text= workData[options1]
            mBinding.tvNatureOfWorkStatus.isSelected=true}
                .setTitleText("工作性质")
                .setContentTextSize(20) //设置滚轮文字大小
                .setDividerColor(Color.LTGRAY) //设置分割线的颜色
                .setSelectOptions(0, 1) //默认选中项
                .isRestoreItem(true) //切换时是否还原，设置默认选中第一项。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .build<Any>()
        (pvWork as OptionsPickerView<Any>?)?.setPicker(workData as List<Any>?) //二级选择器
    }
    /**
     * 工作状态初始化
     */
    private var pvWorkStatus: OptionsPickerView<*>? = null
    private fun initWorkStatusPicker() {
        val workData:MutableList<String> =ArrayList()
        workData.add("在职")
        workData.add("非在职")
        pvWorkStatus = OptionsPickerBuilder(activity) { options1, _, _, v -> //返回的分别是三个级别的选中位置
            mBinding.tvWorkingConditionStatus.text= workData[options1]
            mBinding.tvWorkingConditionStatus.isSelected=true}
                .setTitleText("工作状态")
                .setContentTextSize(20) //设置滚轮文字大小
                .setDividerColor(Color.LTGRAY) //设置分割线的颜色
                .setSelectOptions(0, 1) //默认选中项
                .isRestoreItem(true) //切换时是否还原，设置默认选中第一项。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .build<Any>()
        (pvWorkStatus as OptionsPickerView<Any>?)?.setPicker(workData as List<Any>?) //二级选择器
    }

    override fun observe() {
        mViewModel.applyForDataState.observe(this,{
                    if(it.auth_id==1) {//实名认证
                        mBinding.tvRealNameAuthenticationStatus.isSelected =true
                        mBinding.tvRealNameAuthenticationStatus.text="完成"
                    }else{
                        mBinding.tvRealNameAuthenticationStatus.isSelected =false
                        mBinding.tvRealNameAuthenticationStatus.text="请选择"
                    }
            //工作性质
            mBinding.tvNatureOfWorkStatus.isSelected=it.auth_job==1
            //工作状态
            mBinding.tvWorkingConditionStatus.isSelected=it.auth_working==1

            mBinding.tvPersonalDataStatus.isSelected=it.auth_info==1
            mBinding.tvSubjectStatus.isSelected=it.auth_subject==1
            mBinding.tvTeachingTimeStatus.isSelected=it.auth_course_time==1
            mBinding.tvTeachingAddressORRegionStatus.isSelected=it.auth_course_area==1
            mBinding.tvudioOrVideoStatus.isSelected=it.auth_media==1
            mBinding.tvAsicServiceCriteriaStatus.isSelected=it.auth_step==1
        })
        mViewModel.getGenRenStatus.observe(this,{
               when (it.part_job) {
                   "1" -> {//工作性质
                       mBinding.tvNatureOfWorkStatus.text = "兼职"
                   }
                   "2" -> {//工作性质
                       mBinding.tvNatureOfWorkStatus.text = "全职"
                   }
                   "3" -> {//工作性质
                       mBinding.tvNatureOfWorkStatus.text = "实习"
                   }
                   else -> {
                       mBinding.tvNatureOfWorkStatus.text = "请选择"
                   }
               }

            when (it.working) {
                "1" -> {//工作状态
                    mBinding.tvWorkingConditionStatus.text = "在职"
                }
                "2" -> {//工作状态
                    mBinding.tvWorkingConditionStatus.text = "非在职"
                }
                else -> {
                    mBinding.tvWorkingConditionStatus.text = "请选择"
                }
            }

            //基本服务准则
            if(it.service_rule=="1"){
                mBinding.tvAsicServiceCriteriaStatus.text="完成"
            }else{
                mBinding.tvAsicServiceCriteriaStatus.text="请选择"
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100&&resultCode==100){
            mViewModel.getStatusAll()
            mViewModel.getRealNameAuthenticationData()
        }
    }

}