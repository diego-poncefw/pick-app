package com.pickapp.services.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pickapp.services.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select p.name as product, mp.quantity, me.name as measurement, me.prize, p.id, mp.id as machineProduct from machine_product mp inner join machine m "
			+ "on mp.machine_id = m.id inner join product p on mp.product_id = p.id "
			+ "inner join product_measurement pm on pm.product_id = p.id "
			+ "inner join measurement me on me.id = pm.measurement_id " + "where m.number = ?1", nativeQuery = true)
	public List<Object[]> findProductsByMachine(Integer number);
}
