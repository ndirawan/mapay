package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.SplitBillDetail;
import com.enigma.mapay.repository.SplitBillDetailRepository;
import com.enigma.mapay.service.SplitBillDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SplitBillDetailServiceImpl implements SplitBillDetailService {

    SplitBillDetailRepository splitBillDetailRepository;
    @Override
    public SplitBillDetail saveSplitBillDetail(SplitBillDetail splitBillDetail) {
        return splitBillDetailRepository.save(splitBillDetail);
    }

    @Override
    public SplitBillDetail getById(String id) {
        return null;
    }
}
