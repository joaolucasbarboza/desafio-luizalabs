package com.luizalabs.wishlist.response;

import com.luizalabs.wishlist.entity.ProductEntity;

public record ProductDTO(String id, String name) {

    public ProductDTO(ProductEntity product) {
        this(product.getId(), product.getName());
    }
}
