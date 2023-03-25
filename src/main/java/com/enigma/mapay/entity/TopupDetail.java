package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trx_topup_detail")
@Getter
@Setter
@NoArgsConstructor
public class TopupDetail {

    private String topupDetailId;

    private Integer topupBalance;
    private String methode;
    @ManyToOne
    @JoinColumn(name = "topup_id")
    @JsonIgnoreProperties("PurchaseDetail")
    private Topup topup;

    private String status = "pending";

}
