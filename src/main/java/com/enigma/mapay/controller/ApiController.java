package com.enigma.mapay.controller;

import com.enigma.mapay.transactionApi.constant.Constants;
import com.enigma.mapay.transactionApi.request.ApiRequest;
import com.enigma.mapay.transactionApi.response.PricelistResponse;
import com.enigma.mapay.transactionApi.constant.ApiUrlConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api")
public class ApiController {
    RestTemplate restTemplate;
    ApiRequest apiRequest;
    HttpHeaders headers;
    @Value("${secret.username}")
    private String username;
    @Value("${secret.sign}")
    private String sign;

    public ApiController(RestTemplate restTemplate, ApiRequest apiRequest, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.apiRequest = apiRequest;
        this.headers = headers;
    }

    @PostMapping()
    public ResponseEntity<PricelistResponse> priceList(@RequestParam String type,
                                                       @RequestParam String operator){
        apiRequest.setCommands(Constants.PRICELIST);
        apiRequest.setSign(sign);
        apiRequest.setUsername(username);
        apiRequest.setStatus(Constants.STATUS_ACTIVE);
        String url = ApiUrlConstants.URL_API + type + "/" + operator;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ApiRequest> requestEntity = new HttpEntity<>(apiRequest, headers);
        ResponseEntity<PricelistResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PricelistResponse.class);
        return responseEntity;
    }
}
