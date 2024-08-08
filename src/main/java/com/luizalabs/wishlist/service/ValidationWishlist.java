package com.luizalabs.wishlist.service;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.request.WishlistRequestDTO;

public interface ValidationWishlist {

    void valid(WishlistEntity data);
}
