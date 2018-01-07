package com.huazidev.btsearcher.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * @author hua on 2018/1/7.
 */
public final class GlideHepler {

    // 避免activity被销毁后，避免出现异常
    public static GlideRequests with(Context context) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return GlideApp.with(context.getApplicationContext());
        }
        return GlideApp.with(context);
    }

    public static GlideRequest<Drawable> request(ImageView target, Object model, @DrawableRes int placeholder) {
        return with(target.getContext())
                .load(model)
                .placeholder(placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop();
    }

    public static void loadImage(ImageView target, Object model) {
        loadImage(target, model, 0);
    }

    public static void loadImage(ImageView target, Object model, @DrawableRes int placeholder) {
        request(target, model, placeholder)
                .into(target);
    }

}
