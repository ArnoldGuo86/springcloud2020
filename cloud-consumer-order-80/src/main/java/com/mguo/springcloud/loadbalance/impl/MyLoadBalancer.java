package com.mguo.springcloud.loadbalance.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import com.mguo.springcloud.loadbalance.ILoadBalancer;

@Component
public class MyLoadBalancer implements ILoadBalancer {

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public final int getAndIncrement() {
		int current;

		int next;

		for (;;) {
			current = atomicInteger.get();

			next = current >= Integer.MAX_VALUE ? 0 : current + 1;

			if (this.atomicInteger.compareAndSet(current, next)) {
				System.out.println("*****next: " + next);
				return next;
			}
		}

	}

	@Override
	public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
		int index = getAndIncrement() % serviceInstances.size();

		return serviceInstances.get(index);
	}

}
