package com.leon.loopviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leon.loopviewpagerlib.FunBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/7/16
 * 包名：com.leon.loopviewpager
 * 公司：黑马程序员
 */

public class NetworkActivity extends AppCompatActivity{

    private static final List<String> IMAGES = new ArrayList<>();

    static {
        IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhj53yz5aoj21hc0xcn41.jpg");
        IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhhz28n9vyj20u00u00w9.jpg");
        IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhgsi7mqa9j20ku0kuh1r.jpg");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        FunBanner funBanner = (FunBanner) findViewById(R.id.fun_banner);
        funBanner.setImageUrls(IMAGES);
    }
}
