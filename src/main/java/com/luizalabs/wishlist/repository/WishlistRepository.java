package com.luizalabs.wishlist.repository;

import com.luizalabs.wishlist.entity.WishlistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
}
