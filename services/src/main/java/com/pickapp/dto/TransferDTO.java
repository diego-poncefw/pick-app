package com.pickapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferDTO implements Serializable {

	private static final long serialVersionUID = -4487905104188865443L;
	private BigDecimal credit;
	private String emailUserTo;

	public BigDecimal getCredit() {
		return credit;
	}

	public String getEmailUserTo() {
		return emailUserTo;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public void setEmailUserTo(String emailUserTo) {
		this.emailUserTo = emailUserTo;
	}

}
