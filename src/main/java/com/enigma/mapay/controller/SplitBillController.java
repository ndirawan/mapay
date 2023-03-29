package com.enigma.mapay.controller;

import com.enigma.mapay.entity.SplitBill;
import com.enigma.mapay.service.SplitBillDetailService;
import com.enigma.mapay.service.SplitBillService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiUrlConstant.SPLIT_BILL_PATH)
public class SplitBillController {

    SplitBillService splitBillService;
    SplitBillDetailService splitBillDetailService;

    @PostMapping
    public SplitBill createSplitBill(@RequestBody SplitBill splitBill){
        return splitBillService.createSplitBill(splitBill);
    }

    @GetMapping
    public List<SplitBill> getAll(){
        return splitBillService.getAllSplitBill();
    }
}
