package com.newbee.audio_luminance_lib.service;

import java.util.ArrayList;
import java.util.List;

 class LuminanceEventSubscriptionSubject implements LuminanceEventSubject {

    private List<LuminanceEventObserver> observers;
    private static LuminanceEventSubscriptionSubject subscriptionSubject;

    private LuminanceEventSubscriptionSubject() {
        observers = new ArrayList<>();
    }

     static LuminanceEventSubscriptionSubject getInstence() {
        if (subscriptionSubject == null) {
            synchronized (LuminanceEventSubscriptionSubject.class) {
                if (subscriptionSubject == null)
                    subscriptionSubject = new LuminanceEventSubscriptionSubject();
            }
        }
        return subscriptionSubject;

    }

    @Override
    public void attach(LuminanceEventObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(LuminanceEventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void valueChange() {
        for(LuminanceEventObserver observer:observers){
            observer.valueChange();
        }
    }


}
