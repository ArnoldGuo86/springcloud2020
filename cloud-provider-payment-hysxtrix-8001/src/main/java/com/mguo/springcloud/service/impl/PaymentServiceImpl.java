package com.mguo.springcloud.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mguo.springcloud.dao.PaymentDao;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentDao paymentDao;

	@Override
	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {

		return paymentDao.getPaymentById(id);
	}

	@Override
	public String paymentinfo_OK(Integer id) {
		int x = 10 / 0;
		return "Thread pool: " + Thread.currentThread().getName() + " --OK-- id: " + id + "o(∩_∩)o 哈哈";
	}

	@Override
	@HystrixCommand(fallbackMethod = "paymentinfo_Timeout_handler", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	public String paymentinfo_Timeout(Integer id) {
		int time = 5;
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Thread pool: " + Thread.currentThread().getName() + " --timeout-- id: " + id + "~~~~(>_<)~~~~";
	}

	public String paymentinfo_Timeout_handler(Integer id) {
		return "Thread pool: " + Thread.currentThread().getName() + " --paymentinfo_Timeout_handler: " + id
				+ "~~~~(>_<)~~~~";
	}

	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		if (id < 0) {
			throw new RuntimeException("id is less than 0!!!");
		}

		return Thread.currentThread().getName() + "\t invoke successfully with serial number: " + UUID.randomUUID();
	}

	public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
		return "id cannot be less than zero";
	}

}
