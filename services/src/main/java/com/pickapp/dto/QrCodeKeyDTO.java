package com.pickapp.dto;

import java.io.Serializable;

public class QrCodeKeyDTO implements Serializable {

	private static final long serialVersionUID = 3240591402139380842L;
	private String qrCodeKey;

	public QrCodeKeyDTO(String qrCodeKey) {
		this.qrCodeKey = qrCodeKey;
	}

	public String getQrCodeKey() {
		return qrCodeKey;
	}

	public void setQrCodeKey(String qrCodeKey) {
		this.qrCodeKey = qrCodeKey;
	}

}
