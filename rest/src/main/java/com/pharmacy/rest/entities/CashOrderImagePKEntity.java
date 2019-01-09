package com.pharmacy.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class CashOrderImagePKEntity implements Serializable {
    private static final long serialVersionUID = 6979837682146236089L;

    @ManyToOne
    @JoinColumn(name = "cash_order_code")
    private CashOrderEntity cashOrder;
    @Column(name="url")
    private String url;
}