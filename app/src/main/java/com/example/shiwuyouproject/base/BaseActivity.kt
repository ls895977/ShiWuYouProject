package com.example.shiwuyouproject.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.shiwuyouproject.R
import com.gyf.immersionbar.ImmersionBar
import com.yechaoa.yutilskt.ToastUtil

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = getViewBinding()
        setContentView(mBinding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        ImmersionBar.with(this)
            .transparentStatusBar()
            .fullScreen(true)
            .statusBarDarkFont(statusBarDark())
            .keyboardEnable(true)
            .navigationBarColor(R.color.white).init()
        initialize()
    }

    /**
     * 状态栏字体深色或亮色
     *
     *
     * isDarkFont true 深色
     */
    protected open fun statusBarDark(): Boolean {
        return true
    }
    abstract fun getViewBinding(): VB

    /**
     * 命名与子类要区分，否则会先调用BaseActivity中同名方法，再调用BaseVmActivity中同名方法，可能会出现调用顺序导致的错误
     */
    open fun initialize() {}

    override fun onDestroy() {
        super.onDestroy()
        ToastUtil.release()
    }
}