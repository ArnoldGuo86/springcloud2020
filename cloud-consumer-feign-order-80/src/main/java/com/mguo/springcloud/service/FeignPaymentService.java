package com.mguo.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface FeignPaymentService {
	@GetMapping("/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

	@GetMapping("/payment/feign/timeout")
	public String paymentFeignTimeOut();
}
