package com.pickapp.services.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickapp.services.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
