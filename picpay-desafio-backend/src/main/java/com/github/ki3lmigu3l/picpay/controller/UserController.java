package com.github.ki3lmigu3l.picpay.controller;

import com.github.ki3lmigu3l.picpay.domain.user.User;
import com.github.ki3lmigu3l.picpay.domain.user.UserDTO;
import com.github.ki3lmigu3l.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody UserDTO userData) {
        User user = userService.createUser(userData);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        var usersList = this.userService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
