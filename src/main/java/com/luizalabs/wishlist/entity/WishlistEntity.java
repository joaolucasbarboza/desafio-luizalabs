package com.luizalabs.wishlist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "wishlist")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class WishlistEntity {

    @Id
    private String id;

    @DBRef
    private List<ProductEntity> productsId = new ArrayList<>();

}
