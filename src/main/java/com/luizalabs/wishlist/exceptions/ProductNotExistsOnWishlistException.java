package com.luizalabs.wishlist.exceptions;

public class ProductNotExistsOnWishlistException extends RuntimeException {

    public ProductNotExistsOnWishlistException() {
        super("Product not exists on wishlist.");
    }

    public ProductNotExistsOnWishlistException(String message) {
        super(message);
    }
}
