package com.enigma.mapay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class PricelistResponse {
    @JsonProperty("data")
    private List<PricelistDTO> data;
}
