package com.enigma.mapay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trx_withdraw_detail_mapay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Integer amount;

    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "last_withdrawal_time")
    private LocalDateTime lastWithdrawalTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "withdraw_id")
    @JsonIgnoreProperties("transferDetail")
    private Withdraw withdraw;
    private String codeWithdraw;
    private String status;
}
