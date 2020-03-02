package com.pickapp.services.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String qrKeyOne;
	private String qrKeyTwo;
	private String status;
	private BigDecimal total;
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@ManyToOne
	@JoinColumn(name = "machine_product")
	MachineProduct machineProduct;

	public Transaction() {
	}

	public Transaction(String qrKeyOne, String status, BigDecimal total, Date fecha, User user,
			MachineProduct machineProduct) {
		this.qrKeyOne = qrKeyOne;
		this.status = status;
		this.total = total;
		this.fecha = fecha;
		this.user = user;
		this.machineProduct = machineProduct;
	}

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

	public String getStatus() {
		return status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public User getUser() {
		return user;
	}

	public MachineProduct getMachineProduct() {
		return machineProduct;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMachineProduct(MachineProduct machineProduct) {
		this.machineProduct = machineProduct;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
