package com.huazidev.btsearcher.common;

import android.util.Log;

import com.huazidev.btsearcher.BuildConfig;

import timber.log.Timber;

/**
 * 日志管理
 *
 * @author hua on 2017/8/21.
 */
public final class LogHelper {
    private LogHelper() {
    }

    public static boolean isLog() {
        return BuildConfig.DEBUG;
    }

    public static void init() {
        if (isLog()) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new LogHelper.LogReportingTree());
        }
    }

    private static class LogReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            String log = tag != null ? tag + ": " + message : message;
            if (priority >= Log.WARN) {
                // TODO: 2017/8/21 上报bug
            }
        }
    }
}
