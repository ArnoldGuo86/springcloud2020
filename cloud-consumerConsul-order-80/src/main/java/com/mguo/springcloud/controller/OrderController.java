package com.mguo.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	private static final String payment_url = "http://cloud-payment-service-consul";

	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/consumer/payment/consul")
	public String getPaymnt(Long id) {
		return restTemplate.getForObject(payment_url + "/payment/consul", String.class);
	}
}
