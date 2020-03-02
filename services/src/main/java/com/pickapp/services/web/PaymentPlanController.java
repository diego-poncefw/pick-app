package com.pickapp.services.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pickapp.dto.ResultDTO;
import com.pickapp.services.model.PaymentPlan;
import com.pickapp.services.model.User;
import com.pickapp.services.model.UserPaymentPlan;
import com.pickapp.services.service.PaymentPlanService;
import com.pickapp.services.service.UserService;
import com.pickapp.util.Constants;
import com.pickapp.util.TokenUtil;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("paymentPlan")
public class PaymentPlanController {

	@Autowired
	private PaymentPlanService paymentPlanService;
	@Autowired
	private UserService userService;

	@GetMapping()
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<PaymentPlan> findAll() {
		return paymentPlanService.findAll();
	}

	@PostMapping(value = "/buyPlan")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResultDTO buyPlan(@RequestBody PaymentPlan plan, @RequestHeader(value = "Authorization") String token) {
		ResultDTO result = new ResultDTO();
		try {
			Claims claims = TokenUtil.decodeJWT(token);
			String email = claims.get(Constants.EMAIL).toString();
			User user = userService.findByEmail(email);
			UserPaymentPlan userPlan = new UserPaymentPlan();
			userPlan.setPaymentPlan(plan);
			userPlan.setUser(user);
			paymentPlanService.saveUserPaymentPlan(userPlan);
			user.setCredit(user.getCredit().add(plan.getPrize()));
			userService.saveNewUser(user);
			result.setCode(Constants.SUCCESS_CODE);
			result.setMessage("SUCCESS");
		} catch (Exception e) {
			result.setCode(Constants.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
