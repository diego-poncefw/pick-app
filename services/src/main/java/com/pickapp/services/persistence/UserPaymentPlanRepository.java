package com.pickapp.services.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickapp.services.model.UserPaymentPlan;

public interface UserPaymentPlanRepository extends JpaRepository<UserPaymentPlan, Integer>{

}
