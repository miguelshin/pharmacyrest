package com.pharmacy.rest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="pharmacy")
public class PharmacyEntity {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="name", length=75)
    private String name;
    @Column(name="address", length=150)
    private String address;
    @Column(name="cif", length=9)
    private String cif;
    @Column(name="user_code", length=36)
    private String userCode;

}
