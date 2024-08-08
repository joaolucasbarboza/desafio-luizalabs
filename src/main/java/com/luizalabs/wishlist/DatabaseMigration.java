package com.luizalabs.wishlist;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMigration {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void createSchema() {
        if (!mongoTemplate.collectionExists("wishlist")) {
            mongoTemplate.createCollection("wishlist");
            System.out.println("Collection wishlist created");
        } else {
            System.out.println("Collection wishlist not created");
        }

        if (!mongoTemplate.collectionExists("product")) {
            mongoTemplate.createCollection("product");
            System.out.println("Collection product created");
        } else {
            System.out.println("Collection product not created");
        }
    }
}

