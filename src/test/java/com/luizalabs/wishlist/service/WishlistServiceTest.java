package com.luizalabs.wishlist.service;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.exceptions.ExistsProductOnWishlistExeception;
import com.luizalabs.wishlist.exceptions.ProductNotExistsOnWishlistException;
import com.luizalabs.wishlist.exceptions.WishlistFullSizeException;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.service.impl.WishlistServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    ProductRepository productRepository;

    private static WishlistEntity createProductOnWishllistEntities(int quantity) {

        List<ProductEntity> productEntities = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            productEntities.add(new ProductEntity("produto" + i, "Product " + i));
        }

        return new WishlistEntity("wishlist1", productEntities);
    }

    @Test
    @DisplayName("Deve retornar uma wishlist com todos os produtos")
    public void shouldReturnAWishlistWithAllProducts() {

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(2);

        when(wishlistRepository.findAll())
                .thenReturn(List.of(wishlistEntity));

        assertEquals(wishlistEntity, wishlistService.getAll());
    }

    @Test
    @DisplayName("Deve lançar exceção quando existir produto na lista.")
    void shouldThrowExceptionWhenProductExistsInWishlist() {

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(3);

        lenient().when(wishlistRepository.findById("wishlist1"))
                .thenReturn(Optional.of(wishlistEntity));

        assertThrows(ExistsProductOnWishlistExeception.class, () -> {
            wishlistService.productExistsOnWishlistException("produto2", wishlistEntity);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando não existir produto na lista.")
    void shouldThrowExceptionProductNotFoundOnWishlist() {

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(2);

        lenient().when(wishlistRepository.findById("wishlist1"))
                .thenReturn(Optional.of(wishlistEntity));

        assertThrows(ProductNotExistsOnWishlistException.class, () -> {
            wishlistService.productExistsOnWishlist("produto2", wishlistEntity);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando a lista estiver cheia.")
    void ShouldThrowExceptionWhenWishlistIsFull() {
        final int maxProducts = 20;

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(maxProducts);

        when(wishlistRepository.findAll())
                .thenReturn(List.of(wishlistEntity));

        ProductEntity newProduct = new ProductEntity("produto21", "Product 21");
        when(productRepository.findById("produto21"))
                .thenReturn(Optional.of(newProduct));

        assertThrows(WishlistFullSizeException.class, () -> {
            wishlistService.add(newProduct.getId());
        });

        verify(wishlistRepository, never()).save(wishlistEntity);
    }

    @Test
    @DisplayName("Deve buscar o produto pelo id na wishlist.")
    void shouldReturnProduct() {

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(2);

        when(wishlistRepository.findAll())
                .thenReturn(List.of(wishlistEntity));

        assertEquals(wishlistService.getById("produto1"), wishlistEntity);
    }

    @Test
    @DisplayName("Deve fazer a exclusão do produto pelo id na Wishlist")
    void youMustDeleteTheProductByIdInTheWishlist() {

        WishlistEntity wishlistEntity = createProductOnWishllistEntities(2);

        when(wishlistRepository.findAll())
                .thenReturn(List.of(wishlistEntity));

        wishlistService.deleteById("produto1");

        assertEquals(1, wishlistEntity.getProductsId().size());

        verify(wishlistRepository).save(wishlistEntity);
    }
}
