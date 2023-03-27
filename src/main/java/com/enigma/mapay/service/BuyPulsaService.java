package com.enigma.mapay.service;

import com.enigma.mapay.entity.BuyPulsa;

import javax.security.auth.callback.Callback;

public interface BuyPulsaService {
    void savePulsa(BuyPulsa buyPulsa, CallbackApi callbackApi) throws InterruptedException;
    BuyPulsa findId(String id);
}
