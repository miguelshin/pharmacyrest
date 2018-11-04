package com.pharmacy.rest.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Pharmacy {
	@NotNull
	private String code;
	@NotNull
	private String name;
	private String address;
	private String cif;
	private String email;
	private String phone;
	private String userCode;
}
