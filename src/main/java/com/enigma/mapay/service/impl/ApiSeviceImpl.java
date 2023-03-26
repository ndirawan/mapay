package com.enigma.mapay.service.impl;

import com.enigma.mapay.utils.constant.ApiUrlConstant;
import com.enigma.mapay.utils.constant.Commands;
import com.enigma.mapay.utils.constant.Status;
import com.enigma.mapay.apiTransaction.request.ApiRequest;
import com.enigma.mapay.apiTransaction.response.PricelistResponse;
import com.enigma.mapay.apiTransaction.response.TopUpResponse;
import com.enigma.mapay.service.ApiService;
import com.enigma.mapay.utils.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiSeviceImpl implements ApiService {
    RestTemplate restTemplate;
    HttpHeaders headers;
    SignUtils signUtils;
    @Autowired
    public ApiSeviceImpl(RestTemplate restTemplate, HttpHeaders headers, SignUtils signUtils) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.signUtils = signUtils;
    }

    private ApiRequest createApi(String commands, String refId){
        ApiRequest apiRequest1 = new ApiRequest();
        apiRequest1.setCommands(commands);
        apiRequest1.setSign(signUtils.generateSignature(refId));
        apiRequest1.setUsername(signUtils.getUsername());
        apiRequest1.setRefId(refId);
        return apiRequest1;
    }

    @Override
    public ResponseEntity<TopUpResponse> topUp(String pulsaCode, String noHp, String refId) {
        ApiRequest apiRequest = createApi(Commands.TOP_UP, refId);
        apiRequest.setPulsaCode(pulsaCode);
        apiRequest.setHp(noHp);
        String url = ApiUrlConstant.URL_API;
        HttpEntity<ApiRequest> request = new HttpEntity<>(apiRequest, headers);

        return restTemplate.exchange(url, HttpMethod.POST, request, TopUpResponse.class);
    }

    @Override
    public ResponseEntity<PricelistResponse> pricelist(String type, String operator) {
        ApiRequest apiRequest = createApi(Commands.PRICELIST, "pl");
        apiRequest.setStatus(Status.STATUS_ACTIVE);
        String url = ApiUrlConstant.URL_API + "/" + type + "/" + operator;
        HttpEntity<ApiRequest> requestEntity = new HttpEntity<>(apiRequest, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, PricelistResponse.class);

    }

    @Override
    public ResponseEntity<TopUpResponse> topUpStatus(String refId) {
        ApiRequest apiRequest = createApi(Commands.INQUIRY, refId);
        String url = ApiUrlConstant.URL_API;
        HttpEntity<ApiRequest> request = new HttpEntity<>(apiRequest, headers);

        return restTemplate.exchange(url, HttpMethod.POST, request, TopUpResponse.class);
    }
}
