package com.example.shiwuyouproject.base
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseVmActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    protected open lateinit var mViewModel: VM

    //分页参数
    protected open val mTotalCount = 20//每次加载数量
    protected open var mCurrentSize = 0//当前加载数量
    protected open var mCurrentPage = 0//当前加载页数

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    /**
     * 获取ViewModel的class
     */
    protected abstract fun viewModelClass(): Class<VM>

    /**
     * 订阅，有逻辑的话，复写的时候super不要去掉
     */
    open fun observe() {

    }

    /**
     * 初始化view相关
     */
    open fun initView() {}

    /**
     * 初始化data相关
     */
    open fun initData() {}

    /**
     * 设置监听
     */
    open fun setListener() {}

    override fun onDestroy() {
        super.onDestroy()
        mCurrentSize = 0
        mCurrentPage = 0
    }

}
