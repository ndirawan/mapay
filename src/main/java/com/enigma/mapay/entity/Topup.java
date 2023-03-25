package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_topup")
@Getter
@Setter
@NoArgsConstructor
public class Topup {
    private String topupId;

    private Date topupDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "topup")
    @JsonIgnoreProperties("TopupDetail")
    private List<TopupDetail> topupDetails = new ArrayList<>();
}
