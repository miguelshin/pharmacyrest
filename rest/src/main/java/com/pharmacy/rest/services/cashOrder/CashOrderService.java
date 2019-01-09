package com.pharmacy.rest.services.cashOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.pharmacy.rest.models.CashOrder;

public interface CashOrderService {
    List<CashOrder> searchCashOrdersByMonth(int month, int year);
    List<CashOrder> searchCashOrders(Date date);
    Optional<CashOrder> getCashOrder(String code);
    CashOrder saveCashOrder(CashOrder cashOrder);
    Optional<CashOrder> updateCashOrder(CashOrder cashOrder);
    boolean deleteCashOrder(String code);

}
