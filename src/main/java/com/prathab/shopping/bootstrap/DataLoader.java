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

import com.prathab.shopping.domain.Image;
import com.prathab.shopping.domain.Product;
import com.prathab.shopping.domain.User;
import com.prathab.shopping.repositories.ImageRepository;
import com.prathab.shopping.repositories.ProductRepository;
import com.prathab.shopping.repositories.UserRepository;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final ImageRepository imageRepository;

  public DataLoader(UserRepository userRepository,
      ProductRepository productRepository,
      ImageRepository imageRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.imageRepository = imageRepository;
  }

  @Override public void run(String... args) throws Exception {
    var name = "Prathab";
    var email = "jm.prathab@gmail.com";
    var password = "password";
    var user = new User(name, email, password);
    userRepository.save(user);

    var productName = "Dog Food";
    var productDescription = "Best dog food";
    var product = new Product(productName, productDescription, new HashSet<>());
    var savedProduct = productRepository.save(product);
    var bytes = Files.readAllBytes(
        Paths.get(getClass().getResource("/bootstrap/bootstrap_dog_image.jpg").getPath()));
    var image = new Image(bytes, savedProduct);
    imageRepository.save(image);
  }
}
