package com.mguo.springcloud.loadbalance;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

public interface ILoadBalancer {

	ServiceInstance instance(List<ServiceInstance> servceInstances);
}
