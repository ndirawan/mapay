package com.enigma.mapay.service.impl;


import com.enigma.mapay.entity.WithdrawDetail;
import com.enigma.mapay.repository.WithdrawDetailRepository;
import com.enigma.mapay.service.WithdrawDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WithdrawDetailServiceImpl implements WithdrawDetailService {

    WithdrawDetailRepository withdrawDetailRepository;

    @Override
    public WithdrawDetail saveTransferDetail(WithdrawDetail withdrawDetail) {
        LocalDateTime now = LocalDateTime.now();
        String randomChars = UUID.randomUUID().toString().substring(0, 8);
        String fileName = randomChars;
        withdrawDetail.setCodeWithdraw(fileName);
        withdrawDetail.setStatus("PROCESS");
        withdrawDetail.setCreatedTime(now);
        withdrawDetail.setLastWithdrawalTime(now.plusMinutes(30));
        return withdrawDetailRepository.save(withdrawDetail);
    }

    @Override
    public WithdrawDetail getById(String id) {
        return withdrawDetailRepository.findById(id).get();
    }
}
