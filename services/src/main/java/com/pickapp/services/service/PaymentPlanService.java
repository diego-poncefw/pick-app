package com.pickapp.services.service;

import java.util.List;

import com.pickapp.services.model.PaymentPlan;
import com.pickapp.services.model.UserPaymentPlan;

public interface PaymentPlanService {

	List<PaymentPlan> findAll();

	void saveUserPaymentPlan(UserPaymentPlan userPaymentPlan);

}
