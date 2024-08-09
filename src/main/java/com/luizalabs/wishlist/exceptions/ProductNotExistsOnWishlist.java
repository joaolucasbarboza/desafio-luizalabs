package com.luizalabs.wishlist.exceptions;

public class ProductNotExistsOnWishlist extends RuntimeException {

    public ProductNotExistsOnWishlist() {
        super("Product not exists on wishlist.");
    }

    public ProductNotExistsOnWishlist(String message) {
        super(message);
    }
}
