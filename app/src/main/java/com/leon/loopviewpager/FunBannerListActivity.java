package com.leon.loopviewpager;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.leon.loopviewpagerlib.FunBanner;

/**
 * 作者：Leon
 * 时间：2017/7/16
 * 包名：com.leon.loopviewpager
 * 公司：黑马程序员
 */

public class FunBannerListActivity extends ListActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FunBanner funBanner = new FunBanner.Builder(this).setEnableAutoLoop(true)
                .setImageUrls(ImagesProvider.NETWORK_IMAGES)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .setIndicatorBarHeight(80)
                .setHeight(400)
                .build();
        getListView().addHeaderView(funBanner);

        setListAdapter(mBaseAdapter);
    }

    private BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(FunBannerListActivity.this).inflate(R.layout.list_item, null);
            }
            return convertView;
        }
    };
}
