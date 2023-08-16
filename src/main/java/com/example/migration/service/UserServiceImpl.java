package com.example.migration.service;

import com.example.migration.model.User;
import com.example.migration.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        User saveUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .money(user.getMoney())
                .build();
        return userRepository.save(saveUser);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found"));
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found")));
    }

    @Override
    public boolean update(User user, Long id) {
        Optional<User> optionalUser = read(user.getId());
        if (optionalUser.isEmpty()) {
            return false;
        }
        User getUser = optionalUser.get();
        getUser.setName(user.getName());
        getUser.setEmail(user.getEmail());
        getUser.setMoney(user.getMoney());
        userRepository.save(getUser);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public void transferMoney(Long id, int amount) {
        User user = getById(id);
        if (user.getMoney() > 0 || user.getMoney() > amount) {
            int newMoney = user.getMoney() - amount;
            user.setMoney(newMoney);
            userRepository.save(user);
            throw new RuntimeException("Problem executing transaction.");
        }
    }
}
