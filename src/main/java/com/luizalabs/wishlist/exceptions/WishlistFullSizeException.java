package com.luizalabs.wishlist.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class WishlistFullSizeException extends RuntimeException {

    public WishlistFullSizeException() {
        super("Wishlist full size.");
    }

    public WishlistFullSizeException(String message) {
        super(message);
    }

}
