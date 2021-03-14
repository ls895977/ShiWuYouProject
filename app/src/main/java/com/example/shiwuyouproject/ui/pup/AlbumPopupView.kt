package com.example.shiwuyouproject.ui.pup

import android.content.Context
import android.view.View
import com.example.shiwuyouproject.R
import com.lxj.xpopup.core.BottomPopupView

/**
 * 弹窗
 */
class AlbumPopupView(context: Context,var onItem:OnBackItem) : BottomPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.put_album
    }

    override fun onCreate() {
        super.onCreate()
        findViewById<View>(R.id.tvCancel).setOnClickListener {
            dismiss()
        }
        findViewById<View>(R.id.tvCamera).setOnClickListener {
            dismiss()
            onItem.onCamera()
        }
        findViewById<View>(R.id.tvXiangCe).setOnClickListener {
            dismiss()
            onItem.onXiangCe()
        }
    }

    interface  OnBackItem{
        fun onCamera()
        fun onXiangCe()
    }
}