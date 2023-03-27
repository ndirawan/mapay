package com.enigma.mapay.service.impl;


import com.enigma.mapay.apiTransaction.response.TopUpResponse;
import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.BuyPulsaRepository;
import com.enigma.mapay.service.ApiService;
import com.enigma.mapay.service.BuyPulsaDetailService;
import com.enigma.mapay.service.BuyPulsaService;
import com.enigma.mapay.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLTransactionRollbackException;


@Service
public class BuyPulsaImpl implements BuyPulsaService {
    BuyPulsaRepository buyPulsaRepository;
    UserService userService;
    BuyPulsaDetailService detailService;
    ApiService apiService;

    public BuyPulsaImpl(BuyPulsaRepository buyPulsaRepository, UserService userService, BuyPulsaDetailService detailService, ApiService apiService) {
        this.buyPulsaRepository = buyPulsaRepository;
        this.userService = userService;
        this.detailService = detailService;
        this.apiService = apiService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuyPulsa savePulsa(BuyPulsa buyPulsa) {
        User user = userService.getUserById(buyPulsa.getUser().getId());
        buyPulsa.setUser(user);

        BuyPulsa result = buyPulsaRepository.save(buyPulsa);
        BuyPulsaDetail buyPulsaDetail = result.getBuyDetail();

        TopUpResponse top = apiService.topUp(
                buyPulsa.getBuyDetail().getPulsaCode(),
                user.getPhoneNumb(),
                result.getId()).getBody();

        buyPulsaDetail.setSn(top.getApiTopUpDTO().getSn());
        buyPulsaDetail.setStatus(top.getApiTopUpDTO().getMessage());

        return result;
    }

    @Override
    public BuyPulsa findId(String id) {
        return buyPulsaRepository.findById(id).get();
    }
}
