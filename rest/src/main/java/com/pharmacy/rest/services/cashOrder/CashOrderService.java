package com.pharmacy.rest.services.cashOrder;

import java.util.Date;
import java.util.List;

import com.pharmacy.rest.models.CashOrder;

public interface CashOrderService {
    public List<CashOrder> searchCashOrders(Date date);
    public CashOrder getCashOrder(String code);
    public CashOrder saveCashOrder(CashOrder cashOrder);
    public CashOrder updateCashOrder(CashOrder cashOrder);
    public boolean deleteCashOrder(String code);

}
