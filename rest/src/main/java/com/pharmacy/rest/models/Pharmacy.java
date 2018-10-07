package com.pharmacy.rest.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
