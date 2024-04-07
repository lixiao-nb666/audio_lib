package com.newbee.audio_luminance_lib.service;


import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

import com.newbee.audio_luminance_lib.R;

import com.newbee.audio_luminance_lib.service.float_window.LuminanceNumbShowFloatView;
import com.newbee.bulid_lib.mybase.service.floatwindow.BaseFloatView;
import com.newbee.bulid_lib.mybase.service.floatwindow.BaseFloatWindowService;


public class LuminanceNumbShowFloatWindowService extends BaseFloatWindowService {
    private LuminanceNumbShowFloatView luminanceNumbShowFloatView;
    private BaseFloatView.Listen listen=new BaseFloatView.Listen() {
        @Override
        public void initView(View view, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {

        }
    };

    @Override
    protected void init(WindowManager windowManager) {
        luminanceNumbShowFloatView=new LuminanceNumbShowFloatView(this,windowManager,listen, R.layout.float_view_show_luminance_numb);
    }

    private LuminanceEventObserver luminanceEventObserver=new LuminanceEventObserver() {
        @Override
        public void valueChange() {
            setShowLNumb();
        }
    };

    @Override
    protected BaseFloatView getBaseFloatView() {
        return luminanceNumbShowFloatView;
    }

    private Handler controlHandler=new Handler();
    private Runnable hideRunnable=new Runnable() {
        @Override
        public void run() {
            controlHandler.removeCallbacksAndMessages(null);
            LuminanceNumbShowFloatWindowServiceManager.getInstance().hideView();
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        LuminanceEventSubscriptionSubject.getInstence().attach(luminanceEventObserver);
    }



    @Override
    public IBinder onBind(Intent intent) {
        setShowLNumb();
        return super.onBind(intent);
    }

    private void setShowLNumb(){
        controlHandler.removeCallbacksAndMessages(null);
        controlHandler.postDelayed(hideRunnable,3000);
        luminanceNumbShowFloatView.setShowLNumbIV();
    }



    @Override
    public void close() {
        super.close();
        LuminanceEventSubscriptionSubject.getInstence().detach(luminanceEventObserver);
    }
}
