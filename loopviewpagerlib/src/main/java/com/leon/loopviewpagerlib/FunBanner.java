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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class FunBanner extends FrameLayout {
    private static final String TAG = "Banner";


    private boolean mEnableAutoLoop;
    private int mLoopInterval = 3000;

    LoopViewPager mVp;

    CirclePageIndicator mCirclePageIndicator;

    public boolean isEnableAutoLoop() {
        return mEnableAutoLoop;
    }

    public void setEnableAutoLoop(boolean enableAutoLoop) {
        mEnableAutoLoop = enableAutoLoop;
    }

    public int getLoopInterval() {
        return mLoopInterval;
    }

    public void setLoopInterval(int loopInterval) {
        mLoopInterval = loopInterval;
    }

    public int getDotRadius() {
        return mDotRadius;
    }

    public void setDotRadius(int dotRadius) {
        mDotRadius = dotRadius;
    }

    public int getDotNormalColor() {
        return mDotNormalColor;
    }

    public void setDotNormalColor(int dotNormalColor) {
        mDotNormalColor = dotNormalColor;
    }

    public int getDotSelectedColor() {
        return mDotSelectedColor;
    }

    public void setDotSelectedColor(int dotSelectedColor) {
        mDotSelectedColor = dotSelectedColor;
    }

    public boolean isShowIndicator() {
        return mShowIndicator;
    }

    public void setShowIndicator(boolean showIndicator) {
        mShowIndicator = showIndicator;
    }

    public int getPadding() {
        return mPadding;
    }

    public void setPadding(int padding) {
        mPadding = padding;
    }

    public int getIndicatorBackgroundColor() {
        return mIndicatorBackgroundColor;
    }

    public void setIndicatorBackgroundColor(int indicatorBackgroundColor) {
        mIndicatorBackgroundColor = indicatorBackgroundColor;
    }

    public void setRatio(float ratio) {
        mRatio = ratio;
    }

    private int mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
    private List<String> mImages;
    private String mHost = "";


    private int mDotNormalColor;
    private int mDotSelectedColor;

    private boolean mShowIndicator = true;
    private int mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
    private int mIndicatorBackgroundColor;
    private float mRatio;


    public FunBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoopViewStyle);
        mDotRadius = a.getDimensionPixelSize(R.styleable.LoopViewStyle_dot_radius, mDotRadius);
        mEnableAutoLoop = a.getBoolean(R.styleable.LoopViewStyle_enable_auto_loop, false);
        mLoopInterval = a.getInt(R.styleable.LoopViewStyle_loop_interval, mLoopInterval);
        mShowIndicator = a.getBoolean(R.styleable.LoopViewStyle_show_indicator, true);
        mDotNormalColor = a.getColor(R.styleable.LoopViewStyle_dot_normal_color, Color.WHITE);
        mDotSelectedColor = a.getColor(R.styleable.LoopViewStyle_dot_selected_color, Color.BLUE);
        mIndicatorBackgroundColor = a.getColor(R.styleable.LoopViewStyle_indicator_background_color, Color.TRANSPARENT);
        mRatio = a.getFloat(R.styleable.LoopViewStyle_ratio, 0);
        mPadding = a.getDimensionPixelSize(R.styleable.LoopViewStyle_indicator_padding, mPadding);
        a.recycle();
        init();
    }

    public FunBanner(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loop, this);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRatio > 0) {
            int size = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (size * mRatio + 0.5);
            int changeHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, changeHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    private void initViewPager() {
        mVp = (LoopViewPager) findViewById(R.id.vp);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mVp.setEnableAutoLoop(mEnableAutoLoop);
        mVp.setLoopInterval(mLoopInterval);
        mVp.setAdapter(mPagerAdapter);
        if (mShowIndicator) {
            mCirclePageIndicator.setViewPager(mVp);
            mCirclePageIndicator.setRadius(mDotRadius);
            mCirclePageIndicator.setPageColor(mDotNormalColor);
            mCirclePageIndicator.setFillColor(mDotSelectedColor);
            mCirclePageIndicator.setPadding(mPadding, mPadding, mPadding, mPadding);
            mCirclePageIndicator.setBackgroundColor(mIndicatorBackgroundColor);
        } else {
            mCirclePageIndicator.setVisibility(GONE);
        }

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
            container.addView(imageView);
            if (mRatio > 0) {
                Glide.with(getContext()).load(url).into(imageView);
            } else {
                Glide.with(getContext()).load(url).centerCrop().into(imageView);
            }
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
