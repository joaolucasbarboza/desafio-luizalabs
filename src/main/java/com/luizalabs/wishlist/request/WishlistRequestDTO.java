package com.luizalabs.wishlist.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WishlistRequestDTO(

        @NotNull(message = "O ID do produto não pode ser nulo.")
        List<String> productsId
) {
}
