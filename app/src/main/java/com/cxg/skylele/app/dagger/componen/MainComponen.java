package com.cxg.skylele.app.dagger.componen;

import com.cxg.skylele.app.MainActivity;
import com.cxg.skylele.app.SecondActivity;
import com.cxg.skylele.app.dagger.module.MainModule;
import com.cxg.skylele.app.dagger.module.SecondModule;
import com.cxg.skylele.app.dagger.qulifier.PreActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
@PreActivity
@Component(modules = MainModule.class)
public interface MainComponen {
    void inject(MainActivity mainActivity);
}

