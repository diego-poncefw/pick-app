package com.pickapp.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickapp.services.model.PaymentPlan;
import com.pickapp.services.model.UserPaymentPlan;
import com.pickapp.services.persistence.PaymentPlanRepository;
import com.pickapp.services.persistence.UserPaymentPlanRepository;
import com.pickapp.services.service.PaymentPlanService;

@Service
public class PaymentPlanServiceImpl implements PaymentPlanService {

	@Autowired
	private PaymentPlanRepository paymentPlanRepository;
	@Autowired
	private UserPaymentPlanRepository userPaymentPlanRepository;

	@Override
	public List<PaymentPlan> findAll() {
		return paymentPlanRepository.findAll();
	}

	@Override
	public void saveUserPaymentPlan(UserPaymentPlan userPaymentPlan) {
		userPaymentPlanRepository.save(userPaymentPlan);
	}

}
