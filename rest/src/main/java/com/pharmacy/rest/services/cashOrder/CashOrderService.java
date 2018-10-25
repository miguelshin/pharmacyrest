package com.pharmacy.rest.services.cashOrder;

import java.util.Date;
import java.util.List;

import com.pharmacy.rest.models.CashOrder;
import com.pharmacy.rest.models.Laboratory;

public interface CashOrderService {
	public List<CashOrder> searchCashOrders(Date date);
    public CashOrder getCashOrder(String code);
    public abstract Laboratory getLaboratory(String code);
    public abstract Laboratory saveLaboratory(Laboratory laboratory);
    public abstract boolean deleteLaboratory(String code);
    public abstract Laboratory updateLaboratory(Laboratory laboratory);
}
