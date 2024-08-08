package com.luizalabs.wishlist.exceptions;

public class WishlistNotFoundException extends RuntimeException {

    public WishlistNotFoundException() {
        super("Wishlist not found.");
    }

    public WishlistNotFoundException(String message) {
        super(message);
    }
}
