package com.leon.loopviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.leon.loopviewpagerlib.FunBanner;

/**
 * Created by Leon on 2017/3/1.
 */

public class DemoActivity extends AppCompatActivity {

    private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        init();
    }

    private void init() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        FunBanner.Builder builder = new FunBanner.Builder(this);
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)
                .setDotSelectedColor(Color.GREEN)
                .setHeightWidthRatio(0.5556f)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .setPadding(getResources().getDimensionPixelSize(R.dimen.padding))
                .build();
        frameLayout.addView(funBanner);
    }
}
