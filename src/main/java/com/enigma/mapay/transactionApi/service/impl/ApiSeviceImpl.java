package com.enigma.mapay.transactionApi.service.impl;

import com.enigma.mapay.transactionApi.request.ApiRequest;
import com.enigma.mapay.transactionApi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiSeviceImpl implements ApiService {
    RestTemplate restTemplate;
    ApiRequest apiRequest;
    @Autowired
    public ApiSeviceImpl(RestTemplate restTemplatet) {
        this.restTemplate = restTemplate;
    }

}
