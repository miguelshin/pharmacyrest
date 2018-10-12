package com.pharmacy.rest.services;

import java.util.List;

import com.pharmacy.rest.models.Pharmacy;

public interface PharmacyService {
    public abstract List<Pharmacy> searchPharmacies(String textName);
    public abstract Pharmacy getPharmacy(String code);
    public abstract Pharmacy savePharmacy(Pharmacy pharmacy);
    public abstract boolean deletePharmacy(String code);
    public abstract Pharmacy updatePharmacy(Pharmacy pharmacy);
}
