package com.cxg.skylele.app.dagger.module;

import com.cxg.skylele.app.dagger.qulifier.PreActivity;
import com.cxg.skylele.app.model.Cloth;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
@Module
public class ThirdModule {
    @PreActivity
    @Provides
    public Cloth getBlackCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("黑色");
        return cloth;
    }


}
