package com.pharmacy.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.rest.models.*;
import com.pharmacy.rest.services.cashOrder.CashOrderService;

@RequestMapping("/cashOrder")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class CashOrderRestController {
	protected static final String PERIOD_ROOT_PATH = "/{month}/{year}";
    protected static final String CODE_ROOT_PATH = "/{code}";

    final static Logger logger = Logger.getLogger(CashOrderRestController.class);
    
    @Autowired
    @Qualifier("cashOrderService")
    private CashOrderService cashOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CashOrder>> searchCashOrders(@RequestParam(value="date", required=false) Date date) {
        List<CashOrder> cashOrders = cashOrderService.searchCashOrders(date);
        return new ResponseEntity<List<CashOrder>>(cashOrders, HttpStatus.OK);
    }

	@RequestMapping(value = PERIOD_ROOT_PATH, method = RequestMethod.GET)
	public ResponseEntity<List<CashOrder>> searchCashOrders(@PathVariable(value="month", required=false) int month,
															@PathVariable(value="year", required=false) int year) {
		List<CashOrder> cashOrders = cashOrderService.searchCashOrdersByMonth(month, year);
		return new ResponseEntity<>(cashOrders, HttpStatus.OK);
	}

	@RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Object> getCashOrder(@PathVariable(value="code") String code) {
    	try {
	    	Optional<CashOrder> cashOrder = cashOrderService.getCashOrder(code);
	        if (cashOrder.isPresent()) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(cashOrder.get());
	        } else {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body("Is not possible access to that cashOrder");
	        }
    	} catch (Exception e) {
    		logger.info("Error updating cashOrder: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CashOrder saveCashOrder(@RequestBody CashOrder cashOrder) {
        cashOrder.setCode(UUID.randomUUID().toString());
        CashOrder savedCashOrder = cashOrderService.saveCashOrder(cashOrder);
        return savedCashOrder;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCashOrder(@RequestBody CashOrder cashOrder) {
    	try {
    		Optional<CashOrder> updatedCashOrder = cashOrderService.updateCashOrder(cashOrder);
	        if (updatedCashOrder.isPresent()) {
	    		return ResponseEntity
	    				.status(HttpStatus.ACCEPTED)
	    				.body(updatedCashOrder.get());        	
	        } else {
	    		return ResponseEntity
	    				.status(HttpStatus.NOT_ACCEPTABLE)
	    				.body("The cashOrder cannot be updated by this user");        	
	        }
    	} catch (Exception e) {
    		logger.info("Error updating cashOrder: " + e.getMessage());
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body("There was succeed an error");
    	}
    }

    @RequestMapping(value = CODE_ROOT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCashOrder(@PathVariable(value="code") String code) {
    	if (cashOrderService.deleteCashOrder(code)) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
}
