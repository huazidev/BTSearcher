package com.huazidev.btsearcher.util;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.huazidev.btsearcher.common.BTSearcherApplicationContext;
import com.huazidev.btsearcher.common.ResourceHelper;

/**
 * Toast工具
 */
public class ToastUtils {
    private static Toast toast;
    private static Toast centerToast;

    public static void shortT(@StringRes int resId) {
        shortT(ResourceHelper.getString(resId));
    }

    public static void shortT(@StringRes int resId, Object... formatArgs) {
        shortT(ResourceHelper.getString(resId, formatArgs));
    }

    public static void shortT(String msg) {
        doToastShow(msg, Toast.LENGTH_SHORT);
    }

    public static void longT(@StringRes int resId) {
        longT(ResourceHelper.getString(resId));
    }

    public static void longT(@StringRes int resId, Object... formatArgs) {
        longT(ResourceHelper.getString(resId, formatArgs));
    }

    public static void longT(String msg) {
        doToastShow(msg, Toast.LENGTH_LONG);
    }

    private static void doToastShow(final String msg, final int duration) {
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(BTSearcherApplicationContext.context, msg, duration);
                    toast.show();
                } else {
                    toast.setText(msg);
                    toast.setDuration(duration);
                    toast.show();
                }
            }
        });
    }

    public static void shortCenterT(@StringRes int resId) {
        shortCenterT(ResourceHelper.getString(resId));
    }

    public static void shortCenterT(String msg) {
        if (centerToast == null) {
            centerToast = Toast.makeText(BTSearcherApplicationContext.context, msg, Toast.LENGTH_SHORT);
            centerToast.setGravity(Gravity.CENTER, 0, 0);
            centerToast.show();
        } else {
            centerToast.setText(msg);
            centerToast.setGravity(Gravity.CENTER, 0, 0);
            centerToast.show();
        }
    }

}
