package com.service.impl;

import com.dao.ProductDao;
import com.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productDao.remove(id);
    }

    @Transactional
    @Override
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    @Transactional
    @Override
    public void update(Product newProduct) {
        productDao.update(newProduct);
    }

    @Transactional
    @Override
    public void add(Product product) {
        String name = product.getName();
        String description = product.getDescription();
        Double price = product.getPrice();
        Product productToAdd = new Product(name, description, price);
        productDao.add(productToAdd);
    }
}
