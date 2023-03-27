package com.enigma.mapay.service;

import com.enigma.mapay.entity.BuyPulsa;

public interface CallbackApi {
    void onSuccess(BuyPulsa buyPulsa) throws InterruptedException;
}
