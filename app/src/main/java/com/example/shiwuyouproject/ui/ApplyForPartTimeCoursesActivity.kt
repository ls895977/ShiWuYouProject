package com.example.shiwuyouproject.ui
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.shiwuyouproject.R
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityApplyforparttimecoursesBinding
import com.example.shiwuyouproject.ui.applyforfragment.ApplyForAnInterviewFragment
import com.example.shiwuyouproject.ui.applyforfragment.CourseOpeningAuditFragment
import com.example.shiwuyouproject.ui.applyforfragment.SignAContractFragment
import com.example.shiwuyouproject.ui.applyforfragment.SuccessfulOpeningOfTheCourseFragment
import com.example.shiwuyouproject.ui.modelview.ApplyForPartTimeCoursesViewModel
import java.util.ArrayList

/**
 * 申请兼课
 */
class ApplyForPartTimeCoursesActivity:BaseVmActivity<ActivityApplyforparttimecoursesBinding, ApplyForPartTimeCoursesViewModel>(){
    override fun viewModelClass(): Class<ApplyForPartTimeCoursesViewModel> = ApplyForPartTimeCoursesViewModel::class.java

    override fun getViewBinding(): ActivityApplyforparttimecoursesBinding {
    return ActivityApplyforparttimecoursesBinding.inflate(layoutInflater)
    }
    private val title = arrayOfNulls<TextView>(4)
    private val titleContext = arrayOfNulls<TextView>(4)
    override fun initView() {
        title[0]=mBinding.tv1
        title[1]=mBinding.tv2
        title[2]=mBinding.tv3
        title[3]=mBinding.tv4
        titleContext[0]=mBinding.ttv1
        titleContext[1]=mBinding.ttv2
        titleContext[2]=mBinding.ttv3
        titleContext[3]=mBinding.ttv4
    }
    var fgtData: MutableList<Fragment> = ArrayList<Fragment>()
    override fun initData() {
        fgtData.add(ApplyForAnInterviewFragment())
        fgtData.add(CourseOpeningAuditFragment())
        fgtData.add(SignAContractFragment())
        fgtData.add(SuccessfulOpeningOfTheCourseFragment())
        supportFragmentManager.beginTransaction().add(R.id.myFragment, fgtData[0]).add(
            R.id.myFragment,
            fgtData[1]
        ).hide(fgtData[1]).show(fgtData[0]).commit()
        setCurrent(0)
    }
    override fun setListener() {
    mBinding.ivBack.setOnClickListener { finish() }
    }
    /**
     * 设置点前页面
     */
    fun setPageStatus(index: Int){
        setCurrent(index)
        choseCheck(index)
    }
    var currentTabIndex = 0
    var page = 0
   private fun setCurrent(index: Int) {
        if (currentTabIndex != index) {
            val trx: FragmentTransaction = supportFragmentManager.beginTransaction()
            trx.hide(fgtData[currentTabIndex])
            if (!fgtData[index].isAdded) {
                trx.add(R.id.fragment_container, fgtData[index])
            }
            trx.show(fgtData[index]).commit()
        }
        title[currentTabIndex]!!.isSelected = false
        title[index]!!.isSelected = true
        currentTabIndex = index
    }
    /**
     * 处理当前显示样式
     */
    private  fun choseCheck(myIndex:Int){
        titleContext.forEachIndexed { index, _ ->
            title[index]?.isSelected=index<=myIndex
            titleContext[index]?.isSelected=index<=myIndex
        }
    }

}