package com.pickapp.services.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.pickapp.services.model.Machine;

public interface MachineRepository extends JpaRepository<Machine, Integer> {

	public List<Machine> findByNumber(@Param("number") Integer number);

}
