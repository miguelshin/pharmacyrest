package com.pharmacy.rest.controllers.pharmacy;

import java.util.List;
import java.util.UUID;

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
import com.pharmacy.rest.services.PharmacyService;

@RequestMapping("/pharmacy")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }) 
@RestController
public class PharmacyRestController {
    protected static final String ROOT_PATH = "/pharmacy";
    protected static final String DELETE_ROOT_PATH = "/{code}";
    @Autowired
    @Qualifier("pharmacyService")
    private PharmacyService pharmacyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pharmacy>> getPharmacies(@RequestParam(value="textName", required=false) String textName) {
        List<Pharmacy> pharmacies = pharmacyService.searchPharmacies(textName);
        return new ResponseEntity<List<Pharmacy>>(pharmacies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pharmacy savePharmacy(@RequestBody Pharmacy pharmacy) {
        pharmacy.setCode(UUID.randomUUID().toString());
        Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
        return savedPharmacy;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy pharmacy) {
        Pharmacy updatedPharmacy = pharmacyService.updatePharmacy(pharmacy);
        if (updatedPharmacy != null) {
    		return new ResponseEntity<Pharmacy>(updatedPharmacy, HttpStatus.ACCEPTED);        	
        } else {
    		return new ResponseEntity<Pharmacy>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = DELETE_ROOT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePharmacy(@PathVariable(value="code") String code) {
    	if (pharmacyService.deletePharmacy(code)) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    }
}
