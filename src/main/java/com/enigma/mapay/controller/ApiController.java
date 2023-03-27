package com.enigma.mapay.controller;

import com.enigma.mapay.apiTransaction.response.PricelistResponse;
import com.enigma.mapay.apiTransaction.response.TopUpResponse;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.service.ApiService;
import com.enigma.mapay.service.BuyPulsaDetailService;
import com.enigma.mapay.service.BuyPulsaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiController {
    ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;;
    }

    @PostMapping()
    public ResponseEntity<PricelistResponse> priceList(@RequestParam String type,
                                                       @RequestParam String operator){
        return apiService.pricelist(type,operator);
    }
    @PostMapping("/topup")
    public ResponseEntity<TopUpResponse> topUp(@RequestParam String pulsaCode,
                                               @RequestParam String hp,
                                               @RequestParam String refId){
        return apiService.topUp(pulsaCode,hp,refId);
    }
    @PostMapping("/topup/{refId}")
    public ResponseEntity<TopUpResponse> topUp(@PathVariable String refId){

        return apiService.topUpStatus(refId);
    }
}
