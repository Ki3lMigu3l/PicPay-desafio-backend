package com.github.ki3lmigu3l.picpay.domain.transaction;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transactions")
@Table(name = "transactions_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User userPayer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @Column
    private LocalDateTime transatcionTime;
}
