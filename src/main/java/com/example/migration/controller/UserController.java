package com.example.migration.controller;

import com.example.migration.model.User;
import com.example.migration.service.UserService;
import com.example.migration.service.UserServiceTestTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class UserController {
    private final UserService userService;
    private final UserServiceTestTransaction userServiceTestTransaction;

    public UserController(UserService userService, UserServiceTestTransaction userServiceTestTransaction) {
        this.userService = userService;
        this.userServiceTestTransaction = userServiceTestTransaction;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        User userSave = userService.create(user);
        return ResponseEntity.status(201).body(userSave);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<User>> findAll() {
        Collection<User> users = userService.readAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/{id}/{amount}")
    public void transfer(@PathVariable long id, @PathVariable int amount) {
        userService.transferMoney(id, amount);

    }
//    @PostMapping("/{id}/{amount}")
//    public void transactionTest(@PathVariable long id ,@PathVariable int amount){
//        userServiceTestTransaction.call(id, amount);
//
//    }
}



