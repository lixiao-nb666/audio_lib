package com.newbee.audio_manager_lib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_more);
        NewBeeAudioUtil newBeeAudioUtil=new NewBeeAudioUtil(this);
        findViewById(R.id.bt_v_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeeAudioUtil.valueUp();
            }
        });
        findViewById(R.id.bt_v_j).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeeAudioUtil.valueDown();
            }
        });
        findViewById(R.id.bt_v_loop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeeAudioUtil.valueLoop();
            }
        });
    }
}
