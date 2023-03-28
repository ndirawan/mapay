package com.enigma.mapay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mst_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumb;
    private String fullName;
    private String address;
    private LocalDate birthDate;
    @Column(nullable = true)
    private String profilePicture;
    private Integer status;
    private Integer balance = 0;
    private Integer mapay_point = 0;
}
