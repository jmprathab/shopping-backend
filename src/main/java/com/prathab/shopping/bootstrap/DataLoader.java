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

package com.prathab.shopping.bootstrap;

import com.prathab.shopping.domain.Product;
import com.prathab.shopping.domain.User;
import com.prathab.shopping.repositories.ProductRepository;
import com.prathab.shopping.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public DataLoader(UserRepository userRepository,
      ProductRepository productRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @Override public void run(String... args) throws Exception {
    var name = "Prathab";
    var email = "jm.prathab@gmail.com";
    var password = "password";
    var user = new User(name, email, password);
    userRepository.save(user);

    var productName = "Dog Food";
    var productDescription = "Best dog food";
    var price = "100";
    var product = new Product(productName, productDescription, price);
    productRepository.save(product);
  }
}
