package com.enigma.mapay.controller;

import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.service.ApiService;
import com.enigma.mapay.service.BuyPulsaDetailService;
import com.enigma.mapay.service.BuyPulsaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("buy")
public class BuyController {
    BuyPulsaService service;
    BuyPulsaDetailService detailService;
    ApiService apiService;

    public BuyController(BuyPulsaService service, BuyPulsaDetailService detailService, ApiService apiService) {
        this.service = service;
        this.detailService = detailService;
        this.apiService = apiService;
    }

    @PostMapping
    public BuyPulsa buyPulsa(@RequestBody BuyPulsa buyPulsa) throws Exception{
        return service.savePulsa(buyPulsa);
    }

    @PostMapping("/d")
    public BuyPulsaDetail buyPulsaDetail(@RequestBody BuyPulsaDetail buyPulsaDetail){
        return detailService.save(buyPulsaDetail);
    }
}
