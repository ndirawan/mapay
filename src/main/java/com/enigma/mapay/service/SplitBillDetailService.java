package com.enigma.mapay.service;

import com.enigma.mapay.entity.SplitBillDetail;


public interface SplitBillDetailService {

    SplitBillDetail saveSplitBillDetail(SplitBillDetail splitBillDetail);

    SplitBillDetail getById(String id);

}
