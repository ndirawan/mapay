package com.enigma.mapay.service;

import com.enigma.mapay.entity.Transfer;

import java.util.List;

public interface TransferService {

    Transfer saveTransfer(Transfer transfer);

    List<Transfer> getAllTransfer();

    Transfer getTransferById(String id);
}
