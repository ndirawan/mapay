package com.enigma.mapay.service.impl;

import com.enigma.mapay.dto.TopupDTO;
import com.enigma.mapay.dto.TopupDetailDTO;
import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.TopupDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.TopupDetailRepository;
import com.enigma.mapay.repository.TopupRepository;
import com.enigma.mapay.service.TopupDetailService;
import com.enigma.mapay.service.TopupService;
import com.enigma.mapay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TopupServiceImpl implements TopupService {

    TopupRepository topupRepository;

    UserService userService;

    TopupDetailService topupDetailService;

    @Override
    public Topup saveTopup(Topup topup) {
        User user = userService.getUserById(topup.getUser().getId());
        user.setBalance(user.getBalance() + topup.getTopupDetail().getAmount());

        topup.setUser(user);
        TopupDetail td = topupDetailService.saveTopupDetail(topup.getTopupDetail());
        Topup result = topupRepository.save(topup);
        td.setTopup(result);

        topupDetailService.saveTopupDetail(td);

        return result;
    }
    @Override
    public List<Topup> getAllTopUp() {
        return topupRepository.findAll();
    }

    @Override
    public Topup getTopupById(String id) {
        return topupRepository.findById(id).get();
    }
}
