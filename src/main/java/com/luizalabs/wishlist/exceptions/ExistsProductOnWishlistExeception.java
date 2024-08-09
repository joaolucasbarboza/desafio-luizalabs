package com.luizalabs.wishlist.exceptions;

public class ExistsProductOnWishlistExeception extends RuntimeException {

    public ExistsProductOnWishlistExeception() {
        super("Product already exists.");
    }

    public ExistsProductOnWishlistExeception(String message) {
        super(message);
    }
}