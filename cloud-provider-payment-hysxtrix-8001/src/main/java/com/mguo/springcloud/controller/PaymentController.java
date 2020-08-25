package com.mguo.springcloud.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Resource
	private DiscoveryClient discoveryClient;

	@Value("${server.port}")
	private String serverPort;

	@PostMapping(value = "/payment/create")
	public CommonResult<Payment> create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("插入成功: " + result);
		if (result > 0) {
			return new CommonResult<Payment>(200, "插入成功: " + serverPort, payment);
		} else {
			return new CommonResult<Payment>(444, "插入失败", null);
		}
	}

	@GetMapping("/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);

		log.info("query result: " + payment);

		if (payment != null) {
			return new CommonResult<Payment>(200, "找到了: " + serverPort, payment);
		} else {
			return new CommonResult<Payment>(444, "没找到", null);
		}
	}

	@GetMapping(value = "/payment/discovery")
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		for (String service : services) {
			log.info("service: " + service);
		}

		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance instance : instances) {
			log.info(instance.getServiceId() + "\t" + instance.getHost() + "'\t" + instance.getPort() + "\t"
					+ instance.getUri());
		}

		return this.discoveryClient;
	}

	@GetMapping("/payment/lb")
	public String getPaymentPort() {
		return serverPort;
	}

	@GetMapping("/payment/feign/timeout")
	public String paymentFeignTimeOut() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "finish";
	}

	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentinfo_OK(@PathVariable("id") Integer id) {
		return paymentService.paymentinfo_OK(id);
	}

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentinfo_Timeout(@PathVariable("id") Integer id) {
		return paymentService.paymentinfo_Timeout(id);
	}

	@GetMapping("/payment/hystrix/Circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable(value = "id") Integer id) {
		return paymentService.paymentCircuitBreaker(id);
	}
}
