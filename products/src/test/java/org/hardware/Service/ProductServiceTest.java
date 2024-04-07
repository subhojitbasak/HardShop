package org.hardware.Service;

import org.hardware.Entity.Products;
import org.hardware.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any; // if needed


import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository mockProductRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void testCreateProduct() {
        Products products = new Products();

        products.setName("Saree");
        products.setPrice(2000);
        products.setDescription("This is a typical Indian saree");
        products.setQtyInStocks(20);


        Products savedProducts = new Products();

        savedProducts.setId(UUID.randomUUID());
        savedProducts.setName("Saree");
        savedProducts.setPrice(2000);
        savedProducts.setDescription("This is a typical Indian saree");
        savedProducts.setQtyInStocks(20);

        when(mockProductRepository.save(any(Products.class))).thenReturn(savedProducts);

        Products inputProduct = productService.createProduct(products);
        System.out.println("testing");
        System.out.println(inputProduct.getId());


        assertEquals(inputProduct, savedProducts);

        verify(mockProductRepository, times(1)).save(any(Products.class));
    }

    @Test
    void testFindProductById() {
        Products products = new Products();
        UUID productId = UUID.randomUUID();
        products.setId(productId);
        products.setName("Saree");
        products.setPrice(2000);
        products.setDescription("This is a typical Indian saree");
        products.setQtyInStocks(20);

        Products savedValue = new Products();
        savedValue.setId(productId);

        savedValue.setName("Saree");
        savedValue.setPrice(2000);
        savedValue.setDescription("This is a typical Indian saree");
        savedValue.setQtyInStocks(20);
        Mockito.lenient().when(mockProductRepository.findById(productId)).thenReturn(Optional.of(products));

        assertEquals(products, savedValue);

    }

    @Test
    void testDeleteProductById() {
        Products products = new Products();
        UUID productId = UUID.randomUUID();
        products.setId(productId);
        products.setName("Saree");
        products.setPrice(2000);
        products.setDescription("This is a typical Indian saree");
        products.setQtyInStocks(20);
        Products savedProduct = productService.deleteProductById(productId);

        verify(mockProductRepository, times(1)).deleteById(productId);

//        verify(log).info("Deleted product : " + productId)/;
        verifyNoMoreInteractions(mockProductRepository);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Products existingProduct = new Products();
        UUID productId = UUID.randomUUID();
        existingProduct.setId(productId);
        existingProduct.setName("Saree");
        existingProduct.setPrice(2000);
        existingProduct.setDescription("This is a typical Indian saree");
        existingProduct.setQtyInStocks(20);
        Mockito.lenient().when(mockProductRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        existingProduct.setName("Pajama");
        existingProduct.setPrice(1500);
        existingProduct.setDescription("This is a Pajama not saree");
        existingProduct.setQtyInStocks(10);

//        System.out.println("hi testing");
//        System.out.println(result.getId()+" \n"+result.getName()+" \n"+result.getDescription());


        Products updatedProduct = new Products();
        updatedProduct.setId(productId);
        updatedProduct.setName("Pajama");
        updatedProduct.setPrice(1500);
        updatedProduct.setDescription("This is a Pajama not saree");
        updatedProduct.setQtyInStocks(10);
//        System.out.println("update");
//        System.out.println(updatedProduct.getId()+"\n" +updatedProduct.getName()+"\n"
//                +updatedProduct.getDescription());
        Mockito.lenient().when(mockProductRepository.save(any(Products.class))).thenReturn(updatedProduct);
        // Act

        Products result = productService.updateProduct(existingProduct, productId);


        // Assert
        assertEquals(updatedProduct, result);
    }

}