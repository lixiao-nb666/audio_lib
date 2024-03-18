package com.newbee.audio_manager_lib;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;


public class NewBeeAudioStatuUtil {
    private static NewBeeAudioStatuUtil util;
    private AudioManager audioManager;
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

    private Listen listen;
    public NewBeeAudioStatuUtil(Context context, Listen listen) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.listen=listen;
    }


    public boolean getSpearkStatu() {
        String statu = audioManager.getParameters(GET_BESLOUDNESS_STATUS);

        if (TextUtils.isEmpty(statu)) {
            if(null!=listen){
                listen.needHideSpearkMold();
            }
            return false;
        }
        if (statu.contains("=1")) {
            return true;
        }
        return false;
    }

    public void setSpearkMold() {
        boolean open = !getSpearkStatu();
        setSpearkMold(open);
    }

    public void setSpearkMold(boolean open) {
        String cmd = open ? SET_BESLOUDNESS_ENABLED : SET_BESLOUDNESS_DISABLED;
        audioManager.setParameters(cmd);
    }

    public boolean getSurroundldStatu() {
        String statu = audioManager.getParameters(GET_MUSIC_PLUS_STATUS);

        if (TextUtils.isEmpty(statu)) {
            if(null!=listen){
                listen.needHideSurroundMold();
            }
            return false;
        }
        if (statu.equals("1")) {
            return true;
        }
        return false;
    }

    public void setSurroundldMold() {
        boolean open = !getSurroundldStatu();
        setSurroundldMold(open);
    }

    public void setSurroundldMold(boolean open) {
        String cmd = open ? SET_MUSIC_PLUS_ENABLED : SET_MUSIC_PLUS_DISABLED;
        audioManager.setParameters(cmd);
    }


    public boolean getReductionStatu() {
        String statu = audioManager.getParameters(GET_ANC_UI_STATUS);
        if (TextUtils.isEmpty(statu)) {
            if(null!=listen){
                listen.needHideReductionMold();
            }
            return false;
        }
        if (statu.equals("on")) {
            return true;
        }
        return false;
    }

    public void setReductionMold() {
        boolean open = !getReductionStatu();
        setReductionMold(open);
    }

    public void setReductionMold(boolean open) {
        String cmd = open ? ANC_UI_STATUS_ENABLED : ANC_UI_STATUS_DISABLED;
        audioManager.setParameters(cmd);
    }

    public boolean getHifiStatu() {
        String statu = audioManager.getParameters(GET_HIFI_UI_STATUS);
        if (TextUtils.isEmpty(statu)) {
            if(null!=listen){
                listen.needHideHifiMold();
            }
            return false;
        }
        if (statu.equals("on")) {
            return true;
        }
        return false;
    }

    public void setHifiMold() {
        boolean open = !getHifiStatu();
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


    public interface Listen {

        public void needHideSpearkMold();

        public void needHideSurroundMold();

        public void needHideReductionMold();

        public void needHideHifiMold();

    }


}
