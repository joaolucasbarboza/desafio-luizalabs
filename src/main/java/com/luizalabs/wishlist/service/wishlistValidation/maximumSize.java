package com.luizalabs.wishlist.service.wishlistValidation;

import com.luizalabs.wishlist.request.WishlistRequestDTO;
import com.luizalabs.wishlist.service.ValidationWishlist;
import jakarta.validation.ValidationException;

public class maximumSize implements ValidationWishlist {

    private static final int MAXIMUM_SIZE = 20;

    @Override
    public void valid(WishlistRequestDTO data) {
        if (data.projectsId().size() > MAXIMUM_SIZE ) {
            throw new ValidationException("A wishlist não pode conter mais do que " + MAXIMUM_SIZE + " produtos.");
        }
    }
}
