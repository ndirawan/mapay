package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.entity.Withdraw;
import com.enigma.mapay.entity.WithdrawDetail;
import com.enigma.mapay.repository.WithdrawRepository;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.service.WithdrawDetailService;
import com.enigma.mapay.service.WithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WithdrawServiceImpl implements WithdrawService {


    WithdrawRepository withdrawRepository;

    UserService userService;

    WithdrawDetailService withdrawDetailService;


    @Override
    public Withdraw saveWithdraw(Withdraw withdraw) {
        User user = userService.getUserById(withdraw.getUser().getId());
       withdraw.setUser(user);
        WithdrawDetail wd = withdrawDetailService.saveTransferDetail(withdraw.getWithdrawDetail());
        Withdraw result = withdrawRepository.save(withdraw);
        wd.setWithdraw(result);
        return result;
    }

    @Override
    public List<Withdraw> getAllWithdraw() {
        return withdrawRepository.findAll();
    }

    @Override
    public Withdraw getTransferById(String id) {
        return withdrawRepository.findById(id).get();
    }
}
