package com.cxg.skylele.app.dagger.componen;

import com.cxg.skylele.app.dagger.module.BaseModule;
import com.cxg.skylele.app.dagger.qulifier.PreActivity;
import com.cxg.skylele.app.utils.ClothHandler;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

@Singleton
@Component(modules = BaseModule.class)
public interface BaseComponent {
    ClothHandler getClothHandler();
}
