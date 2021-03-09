package com.example.shiwuyouproject.ui
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.shiwuyouproject.R
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityMainBinding
import com.example.shiwuyouproject.ui.fragment.CurriculumFragment
import com.example.shiwuyouproject.ui.fragment.HomeFragment
import com.example.shiwuyouproject.ui.fragment.MessageFragment
import com.example.shiwuyouproject.ui.fragment.MyFragment
import com.example.shiwuyouproject.ui.modelview.MainViewModel
import java.util.*

class MainActivity : BaseVmActivity<ActivityMainBinding, MainViewModel>(){
    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun getViewBinding(): ActivityMainBinding {
      return ActivityMainBinding.inflate(layoutInflater)
    }
    private val title = arrayOfNulls<TextView>(4)
    override fun initView() {
        title[0] = findViewById(R.id.main_tab_home)
        title[1] = findViewById(R.id.main_tab_curriculum)
        title[2] = findViewById(R.id.main_tab_message)
        title[3] = findViewById(R.id.main_tab_my)
    }
    var fgtData: MutableList<Fragment> = ArrayList<Fragment>()
    override fun initData() {
         fgtData.add(HomeFragment())
         fgtData.add(CurriculumFragment())
         fgtData.add(MessageFragment())
         fgtData.add(MyFragment())
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fgtData[0]).add(
            R.id.fragment_container,
            fgtData[1]
        ).hide(fgtData[1]).show(fgtData[0]).commit()
        setCurrent(page)
    }

    override fun setListener() {
    mBinding.mainTabHome.setOnClickListener {
        setCurrent(0)
    }
        mBinding.mainTabCurriculum.setOnClickListener {
            setCurrent(1)
        }
        mBinding.mainTabMessage.setOnClickListener {
            setCurrent(2)
        }
        mBinding.mainTabMy.setOnClickListener {
            setCurrent(3)
        }

    }
    var currentTabIndex = 0
    var page = 0
    fun setCurrent(index: Int) {
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
}