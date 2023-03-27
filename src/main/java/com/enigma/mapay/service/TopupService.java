package com.enigma.mapay.service;

import com.enigma.mapay.dto.TopupDTO;
import com.enigma.mapay.entity.Topup;

import java.util.List;

public interface TopupService {

    Topup saveTopup(Topup topup);

    List<Topup> getAllTopUp();

    Topup getTopupById(String id);
}
