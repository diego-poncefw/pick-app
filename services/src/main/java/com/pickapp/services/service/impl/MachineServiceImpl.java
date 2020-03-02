package com.pickapp.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.services.model.Machine;
import com.pickapp.services.model.Transaction;
import com.pickapp.services.persistence.MachineRepository;
import com.pickapp.services.service.MachineService;
import com.pickapp.services.service.TransactionRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public void save(Machine machine) {
		machineRepository.save(machine);
	}

	@Override
	public List<Machine> findByNumber(Integer number) {
		return machineRepository.findByNumber(number);
	}

	@Override
	public Transaction findTransactionById(Integer id) {
		return transactionRepository.getOne(id);
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

}
