package com.enigma.mapay.apiTransaction.response;

import com.enigma.mapay.dto.PricelistDTO;
import lombok.Data;

import java.util.List;
@Data
public class PricelistResponse {
    private List<PricelistDTO> data;
}
