package com.github.ki3lmigu3l.picpay.domain.user;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;
    private String password;
    private UserType userType;
    private BigDecimal balance;


}
