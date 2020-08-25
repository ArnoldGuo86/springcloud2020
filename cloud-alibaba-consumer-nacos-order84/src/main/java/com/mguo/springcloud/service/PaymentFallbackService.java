package com.mguo.springcloud.service;

import org.springframework.stereotype.Component;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;
@Component
public class PaymentFallbackService implements PaymentService {
	@Override
	public CommonResult<Payment> paymentSQL(Long id) {
		return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
	}
}
