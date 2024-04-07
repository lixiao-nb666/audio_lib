package com.newbee.audio_luminance;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.newbee.audio_luminance_lib.audio.NewBeeAudioUtil;

import com.newbee.audio_luminance_lib.luminance.NewBeeSystemLuminanceUtil;
import com.newbee.audio_luminance_lib.service.LuminanceNumbShowFloatWindowServiceManager;
import com.newbee.audio_luminance_lib.service.float_window.LuminanceNumbShowValue;


public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_more);
        NewBeeAudioUtil newBeeAudioUtil=new NewBeeAudioUtil(this);
        findViewById(R.id.bt_v_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeeAudioUtil.valueUp(true);
                NewBeeSystemLuminanceUtil.getInstance().valueUp(TestActivity.this);

            }
        });
        findViewById(R.id.bt_v_j).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewBeeSystemLuminanceUtil.getInstance().valueDown(TestActivity.this);
                newBeeAudioUtil.valueDown(false);

            }
        });
        findViewById(R.id.bt_v_loop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeeAudioUtil.valueLoop(true);
                NewBeeSystemLuminanceUtil.getInstance().valueLoop(TestActivity.this);
                LuminanceNumbShowValue.FloatViewShowValue=NewBeeSystemLuminanceUtil.getInstance().getValue(TestActivity.this);
                LuminanceNumbShowFloatWindowServiceManager.getInstance().startShowView(TestActivity.this);
            }
        });
    }
}
