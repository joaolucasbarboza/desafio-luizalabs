package com.luizalabs.wishlist.service;

import com.luizalabs.wishlist.entity.WishlistEntity;

public interface WishlistService {

    public WishlistEntity add(String productId);
    public WishlistEntity deleteById(String productId);
}
