package com.enigma.mapay.transactionApi.service;

import com.enigma.mapay.transactionApi.response.PricelistResponse;
import com.enigma.mapay.transactionApi.response.TopUpResponse;
import org.springframework.http.ResponseEntity;

public interface ApiService {
    ResponseEntity<TopUpResponse> topUp(String pulsaCode, String noHp , String refId);
    ResponseEntity<TopUpResponse> topUpStatus(String refId);
    ResponseEntity<PricelistResponse> pricelist(String type, String operator);
}
