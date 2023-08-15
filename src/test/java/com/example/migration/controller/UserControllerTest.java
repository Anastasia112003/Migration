package com.example.migration.controller;

import com.example.migration.TestConfig;
import com.example.migration.model.User;
import com.example.migration.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc

class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @AfterEach
    public void resetDb() {
       userRepository.deleteAll();
    }


    @Test
    public void crateTest() throws Exception {

        User user = new User(1L,"Vladimir","vladimir9876@mail.ru",900);

        mockMvc.perform(
                        post("/save")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1))
                .andExpect((ResultMatcher) jsonPath("$.email").value("vladimir9876@mail.ru"))
                .andExpect((ResultMatcher) jsonPath("$.money").value(900))
                .andExpect((ResultMatcher) jsonPath("$.name").value("Michail"));
    }

    @Test
    public void findAllTest() throws Exception {
        User user1 = createTestPerson(1L,"Vladimir","vladimir9876@mail.ru",900);
        User user2 =createTestPerson( 2L,"Olga","ole4ka777@mail.ru",1230);
        mockMvc.perform(
                        get("/all"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(Arrays.asList(user1, user2))));
    }
    private User createTestPerson(Long id,String name, String email, Integer money) {
        User emp = new User(id,name,email,money);
        return userRepository.save(emp);
    }
        }



