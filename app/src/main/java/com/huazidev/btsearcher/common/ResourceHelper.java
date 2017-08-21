package com.huazidev.btsearcher.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

/**
 * @author hua on 2017/8/21.
 */
public final class ResourceHelper {
    private ResourceHelper() {
    }

    private static Context getContext() {
        return BTSearcherApplicationContext.context;
    }

    public static String getString(@StringRes int resId) {
        return getContext().getString(resId);
    }

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return getContext().getString(resId, formatArgs);
    }

    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

}
