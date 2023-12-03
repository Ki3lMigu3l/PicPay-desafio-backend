package com.github.ki3lmigu3l.picpay.service;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        System.out.println(email + ":" + message);
    }
}
