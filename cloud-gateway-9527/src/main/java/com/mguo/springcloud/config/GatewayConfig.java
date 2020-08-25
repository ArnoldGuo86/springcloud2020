package com.mguo.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customizeRouteLocater(RouteLocatorBuilder builder) {
		Builder routes = builder.routes();
		routes.route("route1", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
		routes.route("route1", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
		return routes.build();
	}

}
