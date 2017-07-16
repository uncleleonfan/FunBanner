package com.leon.loopviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leon.loopviewpagerlib.FunBanner;

/**
 * 作者：Leon
 * 时间：2017/7/16
 * 包名：com.leon.loopviewpager
 * 公司：黑马程序员
 */

public class NetworkActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        FunBanner funBanner = (FunBanner) findViewById(R.id.fun_banner);
        funBanner.setImageUrls(ImagesProvider.NETWORK_IMAGES);
    }
}
