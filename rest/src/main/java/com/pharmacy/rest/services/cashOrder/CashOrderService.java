package com.pharmacy.rest.services.cashOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.models.CashOrder;

public interface CashOrderService {
    public List<CashOrder> searchCashOrdersByMonth(int month, int year);
    public List<CashOrder> searchCashOrders(Date date);
    public Optional<CashOrder> getCashOrder(String code);
    public CashOrder saveCashOrder(CashOrder cashOrder);
    public Optional<CashOrder> updateCashOrder(CashOrder cashOrder);
    public boolean deleteCashOrder(String code);

}
