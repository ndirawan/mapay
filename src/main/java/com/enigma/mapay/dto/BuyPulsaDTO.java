package com.enigma.mapay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BuyPulsaDTO {
    @JsonProperty("ref_id")
    private String refId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("code")
    private String code;
    @JsonProperty("hp")
    private String hp;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("message")
    private String message;
    @JsonProperty("sn")
    private String sn;
    @JsonProperty("pin")
    private String pin;
    @JsonProperty("balance")
    private Integer balance;
    @JsonProperty("tr_id")
    private Integer trId;
    @JsonProperty("rc")
    private String rc;

}
//ref_id
//status
//code
//hp
//price
//message
//balance
//tr_id
//rc
