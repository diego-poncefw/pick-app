package com.pickapp.services.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.dto.ProductDTO;
import com.pickapp.dto.TransactionDTO;
import com.pickapp.enums.StatusEnum;
import com.pickapp.services.model.MachineProduct;
import com.pickapp.services.model.Transaction;
import com.pickapp.services.model.User;
import com.pickapp.services.persistence.ProductRepository;
import com.pickapp.services.persistence.UserRepository;
import com.pickapp.services.service.ProductService;
import com.pickapp.services.service.TransactionRepository;
import com.pickapp.util.Constants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<ProductDTO> findByMachine(Integer number) {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		List<Object[]> results = productRepository.findProductsByMachine(number);
		for (Object[] p : results) {
			ProductDTO product = new ProductDTO(Integer.parseInt(p[4].toString()), p[0].toString(),
					new BigDecimal(p[1].toString()), p[2].toString(), new BigDecimal(p[3].toString()),
					Integer.parseInt(p[5].toString()));
			products.add(product);
		}
		return products;
	}

	@Override
	@Transactional
	public TransactionDTO pay(List<ProductDTO> products, String userEmail) {
		TransactionDTO result = new TransactionDTO();
		List<String> qrCodeKeys = new ArrayList<String>();
		try {
			BigDecimal total = BigDecimal.ZERO;
			for (ProductDTO product : products) {
				total = total.add(product.getPrize());
			}
			User user = userRepository.findByEmail(userEmail);
			if (total.doubleValue() <= user.getCredit().doubleValue()) {
				for (ProductDTO product : products) {
					MachineProduct machineProduct = new MachineProduct();
					machineProduct.setId(product.getProductMachine());
					Transaction transaction = new Transaction(
							product.getProductMachine() + "/" + product.getId() + "/" + product.getQuantity() + "/"
									+ user.getId() + "/" + StatusEnum.PENDING.getValue(),
							StatusEnum.PENDING.getValue(), total, new Date(), user, machineProduct);
					transactionRepository.save(transaction);
					qrCodeKeys.add(transaction.getQrKeyOne() + "/" + transaction.getId());
				}
				user.setCredit(user.getCredit().subtract(total));
				userRepository.save(user);
				result.setQrCodeKeys(qrCodeKeys);
				result.setCode(Constants.SUCCESS_CODE);
				result.setMessage("SUCCESS");
			} else {
				result.setCode(Constants.ERROR_CODE);
				result.setMessage("Insufficient Credit");
			}
		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;

	}

}
