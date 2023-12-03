package com.github.ki3lmigu3l.picpay.repository;

import com.github.ki3lmigu3l.picpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
