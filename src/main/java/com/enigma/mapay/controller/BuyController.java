package com.enigma.mapay.controller;

import com.enigma.mapay.apiTransaction.response.TopUpResponse;
import com.enigma.mapay.dto.ApiDTO;
import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.service.*;
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
    UserService userService;

    public BuyController(BuyPulsaService service, BuyPulsaDetailService detailService, ApiService apiService, UserService userService) {
        this.service = service;
        this.detailService = detailService;
        this.apiService = apiService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BuyPulsaDetail> buyPulsa(@RequestBody BuyPulsa buyPulsa) throws Exception{
        service.savePulsa(buyPulsa, (BuyPulsa bp) -> {
            while (true) {
                ApiDTO apiDTO = apiService.topUpStatus(bp.getId()).getBody().getApiDTO();
                String status = apiDTO.getStatus();
                if (status.equals("1") || status.equals("2")) {
                    if (status.equals("1")){
                        User user = userService.getUserById(bp.getUser().getId());
                        user.setBalance(user.getBalance() - apiDTO.getPrice());
                    }

                    BuyPulsaDetail buyPulsaDetail = bp.getBuyDetail();
                    buyPulsaDetail.setStatus(apiDTO.getMessage());
                    buyPulsaDetail.setSn(apiDTO.getSn());
                    buyPulsaDetail.setPrice(apiDTO.getPrice());

                    break;
                } else {
                    Thread.sleep(2000);
                }
            }
        });
        BuyPulsaDetail proses = buyPulsa.getBuyDetail();
        proses.setStatus("PROCESS");
        return ResponseEntity.ok(proses);
    }

    @PostMapping("/d")
    public BuyPulsaDetail buyPulsaDetail(@RequestBody BuyPulsaDetail buyPulsaDetail){
        return detailService.save(buyPulsaDetail);
    }
}
