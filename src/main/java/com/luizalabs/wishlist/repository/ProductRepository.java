package com.luizalabs.wishlist.repository;

import com.luizalabs.wishlist.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
