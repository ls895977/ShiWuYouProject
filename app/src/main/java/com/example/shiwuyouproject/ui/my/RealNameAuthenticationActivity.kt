package com.example.shiwuyouproject.ui.my

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.shiwuyouproject.base.BaseVmActivity
import com.example.shiwuyouproject.databinding.ActivityRealnameauthenticationBinding
import com.example.shiwuyouproject.ui.my.viewmodel.RealNameAuthenticationViewModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia

import com.luck.picture.lib.listener.OnResultCallbackListener





/**
 * 实名认证
 */
class RealNameAuthenticationActivity: BaseVmActivity<ActivityRealnameauthenticationBinding, RealNameAuthenticationViewModel>() {
    override fun viewModelClass(): Class<RealNameAuthenticationViewModel> = RealNameAuthenticationViewModel::class.java

    override fun getViewBinding(): ActivityRealnameauthenticationBinding {
     return ActivityRealnameauthenticationBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mBinding.clTitleBar.tvTitle.text="实名认证"

    }

    override fun initData() {


    }

    override fun setListener() {
     mBinding.clUploadTheFrontOfIDCard.setOnClickListener { //上传身份证正面
         requestPermission()
     }
        mBinding.clUploadBackOfIDCard.setOnClickListener { //上传身份证反面
            requestPermission()

        }
    }
   private fun requestPermission() {
        // checkSelfPermission 判断是否已经申请了此权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，shouldShowRequestPermissionRationale将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            for (i in permissions.indices) {
                if (grantResults[i] == PERMISSION_GRANTED) {


                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun openCard(){
//        PictureSelector.create(this)
//                .openGallery(PictureMimeType.ofAll())
//                .loadImageEngine(GlideEngine.createGlideEngine())
//                .forResult(object : OnResultCallbackListener<LocalMedia?> {
//                    override fun onResult(result: List<LocalMedia?>) {
//                        // onResult Callback
//                    }
//
//                    override fun onCancel() {
//                        // onCancel Callback
//                    }
//                })
    }
}