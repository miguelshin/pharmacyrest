package com.pharmacy.rest.services.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.ProductConverter;
import com.pharmacy.rest.entities.ProductEntity;
import com.pharmacy.rest.models.Product;
import com.pharmacy.rest.repositories.laboratory.LaboratoryJpaRepository;
import com.pharmacy.rest.repositories.product.ProductJpaRepository;
import com.pharmacy.rest.repositories.product.ProductQueryDSLRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("productJpaRepository")
    private ProductJpaRepository productJpaRepository;

    @Autowired
    @Qualifier("laboratoryJpaRepository")
    private LaboratoryJpaRepository laboratoryJpaRepository;

    @Autowired
    @Qualifier("productQueryDSLRepository")
    private ProductQueryDSLRepository productQueryDSLRepository;

    @Override
    public List<Product> searchProducts(String textName) {
    	String userCode = getUserCodeFromAuthentication();
        List<ProductEntity> productEntities = productQueryDSLRepository.searchProducts(textName, userCode);
        List<Product> products = new ArrayList<Product>();
        for (ProductEntity productEntity : productEntities) {
            products.add(ProductConverter.productEntityToModel(productEntity));
        }
        return products;
    }

    @Override
    public List<Product> searchProductsByLaboratoryCode(String textName, String laboratoryCode) {
    	String userCode = getUserCodeFromAuthentication();
        List<ProductEntity> productEntities = productQueryDSLRepository.searchProductsByLaboratoryCode(textName, laboratoryCode, userCode);
        List<Product> products = new ArrayList<Product>();
        for (ProductEntity productEntity : productEntities) {
            products.add(ProductConverter.productEntityToModel(productEntity));
        }
        return products;
    }
    
    @Override
    public Optional<Product> getProduct(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	ProductEntity productEntity = productJpaRepository.findByCodeAndLaboratoryUserCode(code, userCode);
        Product product = ProductConverter.productEntityToModel(productEntity);
        return Optional.of(product);
    }
    
    @Override
    public Product saveProduct(Product product) {
        if (checkUserIfAccessLaboratoryIsGranted(product.getLaboratory().getCode())) {
	        ProductEntity productEntity = ProductConverter.productModelToEntity(product);
	        productJpaRepository.save(productEntity);
	        return getProduct(productEntity.getCode()).get();
        }
        return null;
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
    	Optional<Product> updatedProduct = Optional.empty();
    	ProductEntity productEntity = checkUserAndGetProductEntity(product.getCode());
    	if (productEntity != null) {
	        productJpaRepository.save(ProductConverter.productModelToEntity(product));
	        updatedProduct = getProduct(productEntity.getCode());
    	}
    	return updatedProduct;
    }
    
    @Override
    public boolean deleteProduct(String code) {
    	ProductEntity productEntity = checkUserAndGetProductEntity(code);
    	if (productEntity != null) {
    		productJpaRepository.delete(productEntity);
    		return true;
    	}
    	return false;
    }

    private String getUserCodeFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
    
    private boolean checkUserIfAccessLaboratoryIsGranted(String laboratoryCode) {
    	String userCode = getUserCodeFromAuthentication();
    	return (laboratoryJpaRepository.findByCodeAndUserCode(laboratoryCode, userCode) != null);
    }

    private ProductEntity checkUserAndGetProductEntity(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	return productJpaRepository.findByCodeAndLaboratoryUserCode(code, userCode);
    }

}
