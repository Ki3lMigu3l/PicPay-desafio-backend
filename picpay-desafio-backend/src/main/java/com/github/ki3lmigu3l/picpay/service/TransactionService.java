package com.github.ki3lmigu3l.picpay.service;

import com.github.ki3lmigu3l.picpay.domain.transaction.Transaction;
import com.github.ki3lmigu3l.picpay.domain.transaction.TransactionDto;
import com.github.ki3lmigu3l.picpay.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private final UserService userService;
    private final RestTemplate restTemplate;
    private final TransactionRepository transactionRepository;
    private final NotificationService notificationService;

    public TransactionService(UserService userService, TransactionRepository transactionRepository, RestTemplate restTemplate, TransactionRepository transactionRepository1, NotificationService notificationService) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.transactionRepository = transactionRepository1;
        this.notificationService = notificationService;
    }


    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        var userPayer = userService.findUserById(transactionDto.userPayer());
        var userPayee = userService.findUserById(transactionDto.userPayee());

        userService.validateUser(userPayer, transactionDto.amount());

        boolean isAuthorize = this.authorizeTransaction();

        if (!isAuthorize) {
            throw new Exception("Unauthorized transaction.");
        }

        var transaction = new Transaction();
        transaction.setAmount(transactionDto.amount());
        transaction.setUserPayer(userPayer);
        transaction.setPayee(userPayee);
        transaction.setTransatcionTime(LocalDateTime.now());

        userPayer.setBalance(userPayer.getBalance().subtract(transactionDto.amount()));
        userPayee.setBalance(userPayee.getBalance().add(transactionDto.amount()));

        transactionRepository.save(transaction);
        userService.saveUser(userPayer);
        userService.saveUser(userPayee);

        notificationService.sendNotification(userPayer, "Transaction completed successfully.");
        notificationService.sendNotification(userPayee, "Transaction received successfully.");

        return transaction;
    }

    public boolean authorizeTransaction () {
        var response = restTemplate
                .getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);

        } else return false;

    }

}
