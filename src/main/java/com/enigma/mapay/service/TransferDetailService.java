package com.enigma.mapay.service;

import com.enigma.mapay.entity.TransferDetail;

public interface TransferDetailService {
    TransferDetail saveTransferDetail(TransferDetail transferDetail);
    TransferDetail getById(String id);
}
