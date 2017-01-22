package com.leon.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.leon.loopviewpagerlib.Banner;

public class MainActivity extends AppCompatActivity {

    private String[] images = {"image/home01.jpg","image/home02.jpg","image/home03.jpg","image/home04.jpg","image/home05.jpg","image/home06.jpg","image/home07.jpg","image/home08.jpg"};

    private Banner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBanner = (Banner) findViewById(R.id.loop_view);
        mBanner.setImageUrlHost("http://10.0.2.2:8080/GooglePlayServer/image?name=");
        mBanner.setImageUrls(images);
    }
}
