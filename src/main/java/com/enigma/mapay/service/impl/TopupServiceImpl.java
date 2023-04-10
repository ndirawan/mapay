package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.MidtransTrxResponse;
import com.enigma.mapay.config.MidtransConfig;
import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.TopupRepository;
import com.enigma.mapay.service.*;
import com.midtrans.httpclient.error.MidtransError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class TopupServiceImpl implements TopupService {

    TopupRepository topupRepository;
    UserService userService;
    TopupDetailService topupDetailService;
    MidtransConfig midtransConfig;

    @Override
    public MidtransTrxResponse saveTopup(Topup topup) throws MidtransError {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> transactionDetails = new HashMap<>();

        Topup result = topupRepository.save(topup);

        transactionDetails.put("order_id", result.getId());
        transactionDetails.put("gross_amount", topup.getTopupDetail().getAmount().toString());
        params.put("transaction_details", transactionDetails);
        midtransConfig.snapApi().createTransaction(params);

        String token = midtransConfig.snapApi().createTransactionToken(params);
        String redirectUrl = midtransConfig.snapApi().createTransactionRedirectUrl(params);

        MidtransTrxResponse trxResponse = new MidtransTrxResponse();
        trxResponse.setToken(token);
        trxResponse.setRedirectUrl(redirectUrl);

        return trxResponse;
    }

    @Override
    public List<Topup> getAllTopUp(String id) {
        return topupRepository.findTopupByUserPhoneNumber(id).get();
    }

    @Override
    public Topup getTopupById(String id) {
        return topupRepository.findById(id).get();
    }
}
