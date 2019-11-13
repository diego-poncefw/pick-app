package com.pickapp.services.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String qrKeyOne;
	private String qrKeyTwo;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    User user;
	
	@ManyToOne
    @JoinColumn(name = "machine_product")
    MachineProduct machineProduct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQrKeyOne() {
		return qrKeyOne;
	}

	public void setQrKeyOne(String qrKeyOne) {
		this.qrKeyOne = qrKeyOne;
	}

	public String getQrKeyTwo() {
		return qrKeyTwo;
	}

	public void setQrKeyTwo(String qrKeyTwo) {
		this.qrKeyTwo = qrKeyTwo;
	}
}
