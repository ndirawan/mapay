package com.enigma.mapay.controller;

import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.Withdraw;
import com.enigma.mapay.service.WithdrawService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.WITHDRAW_PATH)
@AllArgsConstructor
public class WithdrawController {

    WithdrawService withdrawService;

    @PostMapping
    public Withdraw saveWithdraw(@RequestBody Withdraw withdraw){
        return withdrawService.saveWithdraw(withdraw);
    }

    @GetMapping
    public List<Withdraw> getAllTopup(){
        return withdrawService.getAllWithdraw();
    }

}
