package com.newbee.audio_luminance_lib.service;

import android.content.Context;


public class LuminanceNumbShowFloatWindowServiceManager  {
    private static LuminanceNumbShowFloatWindowServiceManager manager;
    private LuminanceNumbShowFloatWindowServiceDao luminanceNumbShowFloatWindowServiceDao;
    private LuminanceNumbShowFloatWindowServiceManager(){

    }

    public static LuminanceNumbShowFloatWindowServiceManager getInstance(){
        if(null==manager){
            synchronized (LuminanceNumbShowFloatWindowServiceManager.class){
                if(null==manager){
                    manager=new LuminanceNumbShowFloatWindowServiceManager();
                }
            }
        }
        return manager;
    }

    public void initService(Context context){
        luminanceNumbShowFloatWindowServiceDao=new LuminanceNumbShowFloatWindowServiceDao(context);
    }

    public void startShowView(Context context){
        if(null==luminanceNumbShowFloatWindowServiceDao){
            initService(context);
        }
        luminanceNumbShowFloatWindowServiceDao.startServiceIsBind();
        LuminanceEventSubscriptionSubject.getInstence().valueChange();
    }


    public void hideView(){
        if(null==luminanceNumbShowFloatWindowServiceDao){
            return;
        }
        luminanceNumbShowFloatWindowServiceDao.stopServiceIsBind();
    }
}
