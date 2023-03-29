package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.PricelistResponse;
import com.enigma.mapay.dto.TopUpResponse;
import com.enigma.mapay.dto.PricelistDTO;
import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.BuyPulsaRepository;
import com.enigma.mapay.service.*;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Slf4j
public class BuyPulsaImpl implements BuyPulsaService {
    BuyPulsaRepository buyPulsaRepository;
    UserService userService;
    MobilePulsaService mobilePulsaService;
    BuyPulsaDetailService detailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TopUpResponse savePulsa(BuyPulsa buyPulsa) throws JsonProcessingException {
        User user = userService.getUserById(buyPulsa.getUser().getId());
        buyPulsa.setUser(user);
        BuyPulsa result = buyPulsaRepository.save(buyPulsa);
        BuyPulsaDetail detail = result.getBuyDetail();
        String reference = mobilePulsaService.pricelist(buyPulsa.getBuyDetail().getType(), "").getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        PricelistResponse prx = objectMapper.readValue(reference, PricelistResponse.class);

        Integer price = 0;
        for (PricelistDTO x : prx.getData()) {
            if (x.getPulsaCode().equals(buyPulsa.getBuyDetail().getPulsaCode())){
                price += x.getPulsaPrice();
            }
        }
        try {
            if (user.getBalance() < price){
                detail.setStatus("FAILED");
                buyPulsaRepository.save(result);
                throw new ArithmeticException();
            }
            TopUpResponse response = mobilePulsaService.topUp(
                    buyPulsa.getBuyDetail().getPulsaCode(),
                    user.getPhoneNumber(),
                    result.getId()).getBody();
            detail.setPrice(response.getBuyPulsaDTO().getPrice());
            detail.setStatus(response.getBuyPulsaDTO().getMessage());

            result.setBuyDetail(detail);
            buyPulsaRepository.save(result);

            return response;
        }catch (ArithmeticException e){
            e.printStackTrace();
            log.error("BALANCE IS LESS THAN PRICE");
        }catch (NullPointerException e){
            e.printStackTrace();
            log.error("DATA NOT SAVE");
        }
        return null;
    }

    @Override
    public BuyPulsa findId(String id) {
        try {
            if (buyPulsaRepository.findById(id).isPresent()){
                return buyPulsaRepository.findById(id).get();
            }else throw new DataNotFoundException("DATA NOT FOUND");

        }catch (DataNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }
}
