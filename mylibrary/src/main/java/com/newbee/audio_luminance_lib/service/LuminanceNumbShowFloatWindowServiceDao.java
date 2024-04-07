package com.newbee.audio_luminance_lib.service;

import android.content.Context;

import com.newbee.bulid_lib.mybase.service.BaseServiceDao;

public class LuminanceNumbShowFloatWindowServiceDao extends BaseServiceDao {
    public LuminanceNumbShowFloatWindowServiceDao(Context context) {
        super(context);
    }

    @Override
    protected Class getSsCls() {
        return LuminanceNumbShowFloatWindowService.class;
    }
}
