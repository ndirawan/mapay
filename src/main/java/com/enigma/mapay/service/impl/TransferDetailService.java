package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.TransferDetail;
import com.enigma.mapay.repository.TransferDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class TransferDetailService implements com.enigma.mapay.service.TransferDetailService {
    TransferDetailRepository transferDetailRepository;

    @Override

    public TransferDetail saveTransferDetail(TransferDetail transferDetail) {
        return transferDetailRepository.save(transferDetail);
    }

    @Override

    public TransferDetail getById(String id) {
        return transferDetailRepository.findById(id).get();
    }
}
