package com.luizalabs.wishlist.response;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;

import java.util.List;

public record WishlistDTO(String id, List<ProductEntity> products) {

    public WishlistDTO(WishlistEntity wishlistEntity) {
        this(wishlistEntity.getId(), wishlistEntity.getProductsId());
    }
}
