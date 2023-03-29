package com.enigma.mapay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopUpResponse {
    @JsonProperty("data")
    private BuyPulsaDTO buyPulsaDTO;
}
