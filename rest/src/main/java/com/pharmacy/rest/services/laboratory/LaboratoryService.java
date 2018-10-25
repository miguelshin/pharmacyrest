package com.pharmacy.rest.services.laboratory;

import java.util.List;

import com.pharmacy.rest.models.Laboratory;

public interface LaboratoryService {
    public List<Laboratory> searchLaboratories(String textName);
    public abstract Laboratory getLaboratory(String code);
    public abstract Laboratory saveLaboratory(Laboratory laboratory);
    public abstract boolean deleteLaboratory(String code);
    public abstract Laboratory updateLaboratory(Laboratory laboratory);
}
