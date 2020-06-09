[![](https://travis-ci.org/YummyLau/PanelSwitchHelper.svg?branch=master)](https://travis-ci.org/YummyLau/panelSwitchHelper)
![Language](https://img.shields.io/badge/language-java-orange.svg)
![Language](https://img.shields.io/badge/language-kotlin-orange.svg)
![Version](https://img.shields.io/badge/version-1.3.0-blue.svg)
![Size](https://img.shields.io/badge/size-14K-brightgreen.svg)

README: [中文文档](https://github.com/YummyLau/PanelSwitchHelper/blob/master/README-zh.md)

### Introduction to the framework

When developing a chat/video/live/information interface, users are expected to maintain a smooth transition between the input method and the function panel (such as the expression panel/more options panel, etc.). Investigate the mainstream app effects and implementation in the market, and realize a set of input panel switching framework compatible with multiple scenes. Currently the framework has been tested and used.

### Framework advantages

* Improve the traditional technical solution of using `Weight+LinearLayout` to dynamically change the layout height to suit the panel, and support multiple native ViewGroup containers
* In pursuit of a smoother adaptation effect, when the input method dynamically adjusts the height or dynamically hides the navigation bar, the function panel can be adapted in real time
* In pursuit of a smoother switching effect, the sliding mode is supported, the sliding will be smoother, and the fixed mode is also supported
* Rich model adaptation, adapt to non-conventional Phone models such as full screen/bang screen/digging screen/Pad
* Rich scene support, support Activity/Fragment/Dialog/PopupWindow, apply to chat/video/live broadcast/stream comment, etc.
* Rich API support, customizable content container, business panel, flexible control panel hiding, flexible control of switching panel speed

For more details, please refer to

 * [Introduction to scenario usage](https://github.com/YummyLau/PanelSwitchHelper/blob/master/README_SENCE.md)
 * [API Usage Guide](https://github.com/YummyLau/PanelSwitchHelper/blob/master/README_API.md)

Demo content is as follows

<img src="https://github.com/YummyLau/PanelSwitchHelper/blob/master/source/demo_1.jpeg" width = "360" height = "790"/> <img src="https://github.com/YummyLau/PanelSwitchHelper/blob/master/source/demo_2.jpeg" width = "360" height = "790"/>

### Instructions

1. Add library dependencies in module script `build.gradle`

```
implementation'com.effective.android:panelSwitchHelper:1.3.0'
```

2. Use the container provided by the framework in the layout file Xml

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

		 <!-- Layout that does not need to be processed by the frame, can be arranged freely -->
        <RelativeLayout
            android:id="@+id/cus_title_bar"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="@color/colorPrimary"
            android:visibility="gone">

            <TextView
                android:id="@+id/title"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="left|center_vertical"
                android:paddingLeft="20dp"
                android:text="自定义标题栏"
                android:textColor="@android:color/white"
                android:textSize="20sp"
                android:textStyle="bold" />
        </RelativeLayout>

        <com.effective.android.panel.view.PanelSwitchLayout
            android:id="@+id/panel_switch_layout"
            android:layout_width="match_parent"
            app:animationSpeed="standard"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <!-- ContentContainer -->
            <!-- linear_edit_view, Specify an EditText for input, required-->
            <!-- linear_auto_reset_enable,Specifies whether the LinearContentContainer can accept Touch events and automatically hide the panel-->
            <!-- linear_auto_reset_area, Specifies whether the LinearContentContainer only accepts Touch events in a View area to customize the hidden panel-->
            <com.effective.android.panel.view.content.LinearContentContainer
                android:id="@+id/content_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                app:linear_edit_view="@id/edit_text">

                <com.example.demo.scene.chat.view.HookActionUpRecyclerView
                    android:id="@+id/recycler_view"
                    android:layout_width="match_parent"
                    android:layout_height="0dp"
                    android:layout_weight="1"/>

                <LinearLayout
                    android:id="@+id/bottom_action"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@drawable/shape_input_layout"
                    android:gravity="bottom"
                    android:minHeight="@dimen/dp_50"
                    android:orientation="horizontal"
                    android:paddingLeft="@dimen/dp_10"
                    android:paddingRight="@dimen/dp_10"
                    android:paddingBottom="@dimen/dp_7.5">

                    <!--More entrances -->
                    <ImageView
                        android:id="@+id/add_btn"
                        android:layout_width="@dimen/dp_35"
                        android:layout_height="@dimen/dp_35"
                        android:layout_marginRight="@dimen/dp_10"
                        android:src="@drawable/icon_add" />

                    <!-- Input entrances -->
                    <EditText
                        android:id="@+id/edit_text"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_marginEnd="@dimen/dp_10"
                        android:layout_marginRight="@dimen/dp_10"
                        android:layout_weight="1"
                        android:background="@drawable/selector_edit_focus"
                        android:imeOptions="actionSearch"
                        android:maxLines="5"
                        android:minHeight="@dimen/dp_35"
                        android:paddingLeft="@dimen/dp_3"
                        android:paddingTop="@dimen/dp_7.5"
                        android:paddingRight="@dimen/dp_3"
                        android:paddingBottom="@dimen/dp_3"
                        android:textCursorDrawable="@drawable/shape_edit_cursor"
                        android:textSize="@dimen/sp_16" />

                    <LinearLayout
                        android:layout_width="wrap_content"
                        android:layout_height="@dimen/dp_35"
                        android:orientation="horizontal">

                        <!-- Emotion entrances -->
                        <ImageView
                            android:id="@+id/emotion_btn"
                            android:layout_width="@dimen/dp_35"
                            android:layout_height="@dimen/dp_35"
                            android:layout_marginEnd="@dimen/dp_10"
                            android:layout_marginRight="@dimen/dp_10"
                            android:src="@drawable/selector_emotion_btn" />

                        <TextView
                            android:id="@+id/send"
                            android:layout_width="@dimen/dp_50"
                            android:layout_height="@dimen/dp_35"
                            android:background="@drawable/selector_send_btn"
                            android:gravity="center"
                            android:text="@string/send"
                            android:textColor="@color/color_send_btn"
                            android:textSize="@dimen/sp_15" />
                    </LinearLayout>

                </LinearLayout>

            </com.effective.android.panel.view.content.LinearContentContainer>


            <!--Panel Container, can only contain PanelView-->
            <com.effective.android.panel.view.panel.PanelContainer
                android:id="@+id/panel_container"
                android:layout_width="match_parent"
                android:background="@color/common_page_bg_color"
                android:layout_height="wrap_content">

                <!-- Each panel -->
                <!-- panel_layout,Used to specify the layout corresponding to the ID of the panel, required-->
                <!-- panel_trigger, Used to switch to this panel when the user clicks the View corresponding to this ID -->
                <!-- panel_toggle ,When the panel is displayed, if the user clicks the View corresponding to panel_trigger again, whether to switch back to the input method-->
                <com.effective.android.panel.view.panel.PanelView
                    android:id="@+id/panel_emotion"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    app:panel_layout="@layout/panel_emotion_layout"
                    app:panel_trigger="@id/emotion_btn" />

                <!-- In addition to using the PanelView provided by the framework, you can also use a custom Panel -->
                <com.example.demo.scene.api.CusPanelView
                    android:id="@+id/panel_addition"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    app:cus_panel_trigger="@id/add_btn"
                    app:cus_panel_toggle="true"/>

            </com.effective.android.panel.view.panel.PanelContainer>
        </com.effective.android.panel.view.PanelSwitchLayout>

    </LinearLayout>
</layout>
```


3. Initialize the PanelSwitchHelper object, and the framework will automatically collect layout information. At the same time, intercept the processing when the return key is adjusted.

```
	//Activity scene, initialized in onStart method, others such as Fragment/Dialog/PopupWindow refer to Demo
   private PanelSwitchHelper mHelper;

   @Override
   protected void onStart() {
        super.onStart();
        if (mHelper == null) {
            mHelper = new PanelSwitchHelper.Builder(this)
                   .addKeyboardStateListener {
                        onKeyboardChange {
                            //Optional implementation, monitor input method changes
                        }
                    }
                    .addEditTextFocusChangeListener {
                        onFocusChange { _, hasFocus ->
							 //Optional implementation, monitor input box focus changes
                        }
                    }
                    .addViewClickListener {
                        onClickBefore {
 							//Optional implementation, listen for trigger clicks
                        }
                    }
                    .addPanelChangeListener {
                        onKeyboard {
 								//Optional implementation, input method display callback
                        }
                        onNone {
 								//Optional implementation, default state callback
                        }
                        onPanel {
 								//Optional implementation, panel display callback
                        }
                        onPanelSizeChange { panelView, _, _, _, width, height ->
 								//Optional implementation, dynamic callback of panel height change caused by input method dynamic adjustment
                        }
                    }
                    .contentCanScrollOutside(true)    //Optional mode, default true, whether the content area slides up when the panel is implemented
                    .logTrack(true)                   //Optional, default false, whether to enable log information output
                    .build(true)			          //Optional, default false, whether to open the input method by default
        }
    }


   @Override
   public void onBackPressed() {
   		 //When the user presses the return key, if the panel is displayed, it needs to be hidden
        if (mHelper != null && mHelper.hookSystemBackForHindPanel()) {
            return;
        }
        super.onBackPressed();
   }

```

### new version update

* 1.0.1(2019-07-08) Support compatible AndroidQ+ focus conflict, support video mode
* 1.0.2(2019-11-05) Support WeChat-style sliding list. Collapse the panel while the list responds to sliding events, improving the user experience
* 1.0.3(2019-11-06) Fix [issue](https://github.com/YummyLau/PanelSwitchHelper/issues/10) scene issue
* 1.0.4(2019-11-18) Added support for Dialog/Fragment/DialogFragment
* 1.0.5(2019-11-26) Supports special models such as Huawei/Xiaomi that support dynamic navigation bar hiding
* 1.1.0(2020-03-18) Pursuing the ultimate switching experience
    * Supports animation blessing in the switching process, the effect is synchronized with the "WeChat chat" scene, but the supported scenes are far more than these (see Demo), and support custom animation speed
    * Optimize the internal switching process of the framework, abandon the old logic implementation, and the new implementation uses custom drawing to switch the interface without worrying about memory leaks
    * Demo adds a custom title bar scene to optimize the video scene experience
* 1.1.1(2020-03-29) Supports special scenes such as full screen/bang screen/drop screen
    * Optimize internal calculation of effective area height, compatible with special scenes
    * Eliminate the call to bindPanelSwitchLayout api, and customize the logic inside the framework
    * Demo adds complex IM interaction scenarios
* 1.1.2(2020-04-20) Added content sliding mode/content fixed mode dynamic switching api
    * Optimize hidden panel logic to improve animation fluency
    * Added content sliding mode, the content area can dynamically slide to the outside of the interface, class adjustPan, enabled by default
    * Added content fixed mode, content area dynamically adjust drawing area, class adjustResize
    * Solve the problem of being slid outside due to too little content in IM scenarios, support dynamic switching mode, optimize experience
* 1.1.3(2020-04-27) Compatible with the use requirements of Google channel non-public SDK-API, optimize the fixed mode drawing implementation
* 1.2.0(2020-05-08) kotlin version/new content area container that supports multiple layout types
    * Panel is adjusted to kotlin language implementation, fully compatible with existing functions and Java, and supports DSL
    * Added content area container, default provides linear/relative/frame layout, supports custom content area container
    * Demo adds kotlin to use constraint layout to implement custom containers, and adds 4 different layout container scenarios
* 1.2.2(2020-05-17) Fix known defects and optimize
    * Merged pr to fix emptyView reference error
    * Optimize the smoothness of switching in fixed mode
* 1.2.3(2020-05-24) compatible with android pad models
* 1.3.0(2020-06-07) Support auto hide panel, open custom panel, optimization adjustment
    * Added auto_reset_enable and auto_reset_area api to open auto hide panel and abandon EmptyView
    * Add IPanelView interface, externally can realize PanelView independently, more flexible
    * Optimized animation in sliding mode

    `1.3.0` is an important version, it is recommended to upgrade, and pay attention to the following Api changes
    
    * EmptyView removed, please refer to Demo How to realize hidden panel more elegantly
    * Panel class specification naming, with native Linear/Relative/Frame as a prefix, it is easier to distinguish
    * PanelView migrated to panel package


### Expectations

The project was written only to improve the efficiency of daily development and focus on processing business. If you have better practices or suggestions, please write to yummyl.lau@gmail.com.

You can also add WeChat and enter the feedback group to discuss feedback.

<img src = "https://github.com/YummyLau/PanelSwitchHelper/blob/master/source/qr_code.jpg" width = "385" height = "385" />

If the framework is helpful to you, Amway can give the partners around you, and every star is an affirmation of the framework.
