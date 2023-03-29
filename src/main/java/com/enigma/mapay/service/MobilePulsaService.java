package com.enigma.mapay.service;

import com.enigma.mapay.dto.TopUpResponse;
import org.springframework.http.ResponseEntity;

public interface MobilePulsaService {
    ResponseEntity<TopUpResponse> topUp(String pulsaCode, String noHp , String refId);
    ResponseEntity<TopUpResponse> topUpStatus(String refId);
    ResponseEntity<String> pricelist(String type, String operator);
}
