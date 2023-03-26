package com.enigma.mapay.dto;

import com.enigma.mapay.entity.Topup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopupDetailDTO {

   private String method;
   private String status;
   private Integer saldoTopup;
   private Integer ppn;
   private Integer subTotal;
}
