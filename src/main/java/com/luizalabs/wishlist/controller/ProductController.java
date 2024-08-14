package com.luizalabs.wishlist.controller;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product) {

        ProductEntity productEntity = productService.add(product);

        return ResponseEntity.ok().body(productEntity);
    }
}
