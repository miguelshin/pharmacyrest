package com.pharmacy.services;

import java.util.List;

import com.pharmacy.models.Pharmacy;

public interface PharmacyService {
    public abstract List<Pharmacy> searchPharmacies(String textName);
    public abstract Pharmacy getPharmacy(String code);

}
