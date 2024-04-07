package com.newbee.audio_luminance_lib.luminance;

import android.content.Context;


public class NewBeeSystemLuminanceUtil {
    private static NewBeeSystemLuminanceUtil newBeeSystemLuminanceUtil;
   private final int maxLuminanceSystemValue=255;
   private int maxLevel=7;

   private NewBeeSystemLuminanceUtil(){

   }

   public static NewBeeSystemLuminanceUtil getInstance(){
       if(null==newBeeSystemLuminanceUtil){
           synchronized (NewBeeSystemLuminanceUtil.class){
               if(null==newBeeSystemLuminanceUtil){
                   newBeeSystemLuminanceUtil=new NewBeeSystemLuminanceUtil();
               }
           }
       }
        return newBeeSystemLuminanceUtil;
   }

   public void setMaxLevel(int setMaxLevel){
       if(setMaxLevel<1||setMaxLevel>100){
           return;
       }
       this.maxLevel=setMaxLevel;
   }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getValue(Context context){
       try {
           int luminance= android.provider.Settings.System.getInt(context.getContentResolver(),
                   android.provider.Settings.System.SCREEN_BRIGHTNESS);
           int value=luminance/(maxLuminanceSystemValue/ maxLevel);
           return value;
       }catch (Exception e){
           return 0;
       }

   }


   public void setValue(int screenLuminanceNumb , Context context){
       if(screenLuminanceNumb<0){
           screenLuminanceNumb=0;
       }else if(screenLuminanceNumb>maxLevel){
           screenLuminanceNumb=maxLevel;
       }
       int luminance;
       if(screenLuminanceNumb>=maxLevel){
           luminance=maxLuminanceSystemValue;
       }else {
           luminance= maxLuminanceSystemValue/maxLevel * screenLuminanceNumb ;
       }
       android.provider.Settings.System.putInt(context.getContentResolver(),
               android.provider.Settings.System.SCREEN_BRIGHTNESS,
               luminance);
   }

   public boolean valueUp(Context context){
       int luminanceNumb=getValue(context);
       if(luminanceNumb<maxLevel){
           luminanceNumb++;
           setValue(luminanceNumb,context);
           return true;
       }
       return false;
   }


   public boolean valueDown(Context context){
       int luminanceNumb=getValue(context);
       if(luminanceNumb>0){
           luminanceNumb--;
           setValue(luminanceNumb,context);
           return true;
       }
       return false;
   }


   public boolean valueLoop(Context context){
       int luminanceNumb=getValue(context);
       if(luminanceNumb<maxLevel){
           luminanceNumb++;
           setValue(luminanceNumb,context);
       }else {
           luminanceNumb=0;
           setValue(luminanceNumb,context);
       }
       return true;
   }

}
