package com.pickapp.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.services.model.TransferCredit;
import com.pickapp.services.persistence.TransferCreditRepository;
import com.pickapp.services.service.TransferCreditService;

@Service
public class TransferCreditServiceImpl implements TransferCreditService {

	@Autowired
	private TransferCreditRepository transferCreditRepository;

	@Override
	public void save(TransferCredit transferCredit) {
		transferCreditRepository.save(transferCredit);
	}

}
