package com.pickapp.services.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pickapp.dto.ProductDTO;
import com.pickapp.dto.TransactionDTO;
import com.pickapp.services.service.ProductService;
import com.pickapp.util.Constants;
import com.pickapp.util.TokenUtil;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/byMachine")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<ProductDTO> findByMachine(@Param("number") Integer number) {
		return productService.findByMachine(number);
	}

	@PostMapping(value = "/pay")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public TransactionDTO pay(@RequestBody List<ProductDTO> products,
			@RequestHeader(value = "Authorization") String token) {
		TransactionDTO result = new TransactionDTO();
		try {
			Claims claims = TokenUtil.decodeJWT(token);
			String email = claims.get(Constants.EMAIL).toString();
			result = productService.pay(products, email);
		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
