package com.pharmacy.rest.services.pharmacy;

import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.entities.PharmacyEntity;
import com.pharmacy.rest.models.Pharmacy;

public interface PharmacyService {
    public abstract List<Pharmacy> searchPharmacies(String textName);
    public PharmacyEntity checkUserAndGetPharmacyEntity(String code);
    public Optional<Pharmacy> getPharmacy(String code);
    public abstract Pharmacy savePharmacy(Pharmacy pharmacy);
    public abstract boolean deletePharmacy(String code);
    public abstract Optional<Pharmacy> updatePharmacy(Pharmacy pharmacy);
}
