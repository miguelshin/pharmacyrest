package com.pharmacy.rest.services.product;

import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.models.Product;

public interface ProductService {
    List<Product> searchProducts(String textName);
    List<Product> searchProductsByLaboratoryCode(String textName, String laboratoryCode);
    Optional<Product> getProduct(String code);
    Product saveProduct(Product product);
    boolean deleteProduct(String code);
    Optional<Product> updateProduct(Product product);
}
