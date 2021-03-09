package com.example.shiwuyouproject.utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActStartUtils {

    public ActStartUtils() {
        throw new UnsupportedOperationException("ActivitySkipUtil不能实例化");
    }

    /**
     * 功能描述:简单地Activity的跳转(不携带任何数据)
     *
     * @param context 发起跳转的Activity实例
     * @param cls     目标Activity实例
     */
    public static void startAct(Context context, Class<? extends Activity> cls) {
        if (cls == null) return;
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 功能描述：带数据的Activity之间的跳转
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startAct(Context context, Class<? extends Activity> cls, Bundle bundle) {
        if (cls == null) return;
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 带参数携带结果回调
     *
     * @param context
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public static void startForAct(Activity context, Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        if (cls == null) return;
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 不带参数携带结果回调
     *
     * @param context
     * @param cls
     * @param requestCode
     */
    public static void startForAct(Activity context, Class<? extends Activity> cls, int requestCode) {
        if (cls == null) return;
        Intent intent = new Intent(context, cls);
        context.startActivityForResult(intent, requestCode);
    }

}
