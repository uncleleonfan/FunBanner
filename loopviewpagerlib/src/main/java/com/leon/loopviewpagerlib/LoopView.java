package com.leon.loopviewpagerlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/16 12:03
 * 描述： TODO
 */
public class LoopView extends RelativeLayout {

    private boolean mEnableAutoLoop;
    private int mLoopInterval = 3000;

    LoopViewPager mVp;

    CirclePageIndicator mCirclePageIndicator;

    private int mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
    private List<String> mImages;
    private String mHost = "";


    private int mDotNormalColor;
    private int mDotSelectedColor;


    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoopViewStyle);
        mDotRadius = a.getDimensionPixelSize(R.styleable.LoopViewStyle_dot_radius, mDotRadius);
        mEnableAutoLoop = a.getBoolean(R.styleable.LoopViewStyle_enable_auto_loop, false);
        mLoopInterval = a.getInt(R.styleable.LoopViewStyle_loop_interval, mLoopInterval);

        mDotNormalColor = a.getColor(R.styleable.LoopViewStyle_dot_normal_color, Color.WHITE);
        mDotSelectedColor = a.getColor(R.styleable.LoopViewStyle_dot_selected_color, Color.BLUE);
        a.recycle();
        init();
    }

    public LoopView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loop, this);
        mVp = (LoopViewPager) findViewById(R.id.vp);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }

    private void initViewPager() {
        mVp.setEnableAutoLoop(mEnableAutoLoop);
        mVp.setLoopInterval(mLoopInterval);
        mVp.setAdapter(mPagerAdapter);
        mCirclePageIndicator.setViewPager(mVp);
        mCirclePageIndicator.setRadius(mDotRadius);
        mCirclePageIndicator.setPageColor(mDotNormalColor);
        mCirclePageIndicator.setFillColor(mDotSelectedColor);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (mImages == null) {
                return 0;
            }
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            String url = mHost + mImages.get(position);
            Glide.with(getContext()).load(url).centerCrop().into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    };


    public void setImageUrls(List<String> data) {
        mImages = data;
        initViewPager();
    }

    public void setImageUrls(String[] data) {
        List<String> list = Arrays.asList(data);
        setImageUrls(list);
    }

    public void setImageUrlHost(String host) {
        mHost = host;
    }


}
