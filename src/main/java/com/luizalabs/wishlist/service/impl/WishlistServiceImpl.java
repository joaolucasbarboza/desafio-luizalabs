package com.luizalabs.wishlist.service.impl;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.request.WishlistRequestDTO;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductRepository productRepository;

    WishlistEntity wishlistEntity;


    @Override
    public WishlistEntity add(WishlistRequestDTO wishlistRequestDTO) {

        var product = productRepository.findById(wishlistRequestDTO.productId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        System.out.println(product);

//        wishlistEntity.setProductsId();

        return null;
    }
}
