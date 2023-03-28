package com.enigma.mapay.service;

import com.enigma.mapay.entity.WithdrawDetail;

public interface WithdrawDetailService {

    WithdrawDetail saveTransferDetail(WithdrawDetail withdrawDetail);

    WithdrawDetail getById(String id);

//    WithdrawDetail updateStatus(String status);
}
