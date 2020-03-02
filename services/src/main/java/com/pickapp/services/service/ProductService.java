package com.pickapp.services.service;

import java.util.List;

import com.pickapp.dto.ProductDTO;
import com.pickapp.dto.TransactionDTO;

public interface ProductService {

	List<ProductDTO> findByMachine(Integer number);

	TransactionDTO pay(List<ProductDTO> products, String userEmail);

}
