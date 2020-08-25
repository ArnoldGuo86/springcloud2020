package com.mguo.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mguo.springcloud.dao.PaymentDao;
import com.mguo.springcloud.entities.Payment;
import com.mguo.springcloud.service.PaymentService;

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

}
