package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_topup_detail")
@Getter
@Setter
@NoArgsConstructor
public class TopupDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String topupDetailId;

    private Integer topupBalance;
    private String methode;
    @ManyToOne
    @JoinColumn(name = "topup_id")
    @JsonIgnoreProperties("TopupDetail")
    private Topup topup;

    private String status = "pending";

}
