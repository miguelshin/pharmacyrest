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
@Table(name="laboratory")
public class LaboratoryEntity {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="name", length=75)
    private String name;
    @Column(name="user_code", length=36)
    private String userCode;

}
