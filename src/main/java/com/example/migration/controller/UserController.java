package com.example.migration.controller;

import com.example.migration.model.User;
import com.example.migration.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        User userSave = userService.create(user);
        return ResponseEntity.status(201).body(userSave);
    }

    @GetMapping("/all")
    public ResponseEntity <Collection<User> >findAll() {
        Collection<User> users = userService.readAll();
         return ResponseEntity.ok().body(users);
    }



}
