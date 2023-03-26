package com.enigma.mapay.apiTransaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {
    private String commands;
    private String username;
    private String sign;
    private String status;
    @JsonProperty("ref_id")
    private String refId;
    @JsonProperty("hp")
    private String hp;
    @JsonProperty("pulsa_code")
    private String pulsaCode;
}
