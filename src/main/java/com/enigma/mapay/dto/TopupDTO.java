package com.enigma.mapay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TopupDTO {

    private String topupId;

    private Date topupDate;

    private String userName;
    private Integer Total;

    private List<TopupDetailDTO> topupDetailDTOS;
}
