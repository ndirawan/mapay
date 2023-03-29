package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.repository.BuyPulsaDetailRepository;
import com.enigma.mapay.service.BuyPulsaDetailService;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Slf4j
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
        try {
            if (buyPulsaDetailRepository.findById(id).isPresent()){
                return buyPulsaDetailRepository.findById(id).get();
            }else throw new DataNotFoundException("DATA NOT FOUND");
        }catch (DataNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
