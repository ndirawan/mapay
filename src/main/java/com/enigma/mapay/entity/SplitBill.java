package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_split_bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SplitBill {

    @Id
    @Column(name="split_bill_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @CreationTimestamp
    private Date splitDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCreate;
    private Integer totalAmount;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "split_bill_detail_id")
    @JsonIgnoreProperties("splitBill")
    private List<SplitBillDetail> splitBillDetail = new ArrayList<>();
}
