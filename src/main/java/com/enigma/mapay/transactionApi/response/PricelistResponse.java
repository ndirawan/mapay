package com.enigma.mapay.transactionApi.response;

import com.enigma.mapay.transactionApi.dto.PricelistDTO;
import lombok.Data;

import java.util.List;
@Data
public class PricelistResponse {
    private List<PricelistDTO> data;
}
