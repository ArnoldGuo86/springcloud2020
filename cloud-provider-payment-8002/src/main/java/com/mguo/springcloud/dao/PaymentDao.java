package com.mguo.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mguo.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {
	public int create(Payment payment);

	public Payment getPaymentById(@Param("id") Long id);
}
