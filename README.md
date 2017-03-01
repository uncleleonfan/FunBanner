# 简介 #
本项目是一个简单的轮播图，提供了许多属性的配置，简单易用。
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
	    compile 'com.github.uncleleonfan:FunBanner:1.1.0'
	}

## 通过xml配置 ##

    <com.leon.loopviewpagerlib.FunBanner
        android:id="@+id/loop_view"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        app:enable_auto_loop="true"
        app:dot_normal_color="@android:color/white"
        app:dot_selected_color="@color/colorPrimary"
        app:indicator_padding="20dp"
        app:indicator_background_color="#30000000"
        app:show_indicator="true"
        app:height_width_ratio="0.37777">
    </com.leon.loopviewpagerlib.FunBanner>

## 通过代码配置 ##

    private void init() {
        FunBanner.Builder builder = new FunBanner.Builder(this);
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)
                .setDotSelectedColor(Color.GREEN)
                .setHeightWidthRatio(0.5556f)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .setPadding(getResources().getDimensionPixelSize(R.dimen.padding))
                .build();
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