package com.enigma.mapay.controller;

import com.enigma.mapay.dto.TopUpResponse;
import com.enigma.mapay.service.MobilePulsaService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.MOBILEPULSA_PATH)
@AllArgsConstructor
public class MobilePulsaController {
    MobilePulsaService mobilePulsaService;

    @PostMapping()
    public ResponseEntity<String> priceList(@RequestParam String type,
                                            @RequestParam String operator) {
        return mobilePulsaService.pricelist(type,operator);
    }
    @PostMapping("/{refId}")
    public ResponseEntity<TopUpResponse> cekStatus(@PathVariable String refId){
        return mobilePulsaService.topUpStatus(refId);
    }
}
