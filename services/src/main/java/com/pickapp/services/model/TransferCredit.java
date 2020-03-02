package com.pickapp.services.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransferCredit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal credit;

	@ManyToOne
	@JoinColumn(name = "user_to")
	User userTo;

	@ManyToOne
	@JoinColumn(name = "user_from")
	User userFrom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public User getUserTo() {
		return userTo;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}
}
