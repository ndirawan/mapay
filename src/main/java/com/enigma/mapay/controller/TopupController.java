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

    TopupService topupService;

    @PostMapping
    public Topup saveTopup(@RequestBody Topup topup){
        return topupService.saveTopup(topup);
    }

    @GetMapping
    public List<Topup> getAllTopup(){
        return topupService.getAllTopUp();
    }

    @GetMapping("/{id}")
    public Topup findById(@PathVariable String id){
        return topupService.getTopupById(id);
    }
}
