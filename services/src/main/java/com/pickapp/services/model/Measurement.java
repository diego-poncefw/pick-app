package com.pickapp.services.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Measurement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "measurement")
    Set<ProductMeasurement> productMeasurements;

	private String name;
	private BigDecimal prize;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public Set<ProductMeasurement> getProductMeasurements() {
		return productMeasurements;
	}

	public void setProductMeasurements(Set<ProductMeasurement> productMeasurements) {
		this.productMeasurements = productMeasurements;
	}

}
