package com.luizalabs.wishlist.repository;

import com.luizalabs.wishlist.entity.ProdutoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<ProdutoEntity, Long> {
}
