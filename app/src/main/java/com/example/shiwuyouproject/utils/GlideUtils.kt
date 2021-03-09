package com.example.shiwuyouproject.utils
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
 object  GlideUtils {

    var headerOption= RequestOptions()
//        .error(R.mipmap.ic_heard)
    fun headPortrait(context: Context, imView:ImageView, url:String){
        Glide.with(context)
            .applyDefaultRequestOptions(headerOption)
            .load(url)
            .into(imView)
    }

     var fangImgOption= RequestOptions()
//             .error(R.mipmap.ic_fangxing)
     fun fangImgPortrait(context: Context, imView:ImageView, url:String){
         Glide.with(context)
                 .applyDefaultRequestOptions(fangImgOption)
                 .load(url)
                 .into(imView)
     }
}