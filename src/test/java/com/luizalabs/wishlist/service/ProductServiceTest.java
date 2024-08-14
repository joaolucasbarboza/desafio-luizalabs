package com.luizalabs.wishlist.service;

import com.luizalabs.wishlist.entity.ProductEntity;
import com.luizalabs.wishlist.repository.ProductRepository;
import com.luizalabs.wishlist.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("Deve cadastrar um produto com sucesso.")
    void createProduct() {

        ProductEntity product = new ProductEntity("product1", "Name product 1");

        productService.add(product);

        verify(productRepository).save(product);
    }
}