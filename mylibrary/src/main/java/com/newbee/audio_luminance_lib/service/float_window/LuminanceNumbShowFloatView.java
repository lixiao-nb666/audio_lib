package com.newbee.audio_luminance_lib.service.float_window;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.newbee.audio_luminance_lib.R;
import com.newbee.audio_luminance_lib.luminance.NewBeeSystemLuminanceUtil;
import com.newbee.bulid_lib.mybase.service.floatwindow.BaseFloatView;


public class LuminanceNumbShowFloatView extends BaseFloatView {
    private ImageView showLNumbIV,showLTIV;

    public LuminanceNumbShowFloatView(Context context, WindowManager windowManager, Listen listen, int viewId) {
        super(context, windowManager, listen, viewId);
        showLNumbIV=view.findViewById(R.id.iv_l_n);
        showLTIV=view.findViewById(R.id.iv_l_t);
    }

    public LuminanceNumbShowFloatView(Context context, WindowManager windowManager, Listen listen, View view) {
        super(context, windowManager, listen, view);
    }



    @Override
    protected int getGravity() {
        return Gravity.LEFT|Gravity.CENTER_VERTICAL;
    }

    @Override
    protected int getScrollX() {
        return 4;
    }





    public void setShowLNumbIV(){
        int lv=LuminanceNumbShowValue.FloatViewShowValue;
        int imgRs=R.drawable.l_n_0;
        int imgTRs=R.drawable.l_t_0;
        switch (lv){
            case 0:
                imgRs=R.drawable.l_n_0;
                imgTRs=R.drawable.l_t_0;
                break;
            case 1:
                imgRs=R.drawable.l_n_1;
                imgTRs=R.drawable.l_t_1;
                break;
            case 2:
                imgRs=R.drawable.l_n_2;
                imgTRs=R.drawable.l_t_2;
                break;
            case 3:
                imgRs=R.drawable.l_n_3;
                imgTRs=R.drawable.l_t_3;
                break;
            case 4:
                imgRs=R.drawable.l_n_4;
                imgTRs=R.drawable.l_t_4;
                break;
            case 5:
                imgRs=R.drawable.l_n_5;
                imgTRs=R.drawable.l_t_5;
                break;
            case 6:
                imgRs=R.drawable.l_n_6;
                imgTRs=R.drawable.l_t_6;
                break;
            case 7:
                imgRs= R.drawable.l_n_7;
                imgTRs=R.drawable.l_t_7;
                break;
        }
        showLNumbIV.setImageResource(imgRs);
        showLTIV.setImageResource(imgTRs);
    }
}
