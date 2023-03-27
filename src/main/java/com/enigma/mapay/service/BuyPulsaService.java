package com.enigma.mapay.service;

import com.enigma.mapay.entity.BuyPulsa;

public interface BuyPulsaService {
    BuyPulsa savePulsa(BuyPulsa buyPulsa);
    BuyPulsa findId(String id);
}
