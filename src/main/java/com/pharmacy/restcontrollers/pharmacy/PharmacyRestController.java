package com.pharmacy.restcontrollers.pharmacy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.models.*;
import com.pharmacy.repositories.pharmacy.PharmacyJpaRepository;
import com.pharmacy.restcontrollers.main.MainController;
import com.pharmacy.services.PharmacyService;

@RequestMapping("/pharmacy")

@RestController
public class PharmacyRestController {
    protected static final String ROOT_PATH = "/pharmacy";

    @Autowired
    @Qualifier("pharmacyService")
    private PharmacyService pharmacyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pharmacy>> getPharmacies(@RequestParam(value="textName", required=false) String textName) {
        List<Pharmacy> pharmacies = pharmacyService.searchPharmacies(textName);
        return new ResponseEntity<List<Pharmacy>>(pharmacies, HttpStatus.OK);
    }
/*
 * 
 * 

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }
@GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
 */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Pharmacy> savePharmacy(@RequestBody Pharmacy pharmacy) {
        pharmacy.setCode(UUID.randomUUID().toString());
        Pharmacy savedPharmacy = pharmacyService.savePharmacy(pharmacy);
        return new ResponseEntity<Pharmacy>(savedPharmacy, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Pharmacy> updatePharmacy(String textName) {
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePharmacy(String textName) {
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        pharmacies.add(pharmacy);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
