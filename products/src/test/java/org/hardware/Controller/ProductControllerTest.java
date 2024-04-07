package org.hardware.Controller;

import org.hardware.Entity.Products;
import org.hardware.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService mockproductService;

    @InjectMocks
    private ProductController productController;


    @Test
    void testCreateProduct() {
        UUID productId = UUID.randomUUID();
        Products products = new Products();
        products.setId(productId);
        products.setName("Saree");
        products.setPrice(2000);
        products.setDescription("This is a typical Indian saree");
        products.setQtyInStocks(20);

        Mockito.lenient().when(mockproductService.createProduct(any(Products.class))).thenReturn(products);
        Products savedProducts = new Products();

        savedProducts.setId(productId);
        savedProducts.setName("Saree");
        savedProducts.setPrice(2000);
        savedProducts.setDescription("This is a typical Indian saree");
        savedProducts.setQtyInStocks(20);

//        when(mockproductService.createProduct(any(Products.class))).thenReturn(products);
        assertEquals(savedProducts,products);


    }

    @Test
    void testFindProductById() {
    }

    @Test
    void testDeleteProductById() {
    }

    @Test
    void testUpdateProductById() {
    }
}