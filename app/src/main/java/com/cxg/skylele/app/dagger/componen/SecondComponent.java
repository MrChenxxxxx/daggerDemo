package com.cxg.skylele.app.dagger.componen;

import com.cxg.skylele.app.SecondActivity;
import com.cxg.skylele.app.dagger.module.SecondModule;
import com.cxg.skylele.app.dagger.qulifier.PreActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
//@Component(modules = SecondModule.class)
@PreActivity
@Component(modules = SecondModule.class, dependencies = BaseComponent.class)
public interface SecondComponent {
    void inject(SecondActivity secondActivity);
}
