package com.pharmacy.auth.entities;

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
@Table(name="userapp")
public class UserEntity {
    @Id
    @Column(name="username", length=30)
    private String username;
    @Column(name="password", length=60)
    private String password;

}
