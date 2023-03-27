package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.repository.BuyPulsaDetailRepository;
import com.enigma.mapay.service.BuyPulsaDetailService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BuyPulsaDetailServiceImpl implements BuyPulsaDetailService {
    BuyPulsaDetailRepository buyPulsaDetailRepository;

    public BuyPulsaDetailServiceImpl(BuyPulsaDetailRepository buyPulsaDetailRepository) {
        this.buyPulsaDetailRepository = buyPulsaDetailRepository;
    }

    @Override
    public BuyPulsaDetail save(BuyPulsaDetail buyPulsaDetail) {
        return buyPulsaDetailRepository.save(buyPulsaDetail);
    }

    @Override
    public BuyPulsaDetail getById(String id) {
        return buyPulsaDetailRepository.findById(id).get();
    }
}
