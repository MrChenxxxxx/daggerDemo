package com.cxg.skylele.app.dagger.componen;

import com.cxg.skylele.app.dagger.module.ThirdModule;
import com.cxg.skylele.app.dagger.qulifier.PreActivity;
import com.cxg.skylele.app.utils.ThirdActiviy;

import dagger.Component;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
@PreActivity
@Component(modules = ThirdModule.class,dependencies = BaseComponent.class)
public interface ThirdCompont {
    void inject(ThirdActiviy thirdActiviy);
}
