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

package com.prathab.shopping.services.springdatajpa;

import com.prathab.shopping.domain.User;
import com.prathab.shopping.repositories.UserRepository;
import com.prathab.shopping.services.UserService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {

  private final UserRepository userRepository;

  public UserSDJpaService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public Set<User> findAll() {
    var set = new HashSet<User>();
    userRepository.findAll().forEach(set::add);
    return set;
  }

  @Override public User findById(Long aLong) {
    return userRepository.findById(aLong).get();
  }

  @Override public User save(User object) {
    return userRepository.save(object);
  }

  @Override public void delete(User object) {
    userRepository.delete(object);
  }

  @Override public void deleteById(Long aLong) {
    userRepository.deleteById(aLong);
  }

  @Override public Optional<User> findByEmail(String email) {
    return Optional.of(userRepository.findByEmail(email));
  }
}
