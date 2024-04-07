package org.hardware.Service;

import lombok.extern.slf4j.Slf4j;
import org.hardware.Entity.Products;
import org.hardware.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Products createProduct(Products products) {
        products.setId(UUID.randomUUID());
        Products prod = productRepository.save(products);
        log.info("Product updated to the database. \n" +
                "Product: " + products.getId() + "\n" +
                "Product Name: " + products.getName() + "\n" +
                "Product Description: " + products.getDescription() + "\n" +
                "Product Quantity in stock: " + products.getQtyInStocks());
        return prod;

    }

    public Products updateProduct(Products products, UUID productId){
        products.setId(productId);
        Products prod = productRepository.save(products);

        log.info("Product updated to the database. \n" +
                "Product: " + products.getId() + "\n" +
                "Product Name: " + products.getName() + "\n" +
                "Product Description: " + products.getDescription() + "\n" +
                "Product Quantity in stock: " + products.getQtyInStocks());
        return prod;

    }

    public Optional<Products> findProductById(UUID id) {
        Optional<Products> prod = productRepository.findById(id);
        return prod;


    }

    public Products deleteProductById(UUID id) {

        productRepository.deleteById(id);
        log.info("Deleted product : " + id);
        return null;
    }


    public List<Products> findAllProduct() {
        List<Products> all = productRepository.findAll();
        return all;
    }



}
