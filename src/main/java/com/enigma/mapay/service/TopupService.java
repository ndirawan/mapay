package com.enigma.mapay.service;

import com.enigma.mapay.dto.MidtransTrxResponse;
import com.enigma.mapay.entity.Topup;
import com.midtrans.httpclient.error.MidtransError;

import java.util.List;

public interface TopupService {

    MidtransTrxResponse saveTopup(Topup topup) throws MidtransError;

    List<Topup> getAllTopUp(String id);

    Topup getTopupById(String id);
}
