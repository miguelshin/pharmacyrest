package com.pharmacy.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cashorder_image")
public class CashOrderImageEntity {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="url", length=250)
    private String url;
    @ManyToOne
    private CashOrderEntity cashOrder;
}
