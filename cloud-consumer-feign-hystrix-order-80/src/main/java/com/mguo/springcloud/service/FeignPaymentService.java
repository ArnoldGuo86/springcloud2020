package com.mguo.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE", fallback = FeignPaymentServiceFallback.class)
public interface FeignPaymentService {

	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentinfo_OK(@PathVariable("id") Integer id);

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentinfo_Timeout(@PathVariable("id") Integer id);
}
