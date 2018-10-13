package com.pharmacy.rest.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@Min(8)
	private String code;
	@NotNull
	@Max(75)
	private String name;
	@Max(150)
	private String address;
	@Max(9)
	private String cif;
	@Min(8)
	private String userCode;
}
