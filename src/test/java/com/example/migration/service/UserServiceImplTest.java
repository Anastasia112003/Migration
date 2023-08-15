package com.example.migration.service;

import com.example.migration.TestConfig;
import com.example.migration.model.User;
import com.example.migration.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

        @Autowired
        private UserServiceImpl userService;
        @Autowired
        private UserRepository userRepository;

    @Test
    void create() {
        User user = User.builder()
                .name("Vladimir")
                .email("vladimir9876@mail.ru")
                .money(900)
                .build();
        User expected = User.builder()
                .id(8l)
                .name("Vladimir")
                .email("vladimir9876@mail.ru")
                .money(900)
                .build();
        User actual = userService.create(user);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        User user = new User();
        user.setName("Kate");
        user.setEmail("kate12@gmail.com");
        user.setMoney(200);

        userRepository.save(user);

        Collection<User> employeeList = userRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(9);
    }

    @Test
    void read() {
        User user = new User();
        user.setName("Kate");
        user.setEmail("ekaterina17@gmail.com");
        user.setMoney(2);
       userRepository.save(user);

       User user1 = userRepository.findById(user.getId()).get();

        assertThat(user1).isNotNull();
    }

    @Test
    void update() {
        User user = new User();
        user.setName("Kate");
        user.setEmail("ekaterina17@gmail.com");
        user.setMoney(2);
        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).get();
        savedUser.setName("Kate");
        savedUser.setEmail("kate12@gmail.com");
        savedUser.setMoney(200);
        User updatedCandidate = userRepository.save(savedUser);

        assertThat(updatedCandidate.getEmail()).isEqualTo("kate12@gmail.com");
        assertThat(updatedCandidate.getName()).isEqualTo("Kate");

    }

    @Test
    void delete() {
        User user = new User();
        user.setName("Kate");
        user.setEmail("kate12@gmail.com");
        user.setMoney(200);
        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<User> candidateOptional = userRepository.findById(user.getId());

        assertThat(candidateOptional).isEmpty();
    }
}