package com.cxg.skylele.app.dagger.module;

import com.cxg.skylele.app.dagger.qulifier.PreActivity;
import com.cxg.skylele.app.model.Cloth;
import com.cxg.skylele.app.utils.ClothHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
@Module
public class SecondModule {
    @PreActivity
    @Provides
    public Cloth getBlueCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }

//    @Provides
//    public ClothHandler getClothHandler(){
//        return new ClothHandler();
//    }
}
