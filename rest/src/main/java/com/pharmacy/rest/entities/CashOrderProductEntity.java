package com.pharmacy.rest.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cashorder_product")
public class CashOrderProductEntity {
    @EmbeddedId
    private CashOrderProductPKEntity id;
    @Column(name="quantity")
    private int quantity;
    @Column(name="amount")
    private double amount;

}
