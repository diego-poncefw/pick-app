package com.pickapp.services.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickapp.services.model.TransferCredit;

public interface TransferCreditRepository extends JpaRepository<TransferCredit, Integer> {

}
