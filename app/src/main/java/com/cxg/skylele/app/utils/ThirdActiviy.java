package com.cxg.skylele.app.utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cxg.skylele.app.MyApplication;
import com.cxg.skylele.app.R;
import com.cxg.skylele.app.dagger.componen.DaggerThirdCompont;
import com.cxg.skylele.app.dagger.componen.ThirdCompont;
import com.cxg.skylele.app.dagger.module.ThirdModule;
import com.cxg.skylele.app.model.Cloth;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

public class ThirdActiviy extends Activity {
    @Inject
    Cloth cloth;
    @Inject
    ClothHandler clothHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = (TextView) findViewById(R.id.tv);
        ThirdCompont build = DaggerThirdCompont.builder().baseComponent(((MyApplication) getApplication()).getBaseComponent()).thirdModule(new ThirdModule()).build();
        build.inject(this);
        tv.setText(clothHandler.handle(cloth).toString()+" handleCloth地址： " + clothHandler);
    }
}
