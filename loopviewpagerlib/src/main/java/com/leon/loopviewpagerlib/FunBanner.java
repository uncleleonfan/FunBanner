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
    private static final String TAG = "FunBanner";
    LoopViewPager mVp;
    CirclePageIndicator mCirclePageIndicator;
    FunBannerParams mFunBannerParams = new FunBannerParams();

    private static class FunBannerParams {
        private boolean mEnableAutoLoop;
        private int mLoopInterval = 3000;
        private int mDotRadius = 0;
        private List<String> mImageUrls;
        private String mHost = "";
        private int[] mImagesResIds;
        private int mDotNormalColor;
        private int mDotSelectedColor;
        private boolean mShowIndicator = true;
        private int mPadding = 0;
        private int mIndicatorBackgroundColor;
        private float mHeightWidthRatio;

        public void apply(FunBanner funBanner) {
            funBanner.mFunBannerParams.mEnableAutoLoop = this.mEnableAutoLoop;
            funBanner.mFunBannerParams.mLoopInterval = this.mLoopInterval;
            if (this.mDotRadius > 0) {
                funBanner.mFunBannerParams.mDotRadius = this.mDotRadius;
            }
            if (this.mImageUrls != null) {
                funBanner.mFunBannerParams.mImageUrls = this.mImageUrls;
            }
            if (this.mHost.length() > 0) {
                funBanner.mFunBannerParams.mHost = this.mHost;
            }
            if (this.mImagesResIds != null) {
                funBanner.mFunBannerParams.mImagesResIds = this.mImagesResIds;
            }
            if (this.mDotNormalColor != 0) {
                funBanner.mFunBannerParams.mDotNormalColor = this.mDotNormalColor;
            }
            if (this.mDotSelectedColor != 0) {
                funBanner.mFunBannerParams.mDotSelectedColor = this.mDotSelectedColor;
            }
            funBanner.mFunBannerParams.mShowIndicator = this.mShowIndicator;
            if (this.mPadding > 0 ) {
                funBanner.mFunBannerParams.mPadding = this.mPadding;
            }
            if (this.mIndicatorBackgroundColor != 0) {
                funBanner.mFunBannerParams.mIndicatorBackgroundColor = this.mIndicatorBackgroundColor;
            }
            if (this.mHeightWidthRatio != 0) {
                funBanner.mFunBannerParams.mHeightWidthRatio = this.mHeightWidthRatio;
            }
        }
    }


    public FunBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoopViewStyle);
        mFunBannerParams.mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        mFunBannerParams.mDotRadius = a.getDimensionPixelSize(R.styleable.LoopViewStyle_dot_radius, mFunBannerParams.mDotRadius);
        mFunBannerParams.mEnableAutoLoop = a.getBoolean(R.styleable.LoopViewStyle_enable_auto_loop, false);
        mFunBannerParams.mLoopInterval = a.getInt(R.styleable.LoopViewStyle_loop_interval, mFunBannerParams.mLoopInterval);
        mFunBannerParams.mShowIndicator = a.getBoolean(R.styleable.LoopViewStyle_show_indicator, true);
        mFunBannerParams.mDotNormalColor = a.getColor(R.styleable.LoopViewStyle_dot_normal_color, Color.WHITE);
        mFunBannerParams.mDotSelectedColor = a.getColor(R.styleable.LoopViewStyle_dot_selected_color, Color.BLUE);
        mFunBannerParams.mIndicatorBackgroundColor = a.getColor(R.styleable.LoopViewStyle_indicator_background_color, Color.TRANSPARENT);
        mFunBannerParams.mHeightWidthRatio = a.getFloat(R.styleable.LoopViewStyle_height_width_ratio, 0);
        mFunBannerParams.mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        mFunBannerParams.mPadding = a.getDimensionPixelSize(R.styleable.LoopViewStyle_indicator_padding, mFunBannerParams.mPadding);
        a.recycle();
        init();
    }

    private FunBanner(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loop, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mFunBannerParams.mHeightWidthRatio > 0) {
            int size = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (size * mFunBannerParams.mHeightWidthRatio + 0.5);
            int changeHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, changeHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    private void initViewPager() {
        mVp = (LoopViewPager) findViewById(R.id.vp);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mVp.setEnableAutoLoop(mFunBannerParams.mEnableAutoLoop);
        mVp.setLoopInterval(mFunBannerParams.mLoopInterval);
        mVp.setAdapter(mPagerAdapter);
        if (mFunBannerParams.mShowIndicator) {
            mCirclePageIndicator.setViewPager(mVp);
            mCirclePageIndicator.setRadius(mFunBannerParams.mDotRadius);
            mCirclePageIndicator.setPageColor(mFunBannerParams.mDotNormalColor);
            mCirclePageIndicator.setFillColor(mFunBannerParams.mDotSelectedColor);
            mCirclePageIndicator.setPadding(mFunBannerParams.mPadding,
                    mFunBannerParams.mPadding,
                    mFunBannerParams.mPadding,
                    mFunBannerParams.mPadding);
            mCirclePageIndicator.setBackgroundColor(mFunBannerParams.mIndicatorBackgroundColor);
        } else {
            mCirclePageIndicator.setVisibility(GONE);
        }

    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (mFunBannerParams.mImageUrls != null) {
                return mFunBannerParams.mImageUrls.size();
            }
            if (mFunBannerParams.mImagesResIds != null) {
                return mFunBannerParams.mImagesResIds.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            if (mFunBannerParams.mImageUrls == null) {
                imageView.setImageResource(mFunBannerParams.mImagesResIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                String url = mFunBannerParams.mHost + mFunBannerParams.mImageUrls.get(position);
                if (mFunBannerParams.mHeightWidthRatio > 0) {
                    Glide.with(getContext()).load(url).into(imageView);
                } else {
                    Glide.with(getContext()).load(url).centerCrop().into(imageView);
                }
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    public void setImageUrls(List<String> data) {
        mFunBannerParams.mImageUrls = data;
        initViewPager();
    }

    public void setImageUrls(String[] data) {
        List<String> list = Arrays.asList(data);
        setImageUrls(list);
    }

    public void setImageUrlHost(String host) {
        mFunBannerParams.mHost = host;
    }

    public void setImageResIds(int[] resIds) {
        mFunBannerParams.mImagesResIds = resIds;
        initViewPager();
    }

    public static class Builder  {

        private final FunBannerParams mFunBannerParams;
        private final Context mContext;

        public Builder(Context context) {
            mFunBannerParams = new FunBannerParams();
            mContext = context;
        }

        public Builder setEnableAutoLoop(boolean enableAutoLoop) {
            mFunBannerParams.mEnableAutoLoop = enableAutoLoop;
            return this;
        }

        public Builder setLoopInterval(int loopInterval) {
            mFunBannerParams.mLoopInterval = loopInterval;
            return this;
        }

        public Builder setDotRadius(int dotRadius) {
            mFunBannerParams.mDotRadius = dotRadius;
            return this;
        }

        public Builder setDotNormalColor(int dotNormalColor) {
            mFunBannerParams.mDotNormalColor = dotNormalColor;
            return this;
        }

        public Builder setDotSelectedColor(int dotSelectedColor) {
            mFunBannerParams.mDotSelectedColor = dotSelectedColor;
            return this;
        }

        public Builder setShowIndicator(boolean showIndicator) {
            mFunBannerParams.mShowIndicator = showIndicator;
            return this;
        }

        public Builder setPadding(int padding) {
            mFunBannerParams.mPadding = padding;
            return this;
        }


        public Builder setIndicatorBackgroundColor(int indicatorBackgroundColor) {
            mFunBannerParams.mIndicatorBackgroundColor = indicatorBackgroundColor;
            return this;
        }

        public Builder setHeightWidthRatio(float ratio) {
            mFunBannerParams.mHeightWidthRatio = ratio;
            return this;
        }

        public Builder setImageResIds(int[] resIds) {
            mFunBannerParams.mImagesResIds = resIds;
            return this;
        }

        public Builder setImageUrlHost(String host) {
            mFunBannerParams.mHost = host;
            return this;
        }

        public Builder setImageUrls(List<String> data) {
            mFunBannerParams.mImageUrls = data;
            return this;
        }

        public Builder setImageUrls(String[] data) {
            List<String> list = Arrays.asList(data);
            setImageUrls(list);
            return this;
        }

        public FunBanner build() {
            FunBanner funBanner = new FunBanner(mContext);
            mFunBannerParams.apply(funBanner);
            funBanner.initViewPager();
            return funBanner;
        }
    }

}
