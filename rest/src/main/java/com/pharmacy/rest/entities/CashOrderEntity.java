package com.pharmacy.rest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name="cashorder")
public class CashOrderEntity {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="date")
    private Date date;
    @ManyToOne
    private PharmacyEntity pharmacy;

}
