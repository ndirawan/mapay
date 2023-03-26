package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_topup")
@Getter
@Setter
@NoArgsConstructor
public class Topup {
    @Id
    @Column(name="topup_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String topupId;

    private Date topupDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "topup")
    @JsonIgnoreProperties("topup")
    private List<TopupDetail> topupDetails = new ArrayList<>();
}
