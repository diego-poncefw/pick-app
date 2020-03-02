package com.pickapp.enums;

public enum StatusEnum {

	ACTIVE("A"), INACTIVE("I"), PENDING("P"), CONFIRM("C");

	private String value;

	private StatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
