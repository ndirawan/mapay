package com.enigma.mapay.service;

import com.enigma.mapay.entity.BuyPulsaDetail;

public interface BuyPulsaDetailService {
    BuyPulsaDetail save(BuyPulsaDetail buyPulsaDetail);
    BuyPulsaDetail getById(String id);
}
