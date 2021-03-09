package com.example.shiwuyouproject.utils;
import android.app.Activity;

import com.example.shiwuyouproject.R;
import com.example.shiwuyouproject.view.TipsDialog;

import java.util.ArrayList;

/**
 * 文件名:AppActivityStatusUtil
 * 创建者:zed
 * 创建日期:2019/8/20 10:52
 * 描述:TODO
 */
public class AppActivityStatusUtil {
    private static ArrayList<Activity> ACTIVITY_PROXY = new ArrayList<>();

    public static void register(Activity activity) {
        if (ACTIVITY_PROXY != null)
            ACTIVITY_PROXY.add(activity);
    }

    public static void unRegister(Activity activity) {
        if (ACTIVITY_PROXY != null && ACTIVITY_PROXY.contains(activity))
            ACTIVITY_PROXY.remove(activity);
    }

    public static void backToLogin() {
        final Activity currentActivity = getTopActivity();
        if (currentActivity == null) return;
        TipsDialog.createDialog(currentActivity, R.layout.login_expired)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .bindClick(R.id.tv_cancel, (v, dialog) -> {
//                        ActStartUtils.startAct(currentActivity, LoginActivity.class);
//                        SPUtils.getInstance().clear();
//                        clearAllActivity();
                }).show();
    }
    /**
     * 找到最顶层activity 并且当前activity并未 finish
     *
     * @return
     */
    private static Activity getTopActivity() {
        try {
            if (ACTIVITY_PROXY.size() == 0) return null;
            for (int i = ACTIVITY_PROXY.size() - 1; i >= 0; i--) {
                if (!ACTIVITY_PROXY.get(i).isFinishing())
                    return ACTIVITY_PROXY.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
