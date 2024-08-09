package com.luizalabs.wishlist.exceptions;

public class ExistsProductOnWishlist extends RuntimeException {

    public ExistsProductOnWishlist() {
        super("Product already exists.");
    }

    public ExistsProductOnWishlist(String message) {
        super(message);
    }
}