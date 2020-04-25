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

import com.prathab.shopping.domain.Product;
import com.prathab.shopping.repositories.ProductRepository;
import com.prathab.shopping.services.ProductService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class ProductSDJpaService implements ProductService {
  private final ProductRepository productRepository;

  public ProductSDJpaService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override public Set<Product> findAll() {
    var result = new HashSet<Product>();
    productRepository.findAll().forEach(result::add);
    return result;
  }

  @Override public Product findById(Long aLong) {
    return productRepository.findById(aLong).get();
  }

  @Override public Product save(Product object) {
    return productRepository.save(object);
  }

  @Override public void delete(Product object) {
    productRepository.delete(object);
  }

  @Override public void deleteById(Long aLong) {
    productRepository.deleteById(aLong);
  }
}
