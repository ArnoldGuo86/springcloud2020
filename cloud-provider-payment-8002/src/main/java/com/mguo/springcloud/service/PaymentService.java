package com.mguo.springcloud.service;

import com.mguo.springcloud.entities.Payment;

public interface PaymentService {

	public int create(Payment payment);

	public Payment getPaymentById(Long id);
}
