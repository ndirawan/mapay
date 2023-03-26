package com.enigma.mapay.apiTransaction.response;

import com.enigma.mapay.dto.ApiTopUpDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopUpResponse {
    @JsonProperty("data")
    private ApiTopUpDTO apiTopUpDTO;
}
