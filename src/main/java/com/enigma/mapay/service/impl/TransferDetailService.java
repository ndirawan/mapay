package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.TransferDetail;
import com.enigma.mapay.repository.TransferDetailRepository;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransferDetailService implements com.enigma.mapay.service.TransferDetailService {
    TransferDetailRepository transferDetailRepository;

    @Override

    public TransferDetail saveTransferDetail(TransferDetail transferDetail) {
        return transferDetailRepository.save(transferDetail);
    }
    @Override

    public TransferDetail getById(String id) {
        try {
            if (transferDetailRepository.findById(id).isPresent()){
                return transferDetailRepository.findById(id).get();
            }else throw new DataNotFoundException("DATA NOT FOUND");
        }catch (DataNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
