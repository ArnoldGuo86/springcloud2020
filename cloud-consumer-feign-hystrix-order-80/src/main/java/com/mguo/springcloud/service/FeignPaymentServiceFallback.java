package com.mguo.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class FeignPaymentServiceFallback implements FeignPaymentService {

	@Override
	public String paymentinfo_OK(Integer id) {
		return "paymentinfo_OK	fallback";
	}

	@Override
	public String paymentinfo_Timeout(Integer id) {
		return "paymentinfo_Timeout fall back";
	}

}
