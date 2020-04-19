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

package com.prathab.shopping.api.v1.mapper;

import com.prathab.shopping.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

  @Test
  void userToUserDto() {

    var id = 1L;
    var name = "User";
    var email = "abc.xyz.com";
    var mobile = "1234567890";
    var password = "password";

    //given
    var user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setPassword(password);

    //when
    var userDto = UserMapper.INSTANCE.userToUserDto(user);

    //then
    assertEquals(userDto.getEmail(), email);
  }

  @Test
  void userDtoToUser() {
  }
}