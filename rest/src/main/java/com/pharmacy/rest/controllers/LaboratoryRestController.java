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

import com.pharmacy.rest.models.Laboratory;
import com.pharmacy.rest.services.laboratory.LaboratoryService;

@RequestMapping("/laboratory")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class LaboratoryRestController {
    protected static final String CODE_ROOT_PATH = "/{code}";
    
    final static Logger logger = Logger.getLogger(LaboratoryRestController.class);
    
    @Autowired
    @Qualifier("laboratoryService")
    private LaboratoryService laboratoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Laboratory>> searchLaboratories(@RequestParam(value="textName", required=false) String textName) {
        List<Laboratory> laboratories = laboratoryService.searchLaboratories(textName);
        return new ResponseEntity<List<Laboratory>>(laboratories, HttpStatus.OK);
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Object> getLaboratory(@PathVariable(value="code") String code) {
    	try {
	    	Optional<Laboratory> laboratory = laboratoryService.getLaboratory(code);
	        if (laboratory.isPresent()) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(laboratory.get());
	        } else {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body("Is not possible access to that laboratory");
	        }
    	} catch (Exception e) {
    		logger.info("Error updating laboratory: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Laboratory saveLaboratory(@RequestBody Laboratory laboratory) {
        laboratory.setCode(UUID.randomUUID().toString());
        Laboratory savedLaboratory = laboratoryService.saveLaboratory(laboratory);
        return savedLaboratory;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateLaboratory(@RequestBody Laboratory laboratory) {
    	try {
    		Optional<Laboratory> updatedLaboratory = laboratoryService.updateLaboratory(laboratory);
	        if (updatedLaboratory.isPresent()) {
	    		return ResponseEntity
	    				.status(HttpStatus.ACCEPTED)
	    				.body(updatedLaboratory.get());        	
	        } else {
	    		return ResponseEntity
	    				.status(HttpStatus.NOT_ACCEPTABLE)
	    				.body("The laboratory cannot be updated by this user");        	
	        }
    	} catch (Exception e) {
    		logger.info("Error updating laboratory: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLaboratory(@PathVariable(value="code") String code) {
    	if (laboratoryService.deleteLaboratory(code)) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    }
}
