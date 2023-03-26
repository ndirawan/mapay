package com.enigma.mapay.transactionApi.response;

import com.enigma.mapay.transactionApi.dto.TopUpDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TopUpResponse {
    @JsonProperty("data")
    private TopUpDTO topUpDTO;
}
