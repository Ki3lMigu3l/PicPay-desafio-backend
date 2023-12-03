package com.github.ki3lmigu3l.picpay.service;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import com.github.ki3lmigu3l.picpay.domain.user.UserDTO;
import com.github.ki3lmigu3l.picpay.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void saveUser(User user) {
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
}
