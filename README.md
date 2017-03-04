# 简介 #
本项目是一个轻量级的轮播图，提供了许多属性的配置，其中包括了设置图片宽高比例来调整Banner的宽高以防止图片缩放造成的显示问题，简单易用。
# 使用姿势 #

## 集成 ##
	//项目下的build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	//module下的build.gradle
	dependencies {
	    compile 'com.github.uncleleonfan:FunBanner:1.1.2'
	}

## 通过xml配置 ##

    <com.leon.loopviewpagerlib.FunBanner
        android:id="@+id/loop_view"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        app:dot_radius="3dp"
        app:dot_normal_color="@android:color/white"
        app:dot_selected_color="@color/colorPrimary"
        app:enable_auto_loop="true"
        app:loop_interval="5000"
        app:show_indicator="true"
        app:indicator_bar_height="40dp"
        app:indicator_bar_background_color="#30000000"
        app:height_width_ratio="0.37777"
        app:title_color="@android:color/white">
    </com.leon.loopviewpagerlib.FunBanner>

>可配置属性

* dot_radius 点的半径
* dot_normal_color 点的正常颜色
* dot_selected_color 点被选中时的颜色
* enable_auto_loop 是否开启自动轮播
* loop_interval 自动轮播的时间间隔
* show_indicator 是否显示指示器
* indicator_bar_height 指示器的高度
* indicator_bar_background_color 指示器的背景颜色
* height_width_ratio 显示图片的高宽比
* height_width_ratio 标题的颜色，如果没有设置标题数据，则不会显示标题

## 通过代码配置 ##

    private void init() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        FunBanner.Builder builder = new FunBanner.Builder(this);
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)
                .setDotSelectedColor(Color.GREEN)
                .setHeightWidthRatio(0.5556f)
                .setLoopInterval(5000)
                .setEnableAutoLoop(true)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
        frameLayout.addView(funBanner);
    }

# License #
	Copyright (c) 2017 uncleleonfan.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.