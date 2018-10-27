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
import com.pharmacy.rest.services.pharmacy.PharmacyService;

@RequestMapping("/pharmacy")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }) 
@RestController
public class PharmacyRestController {
    protected static final String CODE_ROOT_PATH = "/{code}";
    
    final static Logger logger = Logger.getLogger(PharmacyRestController.class);
    
    @Autowired
    @Qualifier("pharmacyService")
    private PharmacyService pharmacyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pharmacy>> searchPharmacies(@RequestParam(value="textName", required=false) String textName) {
        List<Pharmacy> pharmacies = pharmacyService.searchPharmacies(textName);
        return new ResponseEntity<List<Pharmacy>>(pharmacies, HttpStatus.OK);
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Object> getPharmacy(@PathVariable(value="code") String code) {
    	try {
	    	Optional<Pharmacy> pharmacy = pharmacyService.getPharmacy(code);
	        if (pharmacy.isPresent()) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(pharmacy.get());
	        } else {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body("Is not possible access to that pharmacy");
	        }
    	} catch (Exception e) {
    		logger.info("Error updating pharmacy: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pharmacy savePharmacy(@RequestBody Pharmacy pharmacy) {
        pharmacy.setCode(UUID.randomUUID().toString());
        Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
        return savedPharmacy;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePharmacy(@RequestBody Pharmacy pharmacy) {
    	try {
    		Optional<Pharmacy> updatedPharmacy = pharmacyService.updatePharmacy(pharmacy);
	        if (updatedPharmacy.isPresent()) {
	    		return ResponseEntity
	    				.status(HttpStatus.ACCEPTED)
	    				.body(updatedPharmacy.get());        	
	        } else {
	    		return ResponseEntity
	    				.status(HttpStatus.NOT_ACCEPTABLE)
	    				.body("The pharmacy cannot be updated by this user");        	
	        }
    	} catch (Exception e) {
    		logger.info("Error updating pharmacy: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePharmacy(@PathVariable(value="code") String code) {
    	if (pharmacyService.deletePharmacy(code)) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    }
}
