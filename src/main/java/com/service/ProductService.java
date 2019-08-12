package com.service;

import com.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();

    void delete(Long id);

    Optional<Product> getById(Long id);

    void update (Product newProduct);

    void add(Product product);

}
