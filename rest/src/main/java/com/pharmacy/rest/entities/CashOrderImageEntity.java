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
    @EmbeddedId
    private CashOrderImagePKEntity id;
}
