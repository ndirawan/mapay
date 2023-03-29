package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_buy_pulsa_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyPulsaDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String type;
    private String sn;
    private String pulsaCode;
    private Integer price;
    private String status;
}
