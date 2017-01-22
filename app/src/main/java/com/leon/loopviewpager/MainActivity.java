package com.leon.loopviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leon.loopviewpagerlib.LoopView;

public class MainActivity extends AppCompatActivity {

    private String[] images = {"image/home01.jpg","image/home02.jpg","image/home03.jpg","image/home04.jpg","image/home05.jpg","image/home06.jpg","image/home07.jpg","image/home08.jpg"};

    private LoopView mLoopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoopView = (LoopView) findViewById(R.id.loop_view);
        mLoopView.setImageUrlHost("http://10.0.2.2:8080/GooglePlayServer/image?name=");
        mLoopView.setImageUrls(images);

    }
}
