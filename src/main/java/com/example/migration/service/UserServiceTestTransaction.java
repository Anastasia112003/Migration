package com.example.migration.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceTestTransaction {
    private final UserService userService;

    public UserServiceTestTransaction(UserService userService) {
        this.userService = userService;
    }

    // @Transactional
    public void call(Long id, int amount) {
        userService.transferMoney(id, amount);
    }
}
