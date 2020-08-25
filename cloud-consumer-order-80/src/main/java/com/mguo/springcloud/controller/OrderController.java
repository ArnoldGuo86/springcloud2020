package com.mguo.springcloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.loadbalance.ILoadBalancer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	private static final String payment_url = "http://CLOUD-PAYMENT-SERVICE";

	@Resource
	private RestTemplate restTemplate;

	@Resource
	private DiscoveryClient discoveryClient;

	@Resource
	private ILoadBalancer loadBalancer;

	@GetMapping("/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		log.info("consumer: " + payment);
		return restTemplate.postForObject(payment_url + "/payment/create", payment, CommonResult.class);
	}

	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymnt(@PathVariable("id") Long id) {
		return restTemplate.getForObject(payment_url + "/payment/get/" + id, CommonResult.class);
	}

	@GetMapping("/consumer/payment/getforentity/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		ResponseEntity<CommonResult> entity = restTemplate.getForEntity(payment_url + "/payment/get/" + id,
				CommonResult.class);

		if (entity.getStatusCode().is2xxSuccessful()) {
			System.out.println("-------------------------");
			System.out.println(entity);
			System.out.println("-------------------------");
			return entity.getBody();
		} else {
			log.info("操作失败");
			return new CommonResult<Payment>(444, "操作失败", null);
		}
	}

	@GetMapping("/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		if (serviceInstances == null || serviceInstances.size() <= 0) {
			return null;
		}

		ServiceInstance serviceInstance = loadBalancer.instance(serviceInstances);
		URI uri = serviceInstance.getUri();

		return restTemplate.getForObject(uri + "/payment/lb", String.class);
	}

	@GetMapping("/consumer/payment/zipkin")
	public String paymentZipkin() {
		String result = restTemplate.getForObject(payment_url + "/payment/zipkin/", String.class);

		return result;
	}

}
