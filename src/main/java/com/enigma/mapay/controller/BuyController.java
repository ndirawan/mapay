package com.enigma.mapay.controller;

import com.enigma.mapay.dto.ApiDTO;
import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.service.ApiService;
import com.enigma.mapay.service.BuyPulsaDetailService;
import com.enigma.mapay.service.BuyPulsaService;
import com.enigma.mapay.service.CallbackApi;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiDTO> buyPulsa(@RequestBody BuyPulsa buyPulsa) throws Exception{

        service.savePulsa(buyPulsa, (BuyPulsa bp) -> {
            while (true) {
                ApiDTO apiDTO = apiService.topUpStatus(bp.getId()).getBody().getApiDTO();
                if (apiDTO.getStatus().equals("1") || apiDTO.getStatus().equals("2")) {

                    BuyPulsaDetail buyPulsaDetail = bp.getBuyDetail();
                    buyPulsaDetail.setStatus(apiDTO.getMessage());
                    buyPulsaDetail.setSn(apiDTO.getSn());

                    break;
                } else {
                    Thread.sleep(2000);
                }
            }
        });
        ApiDTO status = apiService.topUpStatus(buyPulsa.getId()).getBody().getApiDTO();
        return ResponseEntity.ok(status);
    }

    @PostMapping("/d")
    public BuyPulsaDetail buyPulsaDetail(@RequestBody BuyPulsaDetail buyPulsaDetail){
        return detailService.save(buyPulsaDetail);
    }
}
