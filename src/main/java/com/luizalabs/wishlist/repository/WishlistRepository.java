package com.luizalabs.wishlist.repository;

import com.luizalabs.wishlist.entity.WishlistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
    List<WishlistEntity> findAllById(Long id);
}
