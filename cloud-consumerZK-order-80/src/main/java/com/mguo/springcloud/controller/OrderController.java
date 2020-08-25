package com.mguo.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	private static final String payment_url = "http://cloud-payment-service";

	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/consumer/payment/ZK")
	public String getPaymnt(Long id) {
		return restTemplate.getForObject(payment_url + "/payment/zk", String.class);
	}
}
