package com.cxg.skylele.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import com.cxg.skylele.app.dagger.componen.DaggerSecondComponent;
import com.cxg.skylele.app.dagger.componen.SecondComponent;
import com.cxg.skylele.app.dagger.module.SecondModule;
import com.cxg.skylele.app.model.Cloth;
import com.cxg.skylele.app.model.Clothes;
import com.cxg.skylele.app.utils.ClothHandler;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;


/**
 * Created by cxg on 2018/7/15.
 *
 * @version 1.0.0
 */

public class SecondActivity extends Activity {
    private TextView mTv;

    @Inject
    Cloth cloth;

    @Inject
    ClothHandler clothHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTv = findViewById(R.id.tv);
        SecondComponent build = DaggerSecondComponent.builder().baseComponent(((MyApplication)getApplication()).getBaseComponent()) .secondModule(new SecondModule()).build();
        build.inject(SecondActivity.this);
        Clothes clothes = clothHandler.handle(cloth);
        mTv.setText("蓝布料加工后变成了" + clothHandler.handle(cloth) + "\nclothHandler地址:" + clothHandler+ "\ncloth地址:" + cloth);
    }
}
