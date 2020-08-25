package com.mguo.springcloud.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mguo.springcloud.entities.CommonResult;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "global_timeoutHandler")
public class OrderFeignHystrixConrtoller {

	@Resource
	private FeignPaymentService paymentService;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentinfo_OK(@PathVariable("id") Integer id) {
		return paymentService.paymentinfo_OK(id);
	}

	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
//	@HystrixCommand(fallbackMethod = "paymentinfo_Timeout_handler", commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//	})
	@HystrixCommand
	public String paymentinfo_Timeout(@PathVariable("id") Integer id) {
		return paymentService.paymentinfo_Timeout(id);
	}
	
	public String paymentinfo_Timeout_handler(Integer id) {
		return "Thread pool: " + Thread.currentThread().getName() + " -- from OrderFeignHystrixConrtoller --paymentinfo_Timeout_handler: " + id
				+ "~~~~(>_<)~~~~";
	}
	
	public String global_timeoutHandler() {
		return "系统繁忙或与运行错误，请10秒钟后重试";
	}
}
