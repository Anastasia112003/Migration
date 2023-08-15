package com.example.migration.service;

import com.example.migration.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    List<User> readAll();
    Optional<User> read(Long id);

    boolean update(User user, Long id);

    boolean delete(Long id);
}

