package com.luizalabs.wishlist.service;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.request.WishlistRequestDTO;

public interface ProductService {

    public ProductEntity add(ProductEntity productEntity);
}
