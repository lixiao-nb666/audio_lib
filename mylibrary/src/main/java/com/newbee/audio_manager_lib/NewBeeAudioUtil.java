package com.newbee.audio_manager_lib;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;


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
    public boolean setVolume(int volume,boolean showUi) {
        try {
            audioManager.setStreamVolume(audioType, //音量类型
                    volume,
                    getNeedFlags(showUi));
            return true;
        } catch (Exception e) {
            return false;
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
    public boolean setVolumeByPercentage(int volume,boolean showUi ) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public boolean valueUp(boolean showUi){
        int volumeNumb=getVolume();
        if(volumeNumb<volumeMaxNumb){
            volumeNumb++;
           return setVolume(volumeNumb,showUi);
        }
        return false;
    }


    public boolean valueDown(boolean showUi){
        int volumeNumb=getVolume();
        if(volumeNumb>0){
            volumeNumb--;
            return  setVolume(volumeNumb,showUi);
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
        return setVolume(volumeNumb,showUi);
    }


    /**
     * 分割线，设置状态
     */
    //音效一
    private static final String GET_MUSIC_PLUS_STATUS = "GetMusicPlusStatus";
    private static final String SET_MUSIC_PLUS_ENABLED = "SetMusicPlusStatus=1";
    private static final String SET_MUSIC_PLUS_DISABLED = "SetMusicPlusStatus=0";
    //音效二
    private static final String GET_BESLOUDNESS_STATUS = "GetBesLoudnessStatus";
    private static final String SET_BESLOUDNESS_ENABLED = "SetBesLoudnessStatus=1";
    private static final String SET_BESLOUDNESS_DISABLED = "SetBesLoudnessStatus=0";

//    private static final String GET_BESLOUDNESS_STATUS = "MTK_BESLOUDNESS_SUPPORT";
//    private static final String SET_BESLOUDNESS_ENABLED = "SetMTK_BESLOUDNESS_SUPPORT=true";
//    private static final String SET_BESLOUDNESS_DISABLED = "SetMTK_BESLOUDNESS_SUPPORT=false";

    //音效三
    private static final String GET_ANC_UI_STATUS = "ANC_UI";
    private static final String ANC_UI_STATUS_DISABLED = "ANC_UI=off";
    private static final String ANC_UI_STATUS_ENABLED = "ANC_UI=on";
    //音效四
    private static final String GET_HIFI_UI_STATUS = "hifi_dac";
    private static final String HIFI_UI_STATUS_DISABLED = "hifi_dac=off";
    private static final String HIFI_UI_STATUS_ENABLED = "hifi_dac=on";
    //音效五
    private static final String MTK_AUDENH_SUPPORT_State = "MTK_AUDENH_SUPPORT";
    private static final String MTK_AUDENH_SUPPORT_on = "MTK_AUDENH_SUPPORT=true";
    private static final String MTK_AUDENH_SUPPORT_off = "MTK_AUDENH_SUPPORT=false";

    public int getStatu(String statuStr) {
        String statu = audioManager.getParameters(statuStr);

        if (TextUtils.isEmpty(statu)) {
            return -1;
        }
        if (statu.contains("=1")) {
            return 1;
        }
        return 0;
    }

    public int getSpearkStatu() {
        return getStatu(GET_BESLOUDNESS_STATUS);
    }

    public void setSpearkMold() {
        int v=getSpearkStatu();
        if(v==-1){
            return;
        }
        boolean open = !(v==1);
        setSpearkMold(open);
    }

    public void setSpearkMold(boolean open) {
        String cmd = open ? SET_BESLOUDNESS_ENABLED : SET_BESLOUDNESS_DISABLED;
        audioManager.setParameters(cmd);
    }

    public int getSurroundldStatu() {
        return getStatu(GET_MUSIC_PLUS_STATUS);
    }

    public void setSurroundldMold() {
        int v=getSurroundldStatu();
        if(v==-1){
            return;
        }
        boolean open = !(v==1);
        setSurroundldMold(open);
    }

    public void setSurroundldMold(boolean open) {
        String cmd = open ? SET_MUSIC_PLUS_ENABLED : SET_MUSIC_PLUS_DISABLED;
        audioManager.setParameters(cmd);
    }


    public int getReductionStatu() {
        return getStatu(GET_ANC_UI_STATUS);
    }

    public void setReductionMold() {
        int v=getReductionStatu();
        if(v==-1){
            return;
        }
        boolean open = !(v==1);
        setReductionMold(open);
    }

    public void setReductionMold(boolean open) {
        String cmd = open ? ANC_UI_STATUS_ENABLED : ANC_UI_STATUS_DISABLED;
        audioManager.setParameters(cmd);
    }

    public int getHifiStatu() {
        return getStatu(GET_HIFI_UI_STATUS);
    }

    public void setHifiMold() {
        int v=getHifiStatu();
        if(v==-1){
            return;
        }
        boolean open = !(v==1);
        setHifiMold(open);
    }

    public void setHifiMold(boolean open) {
        String cmd = open ? HIFI_UI_STATUS_ENABLED : HIFI_UI_STATUS_DISABLED;
        audioManager.setParameters(cmd);
    }

    // 关闭/打开扬声器播放
    public void setSpeakerStatus(boolean on) {
        try {
            if (on) { //扬声器
                audioManager.setSpeakerphoneOn(true);
                audioManager.setMode(AudioManager.MODE_NORMAL);
            } else {
                // 设置最大音量
                int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, max, AudioManager.STREAM_VOICE_CALL);
                // 设置成听筒模式
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                audioManager.setSpeakerphoneOn(false);// 关闭扬声器
                audioManager.setRouting(AudioManager.MODE_NORMAL, AudioManager.ROUTE_EARPIECE, AudioManager.ROUTE_ALL);
            }
        }catch (Exception e){

        }

    }


}
