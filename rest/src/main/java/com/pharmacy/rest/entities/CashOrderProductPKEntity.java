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
    @ManyToOne
    @JoinColumn(name = "cashorder_id")
    private CashOrderEntity cashorder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
