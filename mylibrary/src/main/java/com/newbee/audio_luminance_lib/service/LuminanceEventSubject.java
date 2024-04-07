package com.newbee.audio_luminance_lib.service;




 interface LuminanceEventSubject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    void attach(LuminanceEventObserver observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    void detach(LuminanceEventObserver observer);

     void valueChange();
}
