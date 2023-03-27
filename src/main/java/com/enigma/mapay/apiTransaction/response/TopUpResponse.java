package com.enigma.mapay.apiTransaction.response;

import com.enigma.mapay.dto.ApiDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopUpResponse {
    @JsonProperty("data")
    private ApiDTO apiDTO;
}
