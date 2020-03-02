package com.pickapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 7814261313493112387L;
	Integer id;
	String name;
	BigDecimal quantity;
	String measurement;
	BigDecimal prize;
	Integer productMachine;

	public ProductDTO() {
	}

	public ProductDTO(Integer id,String name, BigDecimal quantity, String measurement, BigDecimal prize, Integer productMachine) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.measurement = measurement;
		this.prize = prize;
		this.productMachine = productMachine;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public String getMeasurement() {
		return measurement;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductMachine() {
		return productMachine;
	}

	public void setProductMachine(Integer productMachine) {
		this.productMachine = productMachine;
	}

}
