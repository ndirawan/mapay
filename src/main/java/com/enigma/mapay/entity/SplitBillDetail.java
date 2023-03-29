package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_split_bill_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SplitBillDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Integer splitAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userSplit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "split_bill_id")
    @JsonIgnoreProperties("SplitBillDetail")
    private SplitBill splitBill;

}
