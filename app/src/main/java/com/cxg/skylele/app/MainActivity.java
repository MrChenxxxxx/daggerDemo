package com.cxg.skylele.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxg.skylele.app.dagger.componen.DaggerMainComponen;
import com.cxg.skylele.app.dagger.componen.MainComponen;
import com.cxg.skylele.app.dagger.module.MainModule;
import com.cxg.skylele.app.dagger.qulifier.WhiteCloth;
import com.cxg.skylele.app.model.Cloth;
import com.cxg.skylele.app.model.Clothes;
import com.cxg.skylele.app.model.Shoe;
import com.cxg.skylele.app.utils.ThirdActiviy;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends Activity {

    @Inject
    Cloth cloth;

    @Inject
    Shoe shoe;

    @Inject
    @Named("yellow")
    Clothes yellowClothes;
    @Inject
    @Named("red")
    Clothes redClothes;
//    @Inject
//    @WhiteCloth
//    Clothes whiteClothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvMessage = (TextView) findViewById(R.id.tv_message);
        MainComponen build = DaggerMainComponen.builder().mainModule(new MainModule()).build();
        build.inject(this);
//        tvMessage.setText("我现在有" + cloth + "和" + shoe + "和" + yellowClothes+ "和"+ redClothes+ "和" + whiteClothes);
        tvMessage.setText("redCloth=clothes中的cloth吗?:" + (cloth == redClothes.getCloth()));
//        ((TextView) findViewById(R.id.tv_message1)).setText("redCloth=yellowClothes中的cloth吗?:" + (cloth == yellowClothes.getCloth()));
        TextView textView = (TextView) findViewById(R.id.tv_message1);
        textView.setText(redClothes.toString());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThirdActiviy.class));
            }
        });
        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
