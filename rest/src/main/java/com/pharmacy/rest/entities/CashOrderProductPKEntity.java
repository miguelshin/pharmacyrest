package com.pharmacy.rest.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class CashOrderProductPKEntity implements Serializable {
	private static final long serialVersionUID = 6979837682146236047L;
	
	@ManyToOne
    @JoinColumn(name = "cash_order_code")
    private CashOrderEntity cashOrder;
    @ManyToOne
    @JoinColumn(name = "product_code")
    private ProductEntity product;
}
