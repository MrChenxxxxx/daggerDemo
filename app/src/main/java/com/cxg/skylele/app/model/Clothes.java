package com.cxg.skylele.app.model;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

public class Clothes {
    private Cloth cloth;

    public Clothes(Cloth cloth){
        this.cloth = cloth;
    }

    public Cloth getCloth() {
        return cloth;
    }

    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }
}
