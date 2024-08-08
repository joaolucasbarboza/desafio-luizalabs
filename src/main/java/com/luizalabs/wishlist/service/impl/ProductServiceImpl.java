package com.luizalabs.wishlist.service.impl;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity add(ProductEntity productEntity) {
        return this.productRepository.save(productEntity);
    }
}
