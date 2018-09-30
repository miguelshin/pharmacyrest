package com.pharmacy.restcontrollers.pharmacy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.models.*;
import com.pharmacy.restcontrollers.main.MainController;

@RestController
public class PharmacyRestController extends MainController {
	protected static String ROOT_PATH = "/pharmacy";

	@RequestMapping("/pharmacy")

	@GetMapping
    public ResponseEntity<List<Pharmacy>> getPharmacies(String textName) {
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        pharmacies.add(pharmacy);
        return new ResponseEntity<List<Pharmacy>>(pharmacies, HttpStatus.OK);
    }

	@PostMapping
    public ResponseEntity<Pharmacy> savePharmacy(String textName) {
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.OK);
    }

	@PutMapping
    public ResponseEntity<Pharmacy> updatePharmacy(String textName) {
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.OK);
    }

	@DeleteMapping
    public ResponseEntity<Void> deletePharmacy(String textName) {
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        Pharmacy pharmacy = new Pharmacy("12121212", "Farmacia 1", "-", "-");
        pharmacies.add(pharmacy);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
