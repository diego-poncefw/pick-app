package com.pickapp.services.service;

import java.util.List;

import com.pickapp.services.model.Machine;
import com.pickapp.services.model.Transaction;

public interface MachineService {

	void save(Machine machine);

	List<Machine> findByNumber(Integer number);

	Transaction findTransactionById(Integer id);

	void saveTransaction(Transaction transaction);

}
