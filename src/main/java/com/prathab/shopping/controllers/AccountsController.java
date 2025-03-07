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

import com.prathab.shopping.api.mapper.UserLoginMapper;
import com.prathab.shopping.api.mapper.UserSignUpMapper;
import com.prathab.shopping.api.model.UserLoginDTO;
import com.prathab.shopping.api.model.UserSignUpDTO;
import com.prathab.shopping.domain.User;
import com.prathab.shopping.services.UserService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.prathab.shopping.controllers.AccountsController.ENDPOINT;

@Slf4j
@RestController
@RequestMapping(ENDPOINT)
public class AccountsController {
  public static final String ENDPOINT = "/api/v1/accounts";
  public static final String CREATE_ACCOUNT = "/create";
  public static final String LOGIN = "/login";

  private final UserService userService;
  private final UserSignUpMapper userSignUpMapper;
  private final UserLoginMapper userLoginMapper;

  public AccountsController(UserService userService,
      UserSignUpMapper userSignUpMapper,
      UserLoginMapper userLoginMapper) {
    this.userService = userService;
    this.userSignUpMapper = userSignUpMapper;
    this.userLoginMapper = userLoginMapper;
  }

  @PostMapping(value = CREATE_ACCOUNT)
  @ResponseStatus(HttpStatus.CREATED)
  User createAccount(@Valid @RequestBody UserSignUpDTO userDTO) {
    var user = userSignUpMapper.userDtoToUser(userDTO);
    userService.save(user);
    return user;
  }

  @PostMapping(value = LOGIN)
  @ResponseStatus(HttpStatus.OK)
  User login(@Valid @RequestBody UserLoginDTO userDTO) {
    var user = userLoginMapper.userDtoToUser(userDTO);
    return userService.findByEmail(user.getEmail()).get();
  }
}
