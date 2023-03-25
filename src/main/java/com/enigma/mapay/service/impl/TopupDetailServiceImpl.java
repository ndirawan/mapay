package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.TopupDetail;
import com.enigma.mapay.repository.TopupDetailRepository;
import com.enigma.mapay.service.TopupDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TopupDetailServiceImpl implements TopupDetailService {
    TopupDetailRepository topupDetailRepository;

    @Override
    public TopupDetail saveTopupDetail(TopupDetail topupDetail) {
        return topupDetailRepository.save(topupDetail);
    }
}
