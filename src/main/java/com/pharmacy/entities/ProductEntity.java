package com.pharmacy.entities;

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
@Table(name="product")
public class ProductEntity {
    @Id
    @Column(name="code", length=36)
    private String code;
    @Column(name="name", length=75)
    private String name;
    @ManyToOne
    private LaboratoryEntity laboratory;
}
