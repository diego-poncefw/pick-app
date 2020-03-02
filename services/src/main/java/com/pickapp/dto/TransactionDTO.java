package com.pickapp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author sebas
 *
 */
public class TransactionDTO extends ResultDTO implements Serializable {

	private static final long serialVersionUID = 3020613388917765023L;
	private List<String> qrCodeKeys;

	public List<String> getQrCodeKeys() {
		return qrCodeKeys;
	}

	public void setQrCodeKeys(List<String> qrCodeKeys) {
		this.qrCodeKeys = qrCodeKeys;
	}

}
