package com.pharmacy.rest.models;

import java.util.Date;
import java.util.List;

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
public class CashOrder {
	@NotNull
	private String code;
	@NotNull
	private Date date;
	@NotNull
	private List<CashOrderProduct> cashOrderProducts;
	@NotNull
	private Pharmacy pharmacy;
	private String observations;
}
