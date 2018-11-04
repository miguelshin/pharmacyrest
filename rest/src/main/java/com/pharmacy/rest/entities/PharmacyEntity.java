package com.pharmacy.rest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(nullable = false, name = "code", length = 36)
    private String code;
    @Column(nullable = false, name = "name", length = 75)
    private String name;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "cif", length = 9)
    private String cif;
    @Column(name = "email", length = 200)
    private String email;
    @Column(name = "phone", length = 9)
    private String phone;
    @Column(nullable = false, name = "user_code", length = 36)
    private String userCode;

}
