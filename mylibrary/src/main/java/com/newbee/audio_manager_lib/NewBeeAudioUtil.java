package com.newbee.audio_manager_lib;

import android.content.Context;
import android.media.AudioManager;


/**
 * Created by Administrator on 2018/7/6 0006.
 */

public class NewBeeAudioUtil {

    private AudioManager audioManager;
    private int volumeMaxNumb;


    public NewBeeAudioUtil(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        volumeMaxNumb=getMaxVolume();
    }


    public NewBeeAudioUtil(Context context,int setType) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.audioType=setType;
        volumeMaxNumb=getMaxVolume();
    }




    //AudioManager.STREAM_VOICE_CALL
    private int audioType=AudioManager.STREAM_MUSIC;
    public void setAudioType(int setType){
        this.audioType=setType;
        volumeMaxNumb=getMaxVolume();
    }

    //获取多媒体最大音量
    public int getMaxVolume() {
        try {
            return audioManager.getStreamMaxVolume(audioType);
        }catch (Exception e){
            return -1;
        }

    }

    //获取多媒体音量
    public int getVolume() {
        try {
            return audioManager.getStreamVolume(audioType);
        }catch (Exception e){
            return -1;
        }
    }




    /**
     * 设置多媒体音量
     * 这里我只写了多媒体和通话的音量调节，其他的只是参数不同，大家可仿照
     */
    public void setVolume(int volume,boolean showUi) {
        try {
            audioManager.setStreamVolume(audioType, //音量类型
                    volume,
                    getNeedFlags(showUi));
        } catch (Exception e) {
        }
    }


    private int getNeedFlags(boolean showUi){
        if(showUi){
            return  AudioManager.FLAG_PLAY_SOUND
                    | AudioManager.FLAG_SHOW_UI;
        }else {
            return AudioManager.FLAG_PLAY_SOUND;

        }
    }
    
    /**
     * 设置多媒体音量
     * 这里我只写了多媒体和通话的音量调节，其他的只是参数不同，大家可仿照
     */
    public void setVolumeByPercentage(int volume,boolean showUi ) {
        try {
            int needVolume = getMaxVolume();
            if (volume <= 0) {
                needVolume = 0;
            } else if (volume > 0 && volume < 100) {

                needVolume = needVolume * volume / 100;
            }
            audioManager.setStreamVolume(audioType, //音量类型
                    needVolume,getNeedFlags(showUi)
                    );
        } catch (Exception e) {
        }
    }









    public boolean valueUp(boolean showUi){
        int volumeNumb=getVolume();
        if(volumeNumb<volumeMaxNumb){
            volumeNumb++;
            setVolume(volumeNumb,showUi);
            return true;

        }
        return false;
    }


    public boolean valueDown(boolean showUi){
        int volumeNumb=getVolume();
        if(volumeNumb>0){
            volumeNumb--;
            setVolume(volumeNumb,showUi);
            return true;
        }
        return false;
    }


    public boolean valueLoop(boolean showUi){
        int volumeNumb=getVolume();
        if(volumeNumb<volumeMaxNumb){
            volumeNumb++;
        }else {
            volumeNumb=0;
        }
        setVolume(volumeNumb,showUi);
        return true;
    }

}
