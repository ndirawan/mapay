package com.enigma.mapay.service;

import com.enigma.mapay.apiTransaction.response.PricelistResponse;
import com.enigma.mapay.apiTransaction.response.TopUpResponse;
import org.springframework.http.ResponseEntity;

public interface ApiService {
    ResponseEntity<TopUpResponse> topUp(String pulsaCode, String noHp , String refId);
    ResponseEntity<TopUpResponse> topUpStatus(String refId);
    ResponseEntity<PricelistResponse> pricelist(String type, String operator);
}
