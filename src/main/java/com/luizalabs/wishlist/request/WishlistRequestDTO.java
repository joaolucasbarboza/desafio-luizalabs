package com.luizalabs.wishlist.request;

import jakarta.validation.constraints.NotNull;

public record WishlistRequestDTO(

        @NotNull
        String productId
) {
}
