package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_transfer_mapay_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Integer amount;

    private String transferTo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transfer_id")
    @JsonIgnoreProperties("transferDetail")
    private Transfer transfer;
    private String status;
}
