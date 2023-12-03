package com.github.ki3lmigu3l.picpay.controller;

import com.github.ki3lmigu3l.picpay.domain.transaction.Transaction;
import com.github.ki3lmigu3l.picpay.domain.transaction.TransactionDto;
import com.github.ki3lmigu3l.picpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (@RequestBody TransactionDto transactionDto) throws Exception {
        var transaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
