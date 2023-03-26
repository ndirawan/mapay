package com.enigma.mapay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricelistDTO {
    @JsonProperty("status")
    private String status;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("pulsa_code")
    private String pulsaCode;
    @JsonProperty("pulsa_op")
    private String pulsaOp;
    @JsonProperty("pulsa_nominal")
    private String pulsaNominal;
    @JsonProperty("pulsa_details")
    private String pulsaDetail;
    @JsonProperty("pulsa_price")
    private Long pulsaPrice;
    @JsonProperty("pulsa_type")
    private String pulsaType;
    @JsonProperty("masaaktif")
    private String masaaktif;

}
//"status"
//"icon_url"
//"pulsa_code"
//"pulsa_op
//"pulsa_nominal"
//"pulsa_details"
//"pulsa_price"
//"pulsa_type"
//"masaaktif"