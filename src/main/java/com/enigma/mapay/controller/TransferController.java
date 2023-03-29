package com.enigma.mapay.controller;

import com.enigma.mapay.entity.Transfer;
import com.enigma.mapay.service.TransferService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.TRANSFER_PATH)
@AllArgsConstructor
public class TransferController {
    TransferService transferService;

    @PostMapping
    public Transfer saveTransfer(@RequestBody Transfer transfer){
        return transferService.saveTransfer(transfer);
    }

    @GetMapping
    public List<Transfer> getAllTransfer(){
        return transferService.getAllTransfer();
    }

    @GetMapping("/{id}")
    public Transfer findById(@PathVariable String id){
        return transferService.getTransferById(id);
    }
}
