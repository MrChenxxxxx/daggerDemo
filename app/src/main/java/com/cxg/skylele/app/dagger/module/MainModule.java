package com.cxg.skylele.app.dagger.module;

import com.cxg.skylele.app.dagger.qulifier.PreActivity;
import com.cxg.skylele.app.dagger.qulifier.WhiteCloth;
import com.cxg.skylele.app.model.Cloth;
import com.cxg.skylele.app.model.Clothes;
import com.cxg.skylele.app.utils.ClothHandler;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */
@Module
public class MainModule {

    @PreActivity
    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    @Named("yellow")
    public Clothes getClothers1() {
        Cloth cloth = new Cloth();
        cloth.setColor("黄色");
        return new Clothes(cloth);
    }

    @Provides
    @Named("red")
    public Clothes getClothers2(Cloth cloth) {
        return new Clothes(cloth);
    }


//    @Provides
//    @WhiteCloth
//    public Clothes getClothes() {
//        Cloth cloth = new Cloth();
//        cloth.setColor("白色");
//        return new Clothes(cloth);
//    }

    @Provides
    public ClothHandler getClothHandler() {
        return new ClothHandler();
    }
}
