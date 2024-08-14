package com.luizalabs.wishlist.service.impl;
import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.exceptions.ExistsProductOnWishlistExeception;
import com.luizalabs.wishlist.exceptions.ProductNotExistsOnWishlistException;
import com.luizalabs.wishlist.exceptions.ProductNotFoundException;
import com.luizalabs.wishlist.exceptions.WishlistFullSizeException;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public WishlistEntity add(String productId) {

        final int MAXIMUM_SIZE = 20;

        var product = findProductById(productId);
        var wishlist = wishlistExists();

        productExistsOnWishlistException(productId, wishlist);

        if (wishlist.getProductsId().size() >= MAXIMUM_SIZE) {
            throw new WishlistFullSizeException();
        }

        wishlist.getProductsId().add(product);

        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistEntity deleteById(String productId) {

        var wishlist = wishlistExists();

        boolean productRemoved = wishlist.getProductsId().removeIf(prod -> prod.getId().equals(productId));

        if (!productRemoved) {
            throw new ProductNotExistsOnWishlistException();
        }

        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistEntity getAll() {
        return wishlistExists();
    }

    @Override
    public WishlistEntity getById(String productId) {

        var wishlist = wishlistExists();

        return productExistsOnWishlist(productId, wishlist);
    }

    public void productExistsOnWishlistException(String productId, WishlistEntity wishlist) {

        for (ProductEntity product : wishlist.getProductsId()) {
            if (product.getId().equals(productId)) {
                throw new ExistsProductOnWishlistExeception();
            }
        }

    }

    public WishlistEntity productExistsOnWishlist(String productId, WishlistEntity wishlist) {

        List<ProductEntity> matchedProducts = wishlist.getProductsId().stream()
                .filter(product -> product.getId().equals(productId))
                .collect(Collectors.toList());

        if (matchedProducts.isEmpty()) {
            throw new ProductNotExistsOnWishlistException();
        }

        wishlist.setProductsId(matchedProducts);

        return wishlist;
    }

    public WishlistEntity wishlistExists() {
        List<WishlistEntity> wishlists = wishlistRepository.findAll();

        if (wishlists.isEmpty()) {

            WishlistEntity newWishlist = new WishlistEntity();
            wishlistRepository.save(newWishlist);

            return newWishlist;
        } else {
            return wishlists.getFirst();
        }
    }

    protected ProductEntity findProductById(String productId) {

        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }
}
