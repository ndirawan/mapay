package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.BuyPulsaRepository;
import com.enigma.mapay.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BuyPulsaImpl implements BuyPulsaService {
    BuyPulsaRepository buyPulsaRepository;
    UserService userService;
    ApiService apiService;

    public BuyPulsaImpl(BuyPulsaRepository buyPulsaRepository, UserService userService, ApiService apiService) {
        this.buyPulsaRepository = buyPulsaRepository;
        this.userService = userService;
        this.apiService = apiService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePulsa(BuyPulsa buyPulsa, CallbackApi callbackApi) throws InterruptedException {
        User user = userService.getUserById(buyPulsa.getUser().getId());
        buyPulsa.setUser(user);

        BuyPulsa result = buyPulsaRepository.save(buyPulsa);
        apiService.topUp(
                buyPulsa.getBuyDetail().getPulsaCode(),
                user.getPhoneNumb(),
                result.getId());

        callbackApi.onSuccess(buyPulsa);
    }

    @Override
    public BuyPulsa findId(String id) {
        if (buyPulsaRepository.findById(id).isPresent()) return buyPulsaRepository.findById(id).get();
        else return null;
    }
}
