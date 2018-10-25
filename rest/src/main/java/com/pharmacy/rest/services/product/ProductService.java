package com.pharmacy.rest.services.product;

import java.util.List;

import com.pharmacy.rest.models.Product;

public interface ProductService {
    public List<Product> searchProducts(String textName);
    public List<Product> searchProductsByLaboratoryCode(String textName, String laboratoryCode);
    public abstract Product getProduct(String code);
    public abstract Product saveProduct(Product product);
    public abstract boolean deleteProduct(String code);
    public abstract Product updateProduct(Product product);
}
