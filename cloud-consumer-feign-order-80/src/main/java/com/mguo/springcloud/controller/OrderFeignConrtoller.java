package com.mguo.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.service.FeignPaymentService;

@RestController
public class OrderFeignConrtoller {

	@Resource
	private FeignPaymentService paymentService;

	@GetMapping("/consumer/payment/feignget/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		CommonResult<Payment> payment = paymentService.getPaymentById(id);

		return payment;
	}

	@GetMapping("/consumer/payment/feign/timeout")
	public String paymentFeignTimeOut() {
		String paymentFeignTimeOut = paymentService.paymentFeignTimeOut();

		return paymentFeignTimeOut;
	}
}
