package com.pickapp.services.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickapp.services.model.PaymentPlan;

public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Integer>{

}
