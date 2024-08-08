package com.luizalabs.wishlist.service.wishlistValidation;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.exceptions.WishlistFullSizeException;
import com.luizalabs.wishlist.service.ValidationWishlist;
import org.springframework.stereotype.Component;

@Component
public class MaximumSize implements ValidationWishlist {

    private static final int MAXIMUM_SIZE = 20;

    @Override
    public void valid(WishlistEntity data) {
        if (data.getProductsId().size() >= MAXIMUM_SIZE ) {
            throw new WishlistFullSizeException();
        }
    }
}
