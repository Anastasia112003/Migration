package com.example.migration.service;

import com.example.migration.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User getById(Long id);

    List<User> readAll();

    Optional<User> read(Long id);

    boolean update(User user, Long id);

    boolean delete(Long id);

    void transferMoney(Long id, int amount);
}


