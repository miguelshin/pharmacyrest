package com.pharmacy.rest.services.pharmacy;

import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.models.Pharmacy;

public interface PharmacyService {
    List<Pharmacy> searchPharmacies(String textName);
    Optional<Pharmacy> getPharmacy(String code);
    Pharmacy savePharmacy(Pharmacy pharmacy);
    boolean deletePharmacy(String code);
    Optional<Pharmacy> updatePharmacy(Pharmacy pharmacy);
}
