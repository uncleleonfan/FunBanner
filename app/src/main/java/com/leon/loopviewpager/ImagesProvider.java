package com.leon.loopviewpager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/7/16
 * 包名：com.leon.loopviewpager
 * 公司：黑马程序员
 */

public class ImagesProvider {

    public static final List<String> NETWORK_IMAGES = new ArrayList<>();

    public static int[] NATIVE_IMAGES = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};


    static {
        NETWORK_IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhj53yz5aoj21hc0xcn41.jpg");
        NETWORK_IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhhz28n9vyj20u00u00w9.jpg");
        NETWORK_IMAGES.add("https://ws1.sinaimg.cn/large/610dc034ly1fhgsi7mqa9j20ku0kuh1r.jpg");
    }
}
