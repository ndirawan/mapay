package com.enigma.mapay.controller;

import com.enigma.mapay.dto.TopupDTO;
import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.service.TopupDetailService;
import com.enigma.mapay.service.TopupService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.TOPUP_PATH)
@AllArgsConstructor
public class TopupController {

    TopupDetailService topupDetailService;
    TopupService topupService;

    @PostMapping("")
    public Topup savePurchase(@RequestBody Topup topup){
        return topupService.saveTopup(topup);
    }

    @GetMapping("")
    public List<Topup> getAllPurchase(){
        return topupService.getAllTopUp();
    }

    @GetMapping("/{id}")
    public TopupDTO findById(@PathVariable String id) throws NoSuchFieldException {
        return topupService.getTopupById(id);
    }
}
