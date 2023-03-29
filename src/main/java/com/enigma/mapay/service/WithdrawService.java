package com.enigma.mapay.service;

import com.enigma.mapay.entity.Withdraw;

import java.util.List;

public interface WithdrawService {
    Withdraw saveWithdraw(Withdraw withdraw);

    List<Withdraw> getAllWithdraw();

    Withdraw getTransferById(String id);
}
