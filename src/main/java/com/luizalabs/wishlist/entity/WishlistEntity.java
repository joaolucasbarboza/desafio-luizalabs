package com.luizalabs.wishlist.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistEntity {

    @Id
    private String id;
    private List<ProductEntity> produtos = new ArrayList<>();

}
