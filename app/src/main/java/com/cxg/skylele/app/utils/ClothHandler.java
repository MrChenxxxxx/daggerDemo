package com.cxg.skylele.app.utils;

import com.cxg.skylele.app.model.Cloth;
import com.cxg.skylele.app.model.Clothes;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

public class ClothHandler {
    public Clothes handle(Cloth cloth) {
        return new Clothes(cloth);
    }
}
