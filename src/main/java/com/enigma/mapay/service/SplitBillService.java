package com.enigma.mapay.service;

import com.enigma.mapay.entity.SplitBill;
import com.enigma.mapay.entity.Transfer;

import java.util.List;

public interface SplitBillService {

    SplitBill createSplitBill(SplitBill splitBill);

    List<SplitBill> getAllSplitBill();

    SplitBill getSplitBillById(String id);
}
