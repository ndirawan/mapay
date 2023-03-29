package com.enigma.mapay.service;

import com.enigma.mapay.dto.TopUpResponse;
import com.enigma.mapay.entity.BuyPulsa;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BuyPulsaService {
    TopUpResponse savePulsa(BuyPulsa buyPulsa) throws JsonProcessingException;
    BuyPulsa findId(String id);
}
