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

import com.prathab.shopping.api.mapper.ProductMapper;
import com.prathab.shopping.api.model.ProductDTO;
import com.prathab.shopping.domain.Product;
import com.prathab.shopping.repositories.ProductRepository;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prathab.shopping.controllers.ProductsController.ENDPOINT;

@Slf4j
@RestController
@RequestMapping(ENDPOINT)
public class ProductsController {
  public static final String ENDPOINT = "/api/v1";
  public static final String PRODUCT = "/product";

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductsController(ProductRepository productRepository,
      ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @GetMapping(PRODUCT)
  public Set<Product> getProduct() {
    var result = new HashSet<Product>();
    productRepository.findAll().forEach(result::add);
    return result;
  }

  @GetMapping(PRODUCT + "/{id}")
  public Product getProductById(@PathVariable("id") Long id) {
    return productRepository.findById(id).get();
  }

  @PostMapping(PRODUCT + "/{id}")
  public Product updateProductById(@PathVariable("id") Long id,
      @Valid @RequestBody ProductDTO productDTO) {
    Product newProduct = productMapper.productDtoToProduct(productDTO);
    newProduct.setId(id);
    return productRepository.save(newProduct);
  }
}
