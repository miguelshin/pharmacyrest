package com.pharmacy.rest.services.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pharmacy.rest.converter.ProductConverter;
import com.pharmacy.rest.entities.LaboratoryEntity;
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
    public Product getProduct(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	ProductEntity productEntity = productJpaRepository.findByCodeAndLaboratoryUserCode(code, userCode);
        Product product = ProductConverter.productEntityToModel(productEntity);
        return product;
    }
    
    @Override
    public Product saveProduct(Product product) {
    	String userCode = getUserCodeFromAuthentication();
        LaboratoryEntity laboratoryEntity = laboratoryJpaRepository.findByCodeAndUserCode(product.getLaboratory().getCode(), userCode);
        if (laboratoryEntity != null) {
	        ProductEntity productEntity = ProductConverter.productModelToEntity(product);
	        productJpaRepository.save(productEntity);
	        return getProduct(productEntity.getCode());
        }
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
    	String userCode = getUserCodeFromAuthentication();
    	ProductEntity productEntity = productJpaRepository.findByCodeAndLaboratoryUserCode(product.getCode(), userCode);
    	if (productEntity != null) {
	        productJpaRepository.save(productEntity);
	        return getProduct(productEntity.getCode());
    	}
    	return null;
    }
    
    @Override
    public boolean deleteProduct(String code) {
    	String userCode = getUserCodeFromAuthentication();
    	ProductEntity productEntity = this.productJpaRepository.findByCodeAndLaboratoryUserCode(code, userCode);
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
}
