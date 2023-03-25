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
        topup.setTopupDate(Date.valueOf(LocalDate.now()));
        User user = userService.getUserById(topup.getUser().getId());
        topup.setUser(user);
        Topup result = topupRepository.save(topup);
        Integer totalPrice = 0; // inisialisasi nilai totalPrice di luar loop
        for(TopupDetail td : topup.getTopupDetails()){
            td.setTopupBalance(td.getTopupBalance());
//            update saldo user
            user.setBalance(user.getBalance() + td.getTopupBalance());
            td.setMethode(td.getMethode());
            td.setStatus(td.getStatus());
            userService.saveUser(user);
            topupDetailService.saveTopupDetail(td);
        }
        return result;
    }

    @Override
    public List<Topup> getAllTopUp() {
        return topupRepository.findAll();
    }

    @Override
    public TopupDTO getTopupById(String id) throws NoSuchFieldException {
        if (topupRepository.findById(id).isPresent()) {
            Topup topup = topupRepository.findById(id).get();
            TopupDTO topupDTO = new TopupDTO();
            topupDTO.setTopupId(topup.getTopupId());
            topupDTO.setUserName(topup.getUser().getFullName());
            topupDTO.setTopupDate(topup.getTopupDate());
            List<TopupDetailDTO> topupDetailDTOS = new ArrayList<>();
            Integer total = 0;
            for (TopupDetail td : topup.getTopupDetails()) {
                TopupDetailDTO topupDetailsDTO = new TopupDetailDTO();
                topupDetailsDTO.setMethod(td.getMethode());
                topupDetailsDTO.setSaldoTopup(td.getTopupBalance());
                topupDetailsDTO.setStatus(td.getStatus());
                topupDetailsDTO.setPpn((td.getTopupBalance() * 10)/100);
                topupDetailsDTO.setSubTotal(topupDetailsDTO.getSaldoTopup() + topupDetailsDTO.getPpn());
                total += topupDetailsDTO.getSubTotal();
                topupDetailDTOS.add(topupDetailsDTO);
            }

            topupDTO.setTotal(total);
            topupDTO.setTopupDetailDTOS(topupDetailDTOS);

            return topupDTO;
        }else throw new NoSuchFieldException();
    }
}
