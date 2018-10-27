package com.pharmacy.rest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.rest.models.*;
import com.pharmacy.rest.services.product.ProductService;

@RequestMapping("/product")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }) 
@RestController
public class ProductRestController {
    protected static final String CODE_ROOT_PATH = "/{code}";
    
    final static Logger logger = Logger.getLogger(ProductRestController.class);
    
    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(value="textName", required=false) String textName) {
        List<Product> products = productService.searchProducts(textName);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable(value="code") String code) {
    	try {
	    	Optional<Product> product = productService.getProduct(code);
	        if (product.isPresent()) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(product.get());
	        } else {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body("Is not possible access to that product");
	        }
    	} catch (Exception e) {
    		logger.info("Error updating product: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        product.setCode(UUID.randomUUID().toString());
        Product savedProduct = productService.saveProduct(product);
        return savedProduct;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
    	try {
    		Optional<Product> updatedProduct = productService.updateProduct(product);
	        if (updatedProduct.isPresent()) {
	    		return ResponseEntity
	    				.status(HttpStatus.ACCEPTED)
	    				.body(updatedProduct.get());        	
	        } else {
	    		return ResponseEntity
	    				.status(HttpStatus.NOT_ACCEPTABLE)
	    				.body("The product cannot be updated by this user");        	
	        }
    	} catch (Exception e) {
    		logger.info("Error updating product: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable(value="code") String code) {
    	if (productService.deleteProduct(code)) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    }
}
