package com.huazidev.btsearcher.common;

import android.app.Application;
import android.os.Handler;

/**
 * @author hua on 2017/8/21.
 */
public class BTSearcherApplication extends Application{

    public static volatile Handler applicationHandler;
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        applicationHandler = new Handler(application.getMainLooper());
        BTSearcherApplicationContext.application = application;
        BTSearcherApplicationContext.context = application;
        LogHelper.init();
    }
}
