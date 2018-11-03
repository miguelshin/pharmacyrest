package com.pharmacy.rest.services.laboratory;

import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.models.Laboratory;

public interface LaboratoryService {
    List<Laboratory> searchLaboratories(String textName);
    Optional<Laboratory> getLaboratory(String code);
    Laboratory saveLaboratory(Laboratory laboratory);
    boolean deleteLaboratory(String code);
    Optional<Laboratory> updateLaboratory(Laboratory laboratory);
}
