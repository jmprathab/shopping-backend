/*
 * Copyright 2020 Prathab Murugan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prathab.shopping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prathab.shopping.api.model.UserDTO;
import com.prathab.shopping.domain.User;
import com.prathab.shopping.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.prathab.shopping.controllers.AccountsController.ENDPOINT;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountsController.class)
class AccountsControllerTest {

  @Mock
  UserService userService;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createAccountTest() throws Exception {
    var email = "abc@xyz.com";
    var mobile = "8110031139";
    var name = "User";
    var password = "password";

    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(email);
    userDTO.setMobile(mobile);
    userDTO.setName(name);
    userDTO.setPassword(password);

    User user = new User();
    user.setId(1L);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setName(name);
    user.setPassword("");

    when(userService.save(ArgumentMatchers.any())).thenReturn(user);

    mockMvc.perform(
        post(ENDPOINT)
            .content(objectMapper.writeValueAsString(userDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", equalTo(1L)))
        .andExpect(jsonPath("$.name", equalTo(name)))
        .andExpect(jsonPath("$.email", equalTo(email)))
        .andExpect(jsonPath("$.mobile", equalTo(mobile)))
        .andExpect(jsonPath("$.password", equalTo(""))); // TODO Remove password from result
  }
}