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

package com.prathab.shopping.api.mapper;

import com.prathab.shopping.api.model.UserSignUpDTO;
import com.prathab.shopping.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserSignUpMapperTest {

  private final Long id = 1L;
  private final String name = "User";
  private final String email = "abc.xyz.com";
  private final String password = "password";

  @Autowired
  private UserSignUpMapper userMapper;

  @Test
  void userToUserDto() {
    //given
    var user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);

    //when
    var userDto = userMapper.userToUserDto(user);

    //then
    assertEquals(userDto.getEmail(), email);
  }

  @Test
  void userDtoToUser() {
    //given
    var userDto = new UserSignUpDTO();
    userDto.setName(name);
    userDto.setEmail(email);
    userDto.setPassword(password);

    //when
    var user = userMapper.userDtoToUser(userDto);

    //then
    assertEquals(user.getEmail(), email);
  }
}