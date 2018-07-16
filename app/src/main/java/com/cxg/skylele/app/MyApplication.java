package com.cxg.skylele.app;

import android.app.Application;

import com.cxg.skylele.app.dagger.componen.BaseComponent;
import com.cxg.skylele.app.dagger.componen.DaggerBaseComponent;
import com.cxg.skylele.app.dagger.module.BaseModule;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

public class MyApplication extends Application {
    private BaseComponent baseComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        baseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule()).build();
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }
}
