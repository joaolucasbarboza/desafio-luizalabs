package com.luizalabs.wishlist.service.impl;
import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.exceptions.ExistsProductOnWishlist;
import com.luizalabs.wishlist.exceptions.ProductNotFoundException;
import com.luizalabs.wishlist.exceptions.WishlistNotFoundException;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.ValidationWishlist;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private List<ValidationWishlist> validationWishlists;

    @Override
    public WishlistEntity add(String productId) {

        var product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        var wishlist = wishlistRepository.findById("66b4eefff40c3c8dbd7f50ef")
                .orElseThrow(WishlistNotFoundException::new);

        for (ProductEntity prod : wishlist.getProductsId()) {
            if (prod.getId().equals(productId)) {
                throw new ExistsProductOnWishlist();
            }
        }

        validationWishlists.forEach(v -> v.valid(wishlist));

        wishlist.getProductsId().add(product);

        return this.wishlistRepository.save(wishlist);
    }
}
