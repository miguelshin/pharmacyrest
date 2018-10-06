package com.pharmacy.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class OrderEntity implements Serializable {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="name", length=75)
    private String name;
    @Column(name="date")
    private String date;
    @ManyToOne
    private PharmacyEntity pharmacy;

}
