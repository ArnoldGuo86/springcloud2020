package com.mguo.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.mguo.springcloud.entities.Payment;

public interface PaymentService {

	public int create(Payment payment);

	public Payment getPaymentById(@Param("id") Long id);

	public String paymentinfo_OK(Integer id);

	public String paymentinfo_Timeout(Integer id);

	public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
