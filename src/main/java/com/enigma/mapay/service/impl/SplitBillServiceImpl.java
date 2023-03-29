package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.SplitBill;
import com.enigma.mapay.entity.SplitBillDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.SplitBIllRepository;
import com.enigma.mapay.repository.TransferRepository;
import com.enigma.mapay.service.SplitBillDetailService;
import com.enigma.mapay.service.SplitBillService;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.constant.MessageConstant;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@AllArgsConstructor
public class SplitBillServiceImpl implements SplitBillService {

    SplitBIllRepository splitBIllRepository;

    UserService userService;

    SplitBillDetailService splitBillDetailService;
    @Override
    @Transactional
    public SplitBill createSplitBill(SplitBill splitBill) {
        User user = userService.getUserById(splitBill.getUserCreate().getId());
        splitBill.setUserCreate(user);
        SplitBill result = splitBIllRepository.save(splitBill);
        int numberOfPersons = splitBill.getSplitBillDetail().size();
        List<SplitBillDetail> splitBillDetails = new ArrayList<>(splitBill.getSplitBillDetail()); // membuat copy list splitBillDetail agar tidak terjadi exception ConcurrentModificationException
        for(SplitBillDetail sbd : splitBillDetails){
            User user1 = userService.getUserById(sbd.getUserSplit().getId());
            sbd.setUserSplit(user1);
            sbd.setSplitAmount(splitBill.getTotalAmount()/numberOfPersons);
            sbd.setSplitBill(splitBill);

            if (user1.getBalance() < sbd.getSplitAmount()){
                throw new DataNotFoundException("insufficient_balance");
            }
            user1.setBalance(user1.getBalance()-sbd.getSplitAmount());

            splitBillDetailService.saveSplitBillDetail(sbd);
        }
        return result;
    }


    @Override
    public List<SplitBill> getAllSplitBill() {
        return splitBIllRepository.findAll();
    }

    @Override
    public SplitBill getSplitBillById(String id) {
        return null;
    }
}
