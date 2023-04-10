package com.enigma.mapay.controller;

import com.enigma.mapay.dto.TopUpResponse;
import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.service.*;
import com.enigma.mapay.service.impl.UserDetailImpl;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.BUY_PATH)
@AllArgsConstructor
public class BuyController {
    BuyPulsaService service;
    UserService userService;
    MobilePulsaService mobilePulsaService;

    @PostMapping
    public TopUpResponse buyPulsa(@RequestBody BuyPulsa buyPulsa, Authentication authentication) throws JsonProcessingException {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        buyPulsa.setUser(userService.getUserByPhoneNumb(userDetail.getUsername()));
        return service.savePulsa(buyPulsa);
    }
    @PostMapping("/pricelist")
    public ResponseEntity<String> priceList(@RequestParam String type,
                                            @RequestParam String operator) {
        return mobilePulsaService.pricelist(type,operator);
    }
    @PostMapping("/{refId}")
    public ResponseEntity<TopUpResponse> cekStatus(@PathVariable String refId){
        return mobilePulsaService.topUpStatus(refId);
    }

    @PostMapping("/callback")
    public ResponseEntity<?> buyPulsaDetail(@RequestBody TopUpResponse topUpResponse) throws JsonProcessingException {
        if (topUpResponse != null){
            String status = topUpResponse.getBuyPulsaDTO().getStatus();

            BuyPulsa buyPulsa = service.findId(topUpResponse.getBuyPulsaDTO().getRefId());
            BuyPulsaDetail buyPulsaDetail = buyPulsa.getBuyDetail();

            if (status.equals("1")) {
                User user = userService.getUserById(buyPulsa.getUser().getId());
                user.setBalance(user.getBalance() - topUpResponse.getBuyPulsaDTO().getPrice());
                buyPulsaDetail.setSn(topUpResponse.getBuyPulsaDTO().getSn());
            }

            buyPulsaDetail.setStatus(topUpResponse.getBuyPulsaDTO().getMessage());
            buyPulsa.setBuyDetail(buyPulsaDetail);
            service.savePulsa(buyPulsa);
        }
        return ResponseEntity.ok("ok");
    }
}
