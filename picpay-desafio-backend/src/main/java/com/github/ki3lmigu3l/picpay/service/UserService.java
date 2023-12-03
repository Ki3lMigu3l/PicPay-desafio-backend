package com.github.ki3lmigu3l.picpay.service;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import com.github.ki3lmigu3l.picpay.domain.user.UserDTO;
import com.github.ki3lmigu3l.picpay.domain.user.UserType;
import com.github.ki3lmigu3l.picpay.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userData) {
        var user = new User();
        BeanUtils.copyProperties(userData, user);

        this.saveUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById (UUID id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found!"));
    }

    public boolean validateUser (User payer, BigDecimal amount) throws Exception {

        if (payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant users cannot perform transfers.");
        }

        if (payer.getBalance().compareTo(amount) < 0) {
            throw new Exception("It's not possible to complete the transfer. Insufficient balance.");
        }

        return true;
    }
}
