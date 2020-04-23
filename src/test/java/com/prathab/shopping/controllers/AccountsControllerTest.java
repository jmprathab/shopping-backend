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
import com.prathab.shopping.api.mapper.UserLoginMapper;
import com.prathab.shopping.api.mapper.UserSignUpMapper;
import com.prathab.shopping.api.model.UserLoginDTO;
import com.prathab.shopping.api.model.UserSignUpDTO;
import com.prathab.shopping.domain.User;
import com.prathab.shopping.services.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.prathab.shopping.controllers.AccountsController.CREATE_ACCOUNT;
import static com.prathab.shopping.controllers.AccountsController.ENDPOINT;
import static com.prathab.shopping.controllers.AccountsController.LOGIN;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

  @MockBean
  UserService userService;
  @MockBean
  UserSignUpMapper userSignUpMapper;

  @MockBean
  UserLoginMapper userLoginMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createAccountTest() throws Exception {
    //when
    var email = "abc@xyz.com";
    var name = "User";
    var password = "password";

    var userDTO = new UserSignUpDTO();
    userDTO.setEmail(email);
    userDTO.setName(name);
    userDTO.setPassword(password);

    var user = new User();
    user.setId(Long.MAX_VALUE);
    user.setEmail(email);
    user.setName(name);
    user.setPassword("");

    when(userService.save(ArgumentMatchers.any(User.class))).thenReturn(user);
    when(userSignUpMapper.userDtoToUser(ArgumentMatchers.any(UserSignUpDTO.class))).thenReturn(
        user);
    when(userSignUpMapper.userToUserDto(ArgumentMatchers.any(User.class))).thenReturn(userDTO);

    //then
    mockMvc.perform(
        post(ENDPOINT + CREATE_ACCOUNT)
            .content(objectMapper.writeValueAsString(userDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", equalTo(Long.MAX_VALUE)))
        .andExpect(jsonPath("$.name", equalTo(name)))
        .andExpect(jsonPath("$.email", equalTo(email)))
        .andExpect(jsonPath("$.password", equalTo("")));
  }

  @Test
  public void loginTest() throws Exception {
    //when
    var email = "abc@xyz.com";
    var name = "User";
    var password = "password";

    var userDTO = new UserLoginDTO();
    userDTO.setEmail(email);
    userDTO.setPassword(password);

    var user = new User();
    user.setId(Long.MAX_VALUE);
    user.setEmail(email);
    user.setName(name);
    user.setPassword("");

    when(userService.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(user));
    when(userLoginMapper.userDtoToUser(ArgumentMatchers.any(UserLoginDTO.class))).thenReturn(user);
    when(userLoginMapper.userToUserDto(ArgumentMatchers.any(User.class))).thenReturn(userDTO);

    //then
    mockMvc.perform(
        post(ENDPOINT + LOGIN)
            .content(objectMapper.writeValueAsString(userDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(Long.MAX_VALUE)))
        .andExpect(jsonPath("$.name", equalTo(name)))
        .andExpect(jsonPath("$.email", equalTo(email)))
        .andExpect(jsonPath("$.password", equalTo("")));
  }
}