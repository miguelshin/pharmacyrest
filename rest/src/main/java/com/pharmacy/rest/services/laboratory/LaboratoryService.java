package com.pharmacy.rest.services.laboratory;

import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.models.Laboratory;

public interface LaboratoryService {
    public List<Laboratory> searchLaboratories(String textName);
    public abstract Optional<Laboratory> getLaboratory(String code);
    public abstract Laboratory saveLaboratory(Laboratory laboratory);
    public abstract boolean deleteLaboratory(String code);
    public abstract Optional<Laboratory> updateLaboratory(Laboratory laboratory);
}
